package com.olivia.sdk.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Strings;
import java.util.Arrays;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据权限返回值类型枚举 定义数据权限过滤值的数据类型，用于SQL条件构建时选择合适的参数类型
 */
@Getter
@AllArgsConstructor
public enum DataPermissionRetType {

  /**
   * 数值类型，适用于ID、编号等整数类型的权限过滤值
   */
  NUMBER("Number", "数值类型"),

  /**
   * 字符串类型，适用于名称、编码等字符串类型的权限过滤值
   */
  STRING("Str", "字符串类型");

  /**
   * 枚举值标识，用于序列化和类型转换
   */
  @JsonValue
  private final String code;

  /**
   * 枚举描述，用于日志输出和业务说明
   */
  private final String description;

  /**
   * 根据编码获取枚举实例 使用Google Guava的Strings工具类处理空值，避免NPE
   *
   * @param code 枚举编码
   * @return 对应的枚举实例，若未找到则返回空Optional
   */
  public static Optional<DataPermissionRetType> getByCode(String code) {
    if (Strings.isNullOrEmpty(code)) {
      return Optional.empty();
    }

    return Arrays.stream(values()).filter(type -> type.code.equals(code)).findFirst();
  }

  /**
   * 重写toString方法，返回描述信息 便于日志输出和调试时快速理解枚举含义
   *
   * @return 枚举描述
   */
  @Override
  public String toString() {
    return description;
  }
}
