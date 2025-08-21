package com.olivia.sdk.utils.conf;

import com.google.gson.*;
import java.lang.reflect.Type;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Long类型转String序列化配置类 提供Long类型到JSON字符串的自定义转换逻辑，解决大整数精度丢失问题
 */
@Setter
@Getter
@Accessors(chain = true)
public class LongToStringConf {

  /**
   * 创建默认的GsonBuilder配置 已注册Long类型转String的序列化器
   *
   * @return 配置好的GsonBuilder
   */
  public static GsonBuilder createDefaultGsonBuilder() {
    return new GsonBuilder().registerTypeAdapter(Long.class, new LongToStringSerializer()).registerTypeAdapter(long.class, new LongToStringSerializer());
  }

  /**
   * 自定义Long类型序列化器 将Long类型值序列化为JSON字符串，避免大整数在JSON中精度丢失
   */
  public static class LongToStringSerializer implements JsonSerializer<Long> {

    @Override
    public JsonElement serialize(Long src, Type typeOfSrc, JsonSerializationContext context) {
      if (src == null) {
        return JsonNull.INSTANCE;
      }
      if (src < 1_0000_0000_00L && src > -1_0000_0000_00L) {
        return new JsonPrimitive(src);
      }
      return new JsonPrimitive(src.toString());
    }
  }
}
