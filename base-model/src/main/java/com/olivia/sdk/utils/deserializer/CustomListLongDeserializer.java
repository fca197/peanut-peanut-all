package com.olivia.sdk.utils.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomListLongDeserializer extends JsonDeserializer<List<Long>> {

  @Override
  public List<Long> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    List<Long> result = new ArrayList<>();

    // 使用Jackson的TreeTraversingParser安全遍历数组元素
    try (JsonParser arrayParser = p.getCodec().treeAsTokens(p.getCodec().readTree(p))) {
      // 移动到数组开始标记
      if (arrayParser.nextToken() != JsonToken.START_ARRAY) {
        return result;
      }

      // 遍历数组元素
      while (arrayParser.nextToken() != JsonToken.END_ARRAY) {
        String value = arrayParser.getText();
        try {
          result.add(new BigInteger(value).longValue());
        } catch (NumberFormatException e) {
          // 记录错误或跳过无效值
          log.error("Error parsing list long value . value: {} error: {}", value, e.getMessage(), e);
        }
      }
    }

    return result;
  }
}