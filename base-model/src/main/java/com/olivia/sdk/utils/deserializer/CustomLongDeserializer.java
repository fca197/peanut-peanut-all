package com.olivia.sdk.utils.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class CustomLongDeserializer extends JsonDeserializer<Long> {

  @Override
  public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    String value = p.readValueAs(String.class);
    try {
      if (StringUtils.isEmpty(value)) {
        return null;
      }
      // 先尝试作为普通Long处理
      return Long.parseLong(value);
    } catch (NumberFormatException e) {
      // 超出范围时，使用BigInteger处理
      log.error("Error converting long value to Long  value {} message: {}", value, e.getMessage(), e);
      return new BigInteger(value).longValue();
    }
  }
}
