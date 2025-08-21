package com.olivia.sdk.mybatis.type.model;

import org.apache.ibatis.type.JdbcType;

/**
 * 自定义JDBC类型枚举 扩展MyBatis支持的JDBC类型，用于处理特殊数据类型（如JSON、JSONB等）
 */
public enum CustomJdbcType {

  /**
   * JSON类型 对应数据库中的JSON数据类型，使用MyBatis的OTHER类型代码
   */
  JSON(JdbcType.OTHER.TYPE_CODE),

  /**
   * JSONB类型 对应PostgreSQL等数据库中的JSONB数据类型，使用MyBatis的OTHER类型代码
   */
  JSONB(JdbcType.OTHER.TYPE_CODE);

  /**
   * JDBC类型代码 对应java.sql.Types中的类型代码
   */
  private final int typeCode;

  /**
   * 构造函数
   *
   * @param typeCode JDBC类型代码
   */
  CustomJdbcType(int typeCode) {
    this.typeCode = typeCode;
  }

  /**
   * 根据类型代码获取对应的CustomJdbcType枚举
   *
   * @param typeCode 类型代码
   * @return 对应的CustomJdbcType，如果没有匹配的则返回null
   */
  public static CustomJdbcType fromTypeCode(int typeCode) {
    for (CustomJdbcType type : values()) {
      if (type.typeCode == typeCode) {
        return type;
      }
    }
    return null;
  }

  /**
   * 获取JDBC类型代码
   *
   * @return 类型代码
   */
  public int getTypeCode() {
    return typeCode;
  }

  /**
   * 将当前自定义类型转换为对应的MyBatis JdbcType
   *
   * @return 对应的JdbcType
   */
  public JdbcType toJdbcType() {
    return JdbcType.forCode(typeCode);
  }
}
