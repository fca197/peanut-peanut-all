package com.olivia.sdk.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.olivia.sdk.utils.deserializer.CustomLongDeserializer;
import com.olivia.sdk.utils.serializer.JSLongSerializer;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import lombok.SneakyThrows;

public class JSON {

  private static final ObjectMapper mapper = new ObjectMapper();

  static {
    AtomicLong index = new AtomicLong(0);
//    ObjectMapper mapper = new ObjectMapper();
    // 配置日期格式化
    mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    // 注册Java 8日期时间模块
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    // 创建 JavaTimeModule
    // 定义日期时间格式
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    // 注册 LocalDateTime 序列化器
    javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter));
    // 注册 LocalDateTime 反序列化器
    javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));

    mapper.registerModule(javaTimeModule);
    // 禁用将日期序列化为时间戳
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

    // 创建一个 SimpleModule 并注册 ToStringSerializer 用于 long 类型
    SimpleModule module = new SimpleModule();
// 构建List<Long>的JavaType
    JavaType type = TypeFactory.defaultInstance().constructCollectionType(List.class, Long.class);

// 构建通配符List<?>类型
    JavaType wildCardType = TypeFactory.defaultInstance()
        .constructCollectionType(List.class, Long.class);

// 注册反序列化器

    module.addSerializer(Long.class, JSLongSerializer.instance)
        .addSerializer(long.class, JSLongSerializer.instance)
        .addDeserializer(Long.class, new CustomLongDeserializer())
        .addDeserializer(long.class, new CustomLongDeserializer())
        .addSerializer(BigDecimal.class, ToStringSerializer.instance);

//    module.addDeserializer(type, new CustomListLongDeserializer());
    mapper.getSerializerProvider().setNullKeySerializer(new JsonSerializer<>() {
      @Override
      public void serialize(Object o, JsonGenerator jsonGenerator,
          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("NULL_KEY_" + index.incrementAndGet());
      }
    });
    mapper.registerModules(List.of(module, javaTimeModule));

  }

  public static ObjectMapper getMapper() {
    return JSON.mapper;
  }

  @SneakyThrows
  public static <T> T readValue(String content, Class<T> valueType) {
    if (Objects.isNull(content) || content.trim().isEmpty()) {
      return null;
    }
    return mapper.readValue(content, valueType);
  }

  @SneakyThrows
  public static <T> T readValue(String content) {
    if (Objects.isNull(content) || content.trim().isEmpty()) {
      return null;
    }
    return mapper.readValue(content, new TypeReference<>() {
    });
  }

  @SneakyThrows
  public static <T> T readValue(String content, TypeReference<T> typeReference) {
    return mapper.readValue(content, typeReference);
  }


  @SneakyThrows
  public static String toJSONString(Object object) {
    return mapper.writeValueAsString(object);
  }

  @SneakyThrows
  public static <T> List<T> readList(String content, Class<T> clazz) {
    if (Objects.isNull(content) || content.trim().isEmpty()) {
      return List.of();
    }
    return mapper.readValue(content,
        mapper.getTypeFactory().constructCollectionType(List.class, clazz));
  }


}
