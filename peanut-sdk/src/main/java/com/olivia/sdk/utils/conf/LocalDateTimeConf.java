package com.olivia.sdk.utils.conf;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * LocalDateTime JSON序列化/反序列化配置类 提供Java 8 LocalDateTime类型与JSON字符串之间的自定义转换逻辑
 */
@Setter
@Getter
@Accessors(chain = true)
public class LocalDateTimeConf {

  /**
   * 默认日期时间格式
   */
  public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
  /**
   * 默认日期时间格式化器
   */
  public static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_PATTERN);

  /**
   * 日期时间格式
   */
  private String pattern = DEFAULT_DATETIME_PATTERN;
  /**
   * 日期时间格式化器
   */
  private DateTimeFormatter formatter = DEFAULT_FORMATTER;

  /**
   * 构造函数：使用默认格式
   */
  public LocalDateTimeConf() {
    this(DEFAULT_DATETIME_PATTERN);
  }

  /**
   * 构造函数：自定义日期时间格式
   *
   * @param pattern 日期时间格式字符串
   */
  public LocalDateTimeConf(String pattern) {
    setPattern(pattern);
  }

  /**
   * 创建默认的GsonBuilder配置
   *
   * @return 配置好LocalDateTime处理的GsonBuilder
   */
  public static GsonBuilder createDefaultGsonBuilder() {
    return new GsonBuilder()
        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
  }

  /**
   * 设置日期时间格式，同时更新格式化器
   *
   * @param pattern 日期时间格式字符串
   */
  public void setPattern(String pattern) {
    this.pattern = pattern;
    this.formatter = DateTimeFormatter.ofPattern(pattern);
  }

  /**
   * 根据当前配置创建GsonBuilder
   *
   * @return 配置好的GsonBuilder
   */
  public GsonBuilder createGsonBuilder() {
    return new GsonBuilder()
        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer(formatter))
        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));
  }

  /**
   * LocalDateTime 序列化器
   */
  public static class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {

    private final DateTimeFormatter formatter;

    /**
     * 使用默认格式化器
     */
    public LocalDateTimeSerializer() {
      this.formatter = DEFAULT_FORMATTER;
    }

    /**
     * 使用自定义格式化器
     *
     * @param formatter 日期时间格式化器
     */
    public LocalDateTimeSerializer(DateTimeFormatter formatter) {
      this.formatter = formatter;
    }

    @Override
    public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
      if (src == null) {
        return JsonNull.INSTANCE;
      }
      return new JsonPrimitive(formatter.format(src));
    }
  }

  /**
   * LocalDateTime 反序列化器
   */
  public static class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {

    private final DateTimeFormatter formatter;

    /**
     * 使用默认格式化器
     */
    public LocalDateTimeDeserializer() {
      this.formatter = DEFAULT_FORMATTER;
    }

    /**
     * 使用自定义格式化器
     *
     * @param formatter 日期时间格式化器
     */
    public LocalDateTimeDeserializer(DateTimeFormatter formatter) {
      this.formatter = formatter;
    }

    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      try {
        if (json == null || json.isJsonNull() || json.getAsString().trim().isEmpty()) {
          return null;
        }
        return LocalDateTime.parse(json.getAsString().trim(), formatter);
      } catch (DateTimeParseException e) {
        throw new JsonParseException("无法解析LocalDateTime字符串: " + json.getAsString(), e);
      }
    }
  }
}
