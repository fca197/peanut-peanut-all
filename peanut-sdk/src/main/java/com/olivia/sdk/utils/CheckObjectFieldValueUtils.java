package com.olivia.sdk.utils;

import static cn.hutool.core.util.ArrayUtil.contains;

import cn.hutool.core.util.ReflectUtil;
import com.olivia.peanut.ann.CheckObjectFieldValueAnn;
import com.olivia.peanut.enums.CheckEnums;
import com.olivia.sdk.ann.ImportCheck;
import com.olivia.sdk.dto.ExcelErrorMsg;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 对象字段值校验工具类，基于注解驱动实现字段合法性验证 支持多种数据类型（整数、长整数、字符串等）的校验规则
 */
@Slf4j
public final class CheckObjectFieldValueUtils {

  /**
   * 字段缓存，存储类与带校验注解的字段映射关系
   */
  private static final Map<Class<?>, Field[]> FIELD_CACHE_MAP = new ConcurrentHashMap<>();

  /**
   * 验证器工厂（线程安全）
   */
  private static final ValidatorFactory VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();

  /**
   * 校验函数映射，根据CheckEnums枚举关联对应的校验方法
   */
  private static final Map<CheckEnums, BiFunction<Object, CheckObjectFieldValueAnn, ExcelErrorMsg>> FUNCTION_MAP;

  static {
    // 初始化校验函数映射
    FUNCTION_MAP = new HashMap<>();
    FUNCTION_MAP.put(CheckEnums.Int, CheckObjectFieldValueUtils::checkObjectValueInt);
    FUNCTION_MAP.put(CheckEnums.Long, CheckObjectFieldValueUtils::checkObjectValueLong);
    FUNCTION_MAP.put(CheckEnums.Str, CheckObjectFieldValueUtils::checkObjectValueString);
  }

  /**
   * 私有构造函数，防止工具类实例化
   */
  private CheckObjectFieldValueUtils() {
    throw new AssertionError("工具类不允许实例化");
  }

  /**
   * 校验对象的字段值是否符合注解定义的规则
   *
   * @param obj 需要校验的对象
   * @return 校验错误信息列表，无错误则返回空列表
   */
  public static List<ExcelErrorMsg> check(Object obj) {
    if (obj == null) {
      return List.of();
    }

    // 从缓存获取带CheckObjectFieldValueAnn注解的字段
    Field[] fields = FIELD_CACHE_MAP.computeIfAbsent(obj.getClass(),
        clazz -> ReflectUtil.getFields(clazz, field -> field.isAnnotationPresent(CheckObjectFieldValueAnn.class)));

    if (fields == null || fields.length == 0) {
      return List.of();
    }

    List<ExcelErrorMsg> errorMsgList = new ArrayList<>();
    for (Field field : fields) {
      // 跳过静态字段
      if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
        continue;
      }

      try {
        Object fieldValue = ReflectUtil.getFieldValue(obj, field);
        CheckObjectFieldValueAnn annotation = field.getAnnotation(CheckObjectFieldValueAnn.class);

        if (annotation.useValid()) {
          // 使用JSR-380验证
          Set<ConstraintViolation<Object>> violations = VALIDATOR_FACTORY.getValidator().validateProperty(obj, field.getName(), ImportCheck.class);

          violations.forEach(violation -> {
            String errorMsg = StringUtils.firstNonEmpty(annotation.errorMessage(), violation.getMessage());
            errorMsgList.add(new ExcelErrorMsg().setColumnName(annotation.fieldShowName()).setErrMsg(errorMsg));
          });
        } else {
          // 使用自定义验证规则
          ExcelErrorMsg errorMsg = checkObjectValue(fieldValue, annotation);
          if (errorMsg != null) {
            errorMsgList.add(errorMsg);
          }
        }
      } catch (Exception e) {
        log.warn("字段校验异常 [类: {}, 字段: {}]", obj.getClass().getSimpleName(), field.getName(), e);
        errorMsgList.add(new ExcelErrorMsg().setColumnName(field.getName()).setErrMsg("字段校验发生异常: " + e.getMessage()));
      }
    }

    return errorMsgList;
  }

  /**
   * 根据注解配置校验字段值
   *
   * @param fieldValue 字段值
   * @param annotation 校验注解
   * @return 校验错误信息，无错误则返回null
   */
  private static ExcelErrorMsg checkObjectValue(Object fieldValue, CheckObjectFieldValueAnn annotation) {
    // 处理空值情况
    if (fieldValue == null) {
      if (!annotation.allowEmpty()) {
        return new ExcelErrorMsg().setColumnName(annotation.fieldShowName()).setErrMsg(StringUtils.defaultIfBlank(annotation.errorMessage(), "字段未填写"));
      }
      return null;
    }

    // 根据校验类型执行对应校验
    CheckEnums checkEnum = annotation.checkEnum();
    if (checkEnum != null) {
      BiFunction<Object, CheckObjectFieldValueAnn, ExcelErrorMsg> validator = FUNCTION_MAP.get(checkEnum);
      if (validator != null) {
        ExcelErrorMsg errorMsg = validator.apply(fieldValue, annotation);
        if (errorMsg != null) {
          // 使用注解自定义错误信息（如果有）
          if (StringUtils.isNotBlank(annotation.errorMessage())) {
            errorMsg.setErrMsg(annotation.errorMessage());
          }
          return errorMsg;
        }
      } else {
        log.warn("未找到对应的校验器 [校验类型: {}]", checkEnum);
      }
    }

    return null;
  }

  /**
   * 校验字符串类型字段
   */
  private static ExcelErrorMsg checkObjectValueString(Object fieldValue, CheckObjectFieldValueAnn annotation) {
    if (!(fieldValue instanceof String strValue)) {
      return new ExcelErrorMsg().setColumnName(annotation.fieldShowName()).setErrMsg("字段值不是有效的字符串类型");
    }

    // 检查是否在允许的值范围内
    String[] allowedValues = annotation.strValues();
    if (allowedValues != null && allowedValues.length > 0) {
      if (!contains(allowedValues, strValue)) {
        String allowedStr = Arrays.stream(allowedValues).collect(Collectors.joining(",", "[", "]"));
        return new ExcelErrorMsg().setColumnName(annotation.fieldShowName()).setErrMsg("值[" + strValue + "]不在允许范围内" + allowedStr);
      }
    }

    // 检查长度范围
    int length = strValue.length();
    if (length < annotation.min() || length > annotation.max()) {
      return new ExcelErrorMsg().setColumnName(annotation.fieldShowName()).setErrMsg("长度[" + length + "]不在范围[" + annotation.min() + "," + annotation.max() + "]内");
    }

    return null;
  }

  /**
   * 校验整数类型字段
   */
  private static ExcelErrorMsg checkObjectValueInt(Object fieldValue, CheckObjectFieldValueAnn annotation) {
    Integer intValue;
    try {
      intValue = (Integer) fieldValue;
    } catch (ClassCastException e) {
      return new ExcelErrorMsg().setColumnName(annotation.fieldShowName()).setErrMsg("字段值不是有效的整数类型");
    }

    // 检查是否在允许的值范围内
    int[] allowedValues = annotation.intValues();
    if (allowedValues != null && allowedValues.length > 0) {
      if (!contains(allowedValues, intValue)) {
        String allowedStr = Arrays.stream(allowedValues).boxed().map(String::valueOf).collect(Collectors.joining(",", "[", "]"));
        return new ExcelErrorMsg().setColumnName(annotation.fieldShowName()).setErrMsg("值[" + intValue + "]不在允许范围内" + allowedStr);
      }
    }

    // 检查数值范围
    if (intValue < annotation.min() || intValue > annotation.max()) {
      return new ExcelErrorMsg().setColumnName(annotation.fieldShowName()).setErrMsg("值[" + intValue + "]不在范围[" + annotation.min() + "," + annotation.max() + "]内");
    }

    return null;
  }

  /**
   * 校验长整数类型字段
   */
  private static ExcelErrorMsg checkObjectValueLong(Object fieldValue, CheckObjectFieldValueAnn annotation) {
    Long longValue;
    try {
      longValue = (Long) fieldValue;
    } catch (ClassCastException e) {
      return new ExcelErrorMsg().setColumnName(annotation.fieldShowName()).setErrMsg("字段值不是有效的长整数类型");
    }

    // 检查是否在允许的值范围内
    long[] allowedValues = annotation.longValues();
    if (allowedValues != null && allowedValues.length > 0) {
      if (!contains(allowedValues, longValue)) {
        String allowedStr = Arrays.stream(allowedValues).boxed().map(String::valueOf).collect(Collectors.joining(",", "[", "]"));
        return new ExcelErrorMsg().setColumnName(annotation.fieldShowName()).setErrMsg("值[" + longValue + "]不在允许范围内" + allowedStr);
      }
    }

    // 检查数值范围（注意：min和max是int类型，这里做长整数比较）
    if (longValue < annotation.min() || longValue > annotation.max()) {
      return new ExcelErrorMsg().setColumnName(annotation.fieldShowName())
          .setErrMsg("值[" + longValue + "]不在范围[" + annotation.min() + "," + annotation.max() + "]内");
    }

    return null;
  }
}
