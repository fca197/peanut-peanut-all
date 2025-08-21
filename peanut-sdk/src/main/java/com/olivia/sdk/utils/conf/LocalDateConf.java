package com.olivia.sdk.utils.conf;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * LocalDate JSON序列化/反序列化配置类 提供Java 8 LocalDate类型与JSON字符串之间的自定义转换逻辑
 */
@Setter
@Getter
@Accessors(chain = true)
public class LocalDateConf {

  /**
   * 默认日期格式
   */
  public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
  /**
   * 默认日期格式化器
   */
  public static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN);

  /**
   * 日期格式
   */
  private String pattern = DEFAULT_DATE_PATTERN;
  /**
   * 日期格式化器
   */
  private DateTimeFormatter formatter = DEFAULT_FORMATTER;

  /**
   * 构造函数：使用默认格式
   */
  public LocalDateConf() {
    this(DEFAULT_DATE_PATTERN);
  }

  /**
   * 构造函数：自定义日期格式
   *
   * @param pattern 日期格式字符串
   */
  public LocalDateConf(String pattern) {
    setPattern(pattern);
  }

  /**
   * 创建默认的GsonBuilder配置
   *
   * @return 配置好LocalDate处理的GsonBuilder
   */
  public static GsonBuilder createDefaultGsonBuilder() {
    return new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateSerializer()).registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
  }

  /**
   * 设置日期格式，同时更新格式化器
   *
   * @param pattern 日期格式字符串
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
    return new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateSerializer(formatter))
        .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer(formatter));
  }

  /**
   * LocalDate 序列化器
   */
  public static class LocalDateSerializer implements JsonSerializer<LocalDate> {

    private final DateTimeFormatter formatter;

    /**
     * 使用默认格式化器
     */
    public LocalDateSerializer() {
      this.formatter = DEFAULT_FORMATTER;
    }

    /**
     * 使用自定义格式化器
     *
     * @param formatter 日期格式化器
     */
    public LocalDateSerializer(DateTimeFormatter formatter) {
      this.formatter = formatter;
    }

    @Override
    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
      if (src == null) {
        return JsonNull.INSTANCE;
      }
      return new JsonPrimitive(formatter.format(src));
    }
  }

  /**
   * LocalDate 反序列化器
   */
  public static class LocalDateDeserializer implements JsonDeserializer<LocalDate> {

    private final DateTimeFormatter formatter;

    /**
     * 使用默认格式化器
     */
    public LocalDateDeserializer() {
      this.formatter = DEFAULT_FORMATTER;
    }

    /**
     * 使用自定义格式化器
     *
     * @param formatter 日期格式化器
     */
    public LocalDateDeserializer(DateTimeFormatter formatter) {
      this.formatter = formatter;
    }

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
      try {
        if (json == null || json.isJsonNull() || json.getAsString().trim().isEmpty()) {
          return null;
        }
        return LocalDate.parse(json.getAsString().trim(), formatter);
      } catch (DateTimeParseException e) {
        throw new JsonParseException("无法解析LocalDate字符串: " + json.getAsString(), e);
      }
    }
  }
}
