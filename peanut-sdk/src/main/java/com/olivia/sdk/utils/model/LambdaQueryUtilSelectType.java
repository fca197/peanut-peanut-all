package com.olivia.sdk.utils.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Lambda查询工具选择类型枚举 定义支持的查询条件操作类型，对应MyBatis-Plus的查询方法
 */

@Getter
@AllArgsConstructor
public enum LambdaQueryUtilSelectType {

  /**
   * 等于 (=)
   */
  EQ("等于", "="),

  /**
   * 不等于 (!=)
   */
  NE("不等于", "!="),

  /**
   * 大于 (>)
   */
  GT("大于", ">"),

  /**
   * 大于等于 (>=)
   */
  GE("大于等于", ">="),

  /**
   * 小于 (<)
   */
  LT("小于", "<"),

  /**
   * 小于等于 (<=)
   */
  LE("小于等于", "<="),

  /**
   * 模糊匹配 (%)value(%)
   */
  LIKE("模糊匹配", "LIKE"),

  /**
   * 左模糊匹配 (%)value
   */
  LIKE_LEFT("左模糊匹配", "LIKE"),

  /**
   * 右模糊匹配 value(%)
   */
  LIKE_RIGHT("右模糊匹配", "LIKE"),

  /**
   * 包含 (IN)
   */
  IN("包含", "IN"),

  /**
   * 为空 (IS NULL)
   */
  IS_NULL("为空", "IS NULL");

  /**
   * 操作类型描述
   */
  private final String description;

  /**
   * 对应的SQL操作符
   */
  private final String sqlOperator;

}
