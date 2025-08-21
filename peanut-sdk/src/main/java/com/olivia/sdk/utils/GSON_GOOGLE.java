package com.olivia.sdk.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.olivia.sdk.utils.conf.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Gson JSON处理工具类，提供对象与JSON字符串的序列化和反序列化功能 支持日期、时间、长整型等特殊类型的自定义处理，基于JDK21特性优化
 */
public final class GSON_GOOGLE {

  /**
   * 默认Gson实例，预配置常用类型适配器
   */
  private static final Gson DEFAULT_GSON = getGsonBuilder().create();

  /**
   * 私有构造函数，防止工具类实例化
   */
  private GSON_GOOGLE() {
    throw new AssertionError("工具类不允许实例化");
  }

  /**
   * 获取预配置的GsonBuilder 已注册常用类型适配器，包括日期、LocalDate、LocalDateTime和Long类型
   *
   * @return 配置好的GsonBuilder实例
   */
  public static GsonBuilder getGsonBuilder() {
    return new GsonBuilder()
        // 日期类型序列化配置
        .registerTypeAdapter(Date.class,
            new GsonDateConf.GsonDateSerializer("yyyy-MM-dd HH:mm:ss"))
        // 本地日期类型序列化与反序列化
        .registerTypeAdapter(LocalDate.class, new LocalDateConf.LocalDateSerializer())
        .registerTypeAdapter(LocalDate.class, new LocalDateConf.LocalDateDeserializer())
        // 本地日期时间类型序列化与反序列化
        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeConf.LocalDateTimeSerializer())
        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeConf.LocalDateTimeDeserializer())
        // 长整型转字符串序列化
        .registerTypeAdapter(Long.class, new LongToStringConf.LongToStringSerializer())
        .registerTypeAdapter(long.class, new LongToStringConf.LongToStringSerializer())
        // 字段命名策略：下划线命名法
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
  }

  /**
   * 获取默认Gson实例
   *
   * @return 预配置的Gson实例
   */
  public static Gson getGson() {
    return DEFAULT_GSON;
  }

  /**
   * 将JSON字符串反序列化为指定类型的对象
   *
   * @param content   JSON字符串
   * @param valueType 目标对象类型
   * @param <T>       目标对象类型泛型
   * @return 反序列化后的对象，若输入为空则返回null
   */
  public static <T> T readValue(String content, Class<T> valueType) {
    // JDK21优化：使用String.isBlank()替代手动trim判断
    if (content == null || content.isBlank()) {
      return null;
    }
    Objects.requireNonNull(valueType, () -> "目标类型不能为空");
    return DEFAULT_GSON.fromJson(content, valueType);
  }

  /**
   * 将JSON字符串反序列化为TypeReference指定的复杂类型对象
   *
   * @param content       JSON字符串
   * @param typeReference 复杂类型引用
   * @param <T>           目标对象类型泛型
   * @return 反序列化后的对象
   */
  public static <T> T readValue(String content, TypeReference<T> typeReference) {
    if (content == null || content.isBlank()) {
      return null;
    }
    Objects.requireNonNull(typeReference, () -> "类型引用不能为空");
    return DEFAULT_GSON.fromJson(content, typeReference.getType());
  }

  /**
   * 将对象序列化为JSON字符串
   *
   * @param object 要序列化的对象
   * @return JSON字符串，若对象为null则返回"null"
   */
  public static String toJSONString(Object object) {
    return DEFAULT_GSON.toJson(object);
  }

  /**
   * 将JSON字符串反序列化为指定类型的列表
   *
   * @param content JSON字符串
   * @param clazz   列表元素类型
   * @param <T>     列表元素类型泛型
   * @return 元素列表，若输入为空则返回空列表
   */
  public static <T> List<T> readList(String content, Class<T> clazz) {
    if (content == null || content.isBlank()) {
      return List.of(); // JDK21优化：返回不可变空列表
    }
    Objects.requireNonNull(clazz, () -> "列表元素类型不能为空");

    // 使用TypeToken处理泛型列表
    TypeToken<List<T>> typeToken = new TypeToken<List<T>>() {
    };
    return DEFAULT_GSON.fromJson(content, typeToken.getType());
  }
}
