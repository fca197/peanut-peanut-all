package com.olivia.sdk.config;

import cn.hutool.core.text.AntPathMatcher;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.DataPermissionHandler;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.*;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableMap;
import com.olivia.sdk.config.entity.FilterTable;
import com.olivia.sdk.enums.DataPermissionRetType;
import com.olivia.sdk.filter.LoginUserContext;
import com.olivia.sdk.service.DataPermissionService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.Str;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.schema.Column;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * MyBatis 配置类 配置MyBatis-Plus插件，包括多租户、数据权限、分页等功能
 */
@Slf4j
@Component
public class MybatisConfig {

  /**
   * 路径匹配器，用于匹配过滤路径
   */
  private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

  /**
   * 忽略表缓存，缓存需要忽略的数据过滤的表名
   */
  private static final Cache<String, Boolean> IGNORE_TABLE_CACHE = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(1, TimeUnit.HOURS).build();

  /**
   * MappedStatementID缓存，缓存需要数据过滤的SQL ID
   */
  private static final Cache<String, Boolean> MAPPED_STATEMENT_ID_CACHE = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(1, TimeUnit.HOURS).build();

  /**
   * 数据权限服务映射表，key为权限类型，value为对应的服务实现
   */
  private static final Map<String, DataPermissionService> DATA_PERMISSION_MAP = new ConcurrentHashMap<>();

  static {
    // 初始化路径匹配器配置
    ANT_PATH_MATCHER.setCaseSensitive(false);
    ANT_PATH_MATCHER.setTrimTokens(true);
  }

  @Resource
  private PeanutProperties peanutProperties;

  /**
   * 过滤路径检查 判断当前路径是否需要应用数据过滤
   *
   * @param filterTable 过滤表配置
   * @param currentPath 当前路径
   * @return 如果需要过滤返回true，否则返回false
   */
  private static boolean filterPath(FilterTable filterTable, String currentPath) {
    // 先检查是否在忽略路径列表中
    if (filterTable.getIgnorePath().stream().anyMatch(pattern -> ANT_PATH_MATCHER.matchStart(pattern, currentPath))) {
      log.trace("路径在忽略列表中 - 路径: {}, 过滤列: {}", currentPath, filterTable.getColumnName());
      return true;
    }

    // 再检查是否在过滤路径列表中
    boolean matches = filterTable.getFilterPath().stream().anyMatch(pattern -> ANT_PATH_MATCHER.matchStart(pattern, currentPath));

    log.trace("路径过滤检查 - 路径: {}, 过滤列: {}, 是否匹配: {}", currentPath, filterTable.getColumnName(), matches);
    return matches;
  }

  /**
   * 仅供测试使用，获取不可修改的数据权限服务映射表
   *
   * @return 不可修改的映射表
   */
  public static Map<String, DataPermissionService> getImmutableDataPermissionMap() {
    return ImmutableMap.copyOf(DATA_PERMISSION_MAP);
  }

  /**
   * 初始化数据权限服务映射表 在Spring容器初始化后，收集所有DataPermissionService实现类
   */
  @PostConstruct
  public void initDataPermissionServices() {
    // 使用Google Guava的ImmutableMap确保初始化后不可修改
    Map<String, DataPermissionService> services = SpringUtil.getBeansOfType(DataPermissionService.class);

    services.forEach((beanName, service) -> {
      String type = service.getType();
      // 检查是否有重复的权限类型
      $.assertTrueCanIgnoreException(!DATA_PERMISSION_MAP.containsKey(type), "数据权限类型冲突: 类型[", type, "]已存在");

      DATA_PERMISSION_MAP.put(type, service);
      log.debug("已注册数据权限服务: 类型[{}], 实现类[{}]", type, service.getClass().getSimpleName());
    });

    log.info("数据权限服务初始化完成，共注册 {} 个服务", DATA_PERMISSION_MAP.size());
  }

  /**
   * 配置MyBatis-Plus插件拦截器 包括多租户插件、数据权限插件、分页插件等
   *
   * @return MybatisPlusInterceptor实例
   */
  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

    // 获取过滤表配置列表，避免多次调用getter方法
    List<FilterTable> filterTables = peanutProperties.getFilterTableList();

    // 配置单值过滤的表（多租户模式）
    filterTables.stream().filter(FilterTable::getOneFilterValue).forEach(filterTable -> {
      TenantLineInnerInterceptor tenantInterceptor = new TenantLineInnerInterceptor(tenantLineHandler(filterTable));
      interceptor.addInnerInterceptor(tenantInterceptor);
      log.debug("已添加单值过滤拦截器: 表名[{}], 列名[{}]", filterTable.getColumnName(), filterTable.getColumnName());
    });

    // 配置多值过滤的表（数据权限模式）
    filterTables.stream().filter(t -> Boolean.FALSE.equals(t.getOneFilterValue())).forEach(filterTable -> {
      DataPermissionInterceptor dataPermissionInterceptor = new DataPermissionInterceptor(new DataPermissionHandlerImpl(filterTable));
      interceptor.addInnerInterceptor(dataPermissionInterceptor);
      log.debug("已添加多值过滤拦截器: 表名[{}], 列名[{}]", filterTable.getColumnName(), filterTable.getColumnName());
    });

    // 添加分页插件
    interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

    // 添加乐观锁插件
    interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

    // 添加防全表更新删除插件
    interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());

    log.info("MyBatis-Plus拦截器配置完成，共添加 {} 个过滤器", filterTables.size());
    return interceptor;
  }

  /**
   * 创建租户行处理器 用于处理单值过滤的多租户场景
   *
   * @param filterTable 过滤表配置
   * @return TenantLineHandler实例
   */
  private TenantLineHandler tenantLineHandler(FilterTable filterTable) {
    return new TenantLineHandler() {
      @Override
      public String getTenantIdColumn() {
        return filterTable.getColumnName();
      }

      @Override
      public Expression getTenantId() {
        // 获取数据权限服务
        DataPermissionService permissionService = DATA_PERMISSION_MAP.get(filterTable.getDataPermissionType());
        // 如果未找到指定服务，使用默认的租户ID服务
        permissionService = Objects.requireNonNullElse(permissionService, DATA_PERMISSION_MAP.get(Str.TENANT_ID));

        // 获取过滤值列表
        List<Serializable> valueList = permissionService.filterValueList();
        $.requireNonNullCanIgnoreException(!valueList.isEmpty(), "数据权限过滤值为空: 类型: " + permissionService.getType());

        // 获取返回值类型
        DataPermissionRetType retType = permissionService.getRetType();
        $.requireNonNullCanIgnoreException(retType, "数据权限返回类型为空: 类型: " + permissionService.getType());

        // 取第一个值作为租户ID
        Serializable value = valueList.getFirst();
        log.trace("租户过滤 - 类型: {}, 列名: {}, 值: {}, 类型: {}", permissionService.getType(), filterTable.getColumnName(), value, retType);

        // 根据返回类型创建对应的表达式
        return DataPermissionRetType.NUMBER.equals(retType) ? new LongValue(String.valueOf(value)) : new StringValue(String.valueOf(value));
      }

      @Override
      @SneakyThrows
      public boolean ignoreTable(String tableName) {
        // 如果当前请求忽略租户ID查询，则直接返回true
        if (LoginUserContext.isIgnoreTenantId()) {
          log.trace("忽略租户过滤 - 表名: {}", tableName);
          return true;
        }

        // 从缓存获取是否忽略该表的过滤
        Boolean ignore = IGNORE_TABLE_CACHE.get(tableName, () -> filterPath(filterTable, tableName));

        log.trace("租户过滤检查 - 表名: {}, 是否忽略: {}", tableName, ignore);
        return ignore;
      }
    };
  }

  /**
   * 数据权限处理器实现类 用于处理多值过滤的数据权限场景
   */
  static class DataPermissionHandlerImpl implements DataPermissionHandler {

    private final FilterTable filterTable;

    public DataPermissionHandlerImpl(FilterTable filterTable) {
      this.filterTable = filterTable;
    }

    @Override
    @SneakyThrows
    public Expression getSqlSegment(Expression where, String mappedStatementId) {
      // 检查是否需要应用数据权限过滤
      if (MAPPED_STATEMENT_ID_CACHE.get(mappedStatementId, () -> filterPath(filterTable, mappedStatementId))) {

        // 获取数据权限服务
        DataPermissionService dataPermissionService = DATA_PERMISSION_MAP.get(filterTable.getDataPermissionType());
        $.requireNonNullCanIgnoreException(dataPermissionService, "未找到数据权限服务: 类型: " + filterTable.getDataPermissionType());

        // 获取返回值类型和过滤值列表
        DataPermissionRetType retType = dataPermissionService.getRetType();
        List<Serializable> valueList = dataPermissionService.filterValueList();
        $.requireNonNullCanIgnoreException(!valueList.isEmpty(), "数据权限过滤值为空: 类型: " + dataPermissionService.getType());

        log.trace("数据权限过滤 - 类型: {}, 列名: {}, 值数量: {}", dataPermissionService.getType(), filterTable.getColumnName(), valueList.size());

        // 创建IN表达式的参数列表
        ExpressionList<Expression> itemsList = new ExpressionList<>();
        itemsList.addExpressions(valueList.stream().map(value -> convertToExpression(value, retType)).collect(Collectors.toList()));

        // 创建IN表达式
        InExpression inExpression = new InExpression(new Column(filterTable.getColumnName()), itemsList);

        // 如果已有WHERE条件，使用AND连接；否则直接返回IN表达式
        return Objects.nonNull(where) ? new AndExpression(where, inExpression) : inExpression;
      }

      // 不需要过滤，返回原WHERE条件
      return where;
    }

    /**
     * 将值转换为对应的SQL表达式
     *
     * @param value   值
     * @param retType 返回值类型
     * @return SQL表达式
     */
    private Expression convertToExpression(Serializable value, DataPermissionRetType retType) {
      return DataPermissionRetType.NUMBER.equals(retType) ? new LongValue(String.valueOf(value)) : new StringValue(String.valueOf(value));
    }
  }
}
