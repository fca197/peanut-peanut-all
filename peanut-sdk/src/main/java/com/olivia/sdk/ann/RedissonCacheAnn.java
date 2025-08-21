package com.olivia.sdk.ann;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * Redisson缓存注解，用于标记需要进行缓存处理的类或方法。
 * <p>
 * 支持通过SpEL表达式定义缓存键，配置缓存超时时间和时间单位， 实现方法返回结果的自动缓存。
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedissonCacheAnn {

  /**
   * 缓存组名称，用于对缓存进行分类管理。
   * <p>
   * 建议使用"业务:模块"格式（如"user:info"），为空时不使用分组前缀。
   *
   * @return 缓存组名称，默认空字符串
   */
  String group() default "";

  /**
   * 缓存键的SpEL表达式，用于动态生成缓存键。
   * <p>
   * 格式示例： - 单个参数：#userId - 对象字段：#dto.userId - 多个参数组合：#userId + ':' + #roleId
   *
   * @return SpEL表达式字符串，不能为空
   */
  String key();

  /**
   * 缓存超时时间单位。
   *
   * @return 时间单位枚举，默认秒(SECONDS)
   */
  TimeUnit unit() default TimeUnit.SECONDS;

  /**
   * 缓存超时时间，结合{@link #unit()}使用。
   *
   * @return 超时时间数值，默认3600（即1小时）
   */
  long ttl() default 3600L;
}
