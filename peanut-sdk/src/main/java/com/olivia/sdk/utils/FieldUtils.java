package com.olivia.sdk.utils;

import cn.hutool.core.util.ReflectUtil;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.sdk.ann.FieldExt;
import com.olivia.sdk.model.KVEntity;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 字段反射工具类，提供字段缓存、反射取值及注解解析功能 基于JDK21特性优化，增强反射操作的性能和安全性
 */
public final class FieldUtils {

  /**
   * 字段缓存，缓存键格式为"类名,字段名" 缓存有效期20分钟，最大缓存200个字段
   */
  private static final Cache<String, Field> FIELD_CACHE = CacheBuilder.newBuilder().expireAfterAccess(20, TimeUnit.MINUTES).maximumSize(200)
      .recordStats() // 记录缓存统计信息，便于性能分析
      .build();

  /**
   * 日志记录器
   */
  private static final Logger log = LoggerFactory.getLogger(FieldUtils.class);

  /**
   * 私有构造函数，防止工具类实例化
   */
  private FieldUtils() {
    throw new AssertionError("工具类不允许实例化");
  }

  /**
   * 获取指定类的指定字段（从缓存获取，不存在则反射获取并缓存）
   *
   * @param clazz     目标类
   * @param fieldName 字段名
   * @return 对应的Field对象
   * @throws IllegalArgumentException 如果类或字段名为null
   */
  public static Field getField(Class<?> clazz, String fieldName) {
    Objects.requireNonNull(clazz, "目标类不能为空");
    Objects.requireNonNull(fieldName, "字段名不能为空");

    String cacheKey = createCacheKey(clazz.getName(), fieldName);
    try {
      return FIELD_CACHE.get(cacheKey, getFieldCallable(clazz, fieldName));
    } catch (Exception e) {
      log.warn("获取字段失败 [类: {}, 字段: {}]", clazz.getName(), fieldName, e);
      return null;
    }
  }

  /**
   * 从对象中获取指定字段的值
   *
   * @param obj   目标对象
   * @param field 字段对象
   * @param <T>   值类型
   * @return 字段值，若对象或字段为null则返回null
   */
  @SuppressWarnings("unchecked")
  public static <T> T getFieldValue(Object obj, Field field) {
    if (Objects.isNull(obj) || Objects.isNull(field)) {
      return null;
    }
    try {
      Object value = ReflectUtil.getFieldValue(obj, field);
      return (T) value;
    } catch (Exception e) {
      log.warn("获取字段值失败 [对象: {}, 字段: {}]", obj.getClass().getName(), field.getName(), e);
      return null;
    }
  }

  /**
   * 从对象中获取指定名称字段的值
   *
   * @param obj       目标对象
   * @param fieldName 字段名
   * @param <T>       值类型
   * @return 字段值，若对象或字段不存在则返回null
   */
  public static <T> T getFieldValue(Object obj, String fieldName) {
    Objects.requireNonNull(obj, () -> "目标对象不能为空");
    Objects.requireNonNull(fieldName, () -> "字段名不能为空");

    Field field = getField(obj.getClass(), fieldName);
    return getFieldValue(obj, field);
  }

  /**
   * 从对象（或集合）中获取指定名称字段
   *
   * @param object    目标对象（可为集合）
   * @param fieldName 字段名
   * @return 对应的Field对象
   */
  public static Field getField(Object object, String fieldName) {
    Objects.requireNonNull(object, () -> "目标对象不能为空");
    Objects.requireNonNull(fieldName, () -> "字段名不能为空");

    Class<?> targetClass = getTargetClass(object);
    return getField(targetClass, fieldName);
  }

  /**
   * 获取类中带有FieldExt注解的字段映射关系
   *
   * @param clazz 目标类
   * @return 字段注解映射列表，键为注解.fieldName()，值为字段名
   */
  public static List<KVEntity> getFieldExtList(Class<?> clazz) {
    if (Objects.isNull(clazz)) {
      return List.of(); // JDK21优化：返回不可变空列表
    }

    return Arrays.stream(ReflectUtil.getFields(clazz)).map(field -> {
      FieldExt annotation = field.getAnnotation(FieldExt.class);
      if (Objects.isNull(annotation)) {
        return null;
      }
      return KVEntity.of(annotation.fieldName(), field.getName());
    }).filter(Objects::nonNull).toList(); // JDK21优化：返回不可变列表
  }

  /**
   * 创建缓存键
   */
  private static String createCacheKey(String className, String fieldName) {
    return className + "," + fieldName;
  }

  /**
   * 获取字段的Callable（用于缓存加载）
   */
  private static Callable<Field> getFieldCallable(Class<?> clazz, String fieldName) {
    return () -> {
      if (log.isDebugEnabled()) {
        log.debug("缓存未命中，反射获取字段 [类: {}, 字段: {}]", clazz.getName(), fieldName);
      }
      Field field = ReflectUtil.getField(clazz, fieldName);
      if (Objects.isNull(field)) {
        throw new NoSuchFieldException("字段不存在: " + clazz.getName() + "." + fieldName);
      }
      return field;
    };
  }

  /**
   * 获取目标类（处理集合类型）
   */
  private static Class<?> getTargetClass(Object object) {
    if (object instanceof Iterable<?> iterable) {
      // 处理集合类型，取第一个元素的类
      var iterator = iterable.iterator();

      if (iterator.hasNext()) {
        Object firstElement = iterator.next();
        return firstElement.getClass();
      }

      throw new IllegalArgumentException("集合为空，无法确定元素类型");
    }
    return object.getClass();
  }
}
