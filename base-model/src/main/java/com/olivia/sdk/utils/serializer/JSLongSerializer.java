package com.olivia.sdk.utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Objects;

public class JSLongSerializer extends JsonSerializer<Long> {

  public static JSLongSerializer instance = new JSLongSerializer();
  private final Long MAX = 9007199254740991L;
  private final Long MIN = -9007199254740991L;

  @Override
  public void serialize(Long value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    if (Objects.isNull(value)) {
      jsonGenerator.writeNull();
      return;
    }
    if (value < MIN || value > MAX) {
      jsonGenerator.writeString(String.valueOf(value));
    } else {
      jsonGenerator.writeNumber(value);
    }
  }
}
