package com.olivia.sdk.config.entity;

import com.olivia.sdk.utils.$;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 数据过滤表配置实体类 用于配置数据权限过滤规则，包括单值/多值过滤、权限类型、过滤列和路径规则等
 */
@Getter
@Setter
@Accessors(chain = true)
public class FilterTable {

  /**
   * 是否为单值过滤 true: 单值过滤（如多租户ID） false: 多值过滤（如数据权限列表）
   */
  private Boolean oneFilterValue = Boolean.FALSE;  // 默认多值过滤

  /**
   * 数据权限类型 关联到具体的DataPermissionService实现类
   */
  private String dataPermissionType;

  /**
   * 过滤列名 数据库表中用于过滤的字段名
   */
  private String columnName;

  /**
   * 忽略过滤的路径列表 符合这些路径规则的操作将不应用数据过滤
   */
  private List<String> ignorePath;

  /**
   * 需要应用过滤的路径列表 符合这些路径规则的操作将应用数据过滤
   */
  private List<String> filterPath;

  /**
   * 获取非空的忽略路径列表 确保返回的列表永远不为null，避免空指针异常
   *
   * @return 非空的忽略路径列表
   */
  public List<String> getIgnorePath() {
    return $.getNoNullList(ignorePath);
  }

  /**
   * 获取非空的过滤路径列表 确保返回的列表永远不为null，避免空指针异常
   *
   * @return 非空的过滤路径列表
   */
  public List<String> getFilterPath() {
    return $.getNoNullList(filterPath);
  }

  /**
   * 设置单值过滤标志 增强类型安全，确保不会设置null值
   *
   * @param oneFilterValue 是否为单值过滤
   * @return 当前对象，支持链式调用
   */
  public FilterTable setOneFilterValue(Boolean oneFilterValue) {
    this.oneFilterValue = Objects.requireNonNullElse(oneFilterValue, Boolean.FALSE);
    return this;
  }

  /**
   * 重写toString方法，便于日志输出和调试
   *
   * @return 包含关键属性的字符串表示
   */
  @Override
  public String toString() {
    return "FilterTable{" + "oneFilterValue=" + oneFilterValue + ", dataPermissionType='" + dataPermissionType + '\'' + ", columnName='" + columnName + '\''
        + ", ignorePathSize=" + getIgnorePath().size() + ", filterPathSize=" + getFilterPath().size() + '}';
  }
}
