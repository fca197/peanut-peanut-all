package com.olivia.sdk.utils.conf;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Gson日期序列化/反序列化配置类 提供日期类型与JSON字符串之间的自定义转换逻辑
 */
@Setter
@Getter
@Accessors(chain = true)
public class GsonDateConf {

  /**
   * 默认日期时间格式
   */
  public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
  /**
   * 默认日期格式
   */
  public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
  /**
   * 默认时区
   */
  public static final TimeZone DEFAULT_TIME_ZONE = TimeZone.getTimeZone("GMT+8");

  /**
   * 日期格式
   */
  private String pattern = DEFAULT_DATETIME_PATTERN;
  /**
   * 时区
   */
  private TimeZone timeZone = DEFAULT_TIME_ZONE;

  /**
   * 创建默认的GsonBuilder配置
   *
   * @return 配置好日期处理的GsonBuilder
   */
  public static GsonBuilder createDefaultGsonBuilder() {
    return new GsonBuilder().registerTypeAdapter(Date.class, new GsonDateSerializer(DEFAULT_DATETIME_PATTERN))
        .registerTypeAdapter(Date.class, new GsonDateDeserializer(DEFAULT_DATETIME_PATTERN)).setDateFormat(DEFAULT_DATETIME_PATTERN);
  }

  /**
   * 根据当前配置创建GsonBuilder
   *
   * @return 配置好的GsonBuilder
   */
  public GsonBuilder createGsonBuilder() {
    return new GsonBuilder().registerTypeAdapter(Date.class, new GsonDateSerializer(pattern)).registerTypeAdapter(Date.class, new GsonDateDeserializer(pattern))
        .setDateFormat(pattern);
  }

  /**
   * 自定义Date类型序列化器
   */
  public static class GsonDateSerializer implements JsonSerializer<Date> {

    private final SimpleDateFormat dateFormat;

    public GsonDateSerializer(String pattern) {
      this.dateFormat = new SimpleDateFormat(pattern);
      this.dateFormat.setTimeZone(DEFAULT_TIME_ZONE);
    }

    public GsonDateSerializer(String pattern, TimeZone timeZone) {
      this.dateFormat = new SimpleDateFormat(pattern);
      this.dateFormat.setTimeZone(timeZone != null ? timeZone : DEFAULT_TIME_ZONE);
    }

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
      if (src == null) {
        return JsonNull.INSTANCE;
      }
      return new JsonPrimitive(dateFormat.format(src));
    }
  }

  /**
   * 自定义Date类型反序列化器
   */
  public static class GsonDateDeserializer implements JsonDeserializer<Date> {

    private final SimpleDateFormat dateFormat;

    public GsonDateDeserializer(String pattern) {
      this.dateFormat = new SimpleDateFormat(pattern);
      this.dateFormat.setTimeZone(DEFAULT_TIME_ZONE);
    }

    public GsonDateDeserializer(String pattern, TimeZone timeZone) {
      this.dateFormat = new SimpleDateFormat(pattern);
      this.dateFormat.setTimeZone(timeZone != null ? timeZone : DEFAULT_TIME_ZONE);
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
      try {
        if (json == null || json.isJsonNull()) {
          return null;
        }
        return dateFormat.parse(json.getAsString());
      } catch (ParseException e) {
        throw new JsonParseException("无法解析日期字符串: " + json.getAsString(), e);
      }
    }
  }
}
