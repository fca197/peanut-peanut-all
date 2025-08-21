package com.olivia.sdk.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Stream;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * BigDecimal工具类，提供对对象中BigDecimal类型字段进行放大操作的功能 支持精确匹配和前缀匹配两种字段匹配模式
 */
@Slf4j
public final class BigDecimalUtils {

  /**
   * 私有构造函数，防止工具类实例化
   */
  private BigDecimalUtils() {
    throw new AssertionError("工具类不允许实例化");
  }

  /**
   * 对单个对象的BigDecimal字段进行放大操作
   *
   * @param obj          待处理对象
   * @param expand       放大倍数
   * @param matchType    字段匹配类型
   * @param fieldNameArr 字段名称数组
   */
  public static void valueExpand(Object obj, double expand, MatchType matchType, String... fieldNameArr) {
    if (obj == null) {
      log.warn("待处理对象为null，不执行放大操作");
      return;
    }
    valueExpand(List.of(obj), expand, matchType, fieldNameArr);
  }

  /**
   * 对对象列表中的BigDecimal字段进行批量放大操作
   *
   * @param list         待处理对象列表
   * @param expand       放大倍数
   * @param matchType    字段匹配类型（非空）
   * @param fieldNameArr 字段名称数组（非空）
   */
  public static void valueExpand(List<? extends Object> list, double expand,
      @NonNull MatchType matchType, @NonNull String... fieldNameArr) {
    // 输入参数校验
    if (CollUtil.isEmpty(list) || fieldNameArr.length == 0) {
      log.warn("数据列表为空或字段名称数组为空，不执行放大操作");
      return;
    }

    // 获取第一个对象的类信息（所有对象视为同一类型）
    Class<?> clazz = list.getFirst().getClass();
    List<Field> targetFields = getTargetFields(clazz, matchType, fieldNameArr);

    if (targetFields.isEmpty()) {
      log.debug("未找到匹配的字段，不执行放大操作");
      return;
    }

    // 准备放大倍数的BigDecimal表示
    BigDecimal multiplicand = BigDecimal.valueOf(expand);

    // 遍历所有对象和字段进行放大操作
    for (Object obj : list) {
      if (obj == null) {
        log.warn("跳过null对象");
        continue;
      }

      for (Field field : targetFields) {
        // 只处理BigDecimal类型的字段
        if (!BigDecimal.class.equals(field.getType())) {
          log.trace("字段[{}]不是BigDecimal类型，跳过处理", field.getName());
          continue;
        }

        try {
          // 获取并检查字段值
          BigDecimal fieldValue = (BigDecimal) ReflectUtil.getFieldValue(obj, field);
          if (Objects.nonNull(fieldValue)) {
            // 执行放大操作并设置回字段（保留4位小数，向下取整）
            BigDecimal expandedValue = fieldValue.multiply(multiplicand)
                .setScale(4, RoundingMode.DOWN);
            ReflectUtil.setFieldValue(obj, field, expandedValue);
          }
        } catch (Exception e) {
          log.error("处理字段[{}]时发生异常", field.getName(), e);
        }
      }
    }
  }

  /**
   * 根据匹配类型获取目标字段列表
   *
   * @param clazz        目标类
   * @param matchType    匹配类型
   * @param fieldNameArr 字段名称数组
   * @return 匹配的字段列表
   */
  private static List<Field> getTargetFields(Class<?> clazz, MatchType matchType, String[] fieldNameArr) {
    List<Field> targetFields = new ArrayList<>();

    // 获取类及其父类的所有字段
    Field[] declaredFields = clazz.getDeclaredFields();
    Field[] superDeclaredFields = clazz.getSuperclass() != null ? clazz.getSuperclass().getDeclaredFields() : new Field[0];
    Field[] allFields = Stream.concat(Arrays.stream(declaredFields), Arrays.stream(superDeclaredFields))
        .toArray(Field[]::new);

    if (MatchType.eq.equals(matchType)) {
      // 精确匹配：字段名完全相等
      for (String fieldName : fieldNameArr) {
        Field field = Arrays.stream(allFields)
            .filter(f -> f.getName().equals(fieldName))
            .findFirst()
            .orElse(null);

        if (field != null) {
          targetFields.add(field);
        } else {
          log.debug("未找到精确匹配的字段: {}", fieldName);
        }
      }
    } else if (MatchType.likeLeft.equals(matchType)) {
      // 前缀匹配：字段名以指定字符串开头
      for (String prefix : fieldNameArr) {
        Arrays.stream(allFields)
            .filter(f -> f.getName().startsWith(prefix))
            .forEach(targetFields::add);
      }
      log.debug("前缀匹配找到 {} 个字段", targetFields.size());
    }

    return targetFields;
  }

  /**
   * 字段匹配类型枚举
   */
  public enum MatchType {
    /**
     * 前缀匹配（字段名以指定字符串开头）
     */
    likeLeft,
    /**
     * 精确匹配（字段名完全相等）
     */
    eq
  }
}
