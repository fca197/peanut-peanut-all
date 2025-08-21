package com.olivia.sdk.config;

import cn.hutool.core.collection.CollUtil;
import com.google.common.base.Preconditions;
import com.olivia.sdk.config.entity.*;
import com.olivia.sdk.exception.RunException;
import jakarta.annotation.PostConstruct;
import java.time.Instant;
import java.util.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 系统核心配置属性类 用于绑定并管理应用程序的核心配置参数
 */
@Setter
@Getter
@Accessors(chain = true)
@Component
@ConfigurationProperties(prefix = "peanut")
public class PeanutProperties {

  /**
   * 系统启动时间（秒级），使用JDK 8+的Instant API获取更精确的时间戳
   */
  private final long systemStartSecond = Instant.now().getEpochSecond();
  /**
   * 公钥字符串，用于加密/解密操作
   */
  private String publicKey;
  /**
   * 私钥字符串，用于加密/解密操作
   */
  private String privateKey;
  /**
   * 模拟服务配置
   */
  private Mock mock;

  /**
   * 匹配规则配置
   */
  private Match match;

  /**
   * URL白名单列表，用于无需认证的接口路径
   */
  private List<String> urlWhiteList;

  /**
   * Redis键配置
   */
  private RedisKey redisKey;

  /**
   * 实体类包名，用于扫描实体类
   */
  private String entityPackageName;

  /**
   * 扫描属性并应用的时间间隔（秒），默认60秒
   */
  private long scanProperty2UseInTimeSecond = 60L;

  /**
   * 是否初始化数据库，默认false
   */
  private boolean dbInit;

  /**
   * 本地文件上传路径
   */
  private String localFileUploadPath;

  /**
   * APS系统配置
   */
  private ApsProperties aps;

  /**
   * 销售转项目的线程池大小
   */
  private Integer sale2ProjectThreadSize;

  /**
   * 钉钉配置列表
   */
  private List<DingConfig> dingConfigList;

  /**
   * 禁用的操作列表
   */
  private String[] disableOperation;

  /**
   * 默认使用的应用名称
   */
  private String defaultUseAppName;

  /**
   * 订单状态更新所需的天数
   */
  private Integer orderStatusUpdateNeedDayCount;

  /**
   * 服务器通知用户ID映射表 key: 通知类型, value: 接收通知的用户ID列表
   */
  private Map<String, List<String>> serverNoticeUserIdMap;

  /**
   * 过滤表配置列表
   */
  private List<FilterTable> filterTableList;

  /**
   * 授权码，用于系统授权验证
   */
  private String licenseCode;

  /**
   * 初始化后检查必要的配置参数 使用PostConstruct确保在依赖注入完成后执行
   */
  @PostConstruct
  public void validate() {
    // 检查关键配置参数是否存在
    Preconditions.checkState(scanProperty2UseInTimeSecond > 0, "扫描属性的时间间隔必须大于0秒");

    // 对需要非空的核心配置进行校验
    Optional.ofNullable(redisKey).orElseThrow(() -> new RunException("Redis配置不能为空"));
  }

  /**
   * 判断URL是否在白名单中
   *
   * @param url 待检查的URL
   * @return 如果URL以白名单中的任一前缀开头则返回true，否则返回false
   */
  public boolean isUrlWhite(String url) {
    // 使用Google Guava的Preconditions进行参数校验
    Preconditions.checkNotNull(url, "URL不能为空");

    return CollUtil.isNotEmpty(urlWhiteList) && urlWhiteList.stream().anyMatch(url::startsWith);
  }

  /**
   * 根据企业ID获取对应的钉钉配置
   *
   * @param corpId 企业ID
   * @return 对应的钉钉配置
   * @throws RunException 如果未找到对应配置
   */
  public DingConfig getDingConfig(String corpId) {
    Preconditions.checkNotNull(corpId, "企业ID(corpId)不能为空");

    // 使用JDK 8+的Stream API进行过滤查找
    return Optional.ofNullable(dingConfigList).flatMap(list -> list.stream().filter(config -> Objects.equals(corpId, config.getCorpId())).findFirst())
        .orElseThrow(() -> new RunException("企业没有配置钉钉信息：" + corpId));
  }

  /**
   * 获取销售转项目的线程池大小，提供默认值
   *
   * @return 线程池大小，默认值为CPU核心数
   */
  public int getSale2ProjectThreadSize() {
    return Optional.ofNullable(sale2ProjectThreadSize).orElse(Runtime.getRuntime().availableProcessors());
  }

  /**
   * 获取订单状态更新所需的天数，提供默认值
   *
   * @return 天数，默认值为30天
   */
  public int getOrderStatusUpdateNeedDayCount() {
    return Optional.ofNullable(orderStatusUpdateNeedDayCount).orElse(30);
  }
}
