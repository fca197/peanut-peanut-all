package com.olivia.sdk.ann;

import java.lang.annotation.*;

/**
 * Redisson分布式锁注解，用于标记需要加分布式锁的类或方法。
 * <p>
 * 支持通过SpEL表达式定义锁键，配置锁超时时间、等待策略等， 实现分布式环境下的并发控制。
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedissonLockAnn {

  /**
   * 锁的前缀，用于区分不同业务类型的锁。
   * <p>
   * 建议使用"业务:模块:lock:"格式（如"order:payment:lock:"）， 最终锁键将格式化为"前缀+业务标识+业务ID"。
   *
   * @return 锁前缀字符串，默认"biz:lock:"
   */
  String lockPrefix() default "biz:lock";

  /**
   * 锁的业务标识，用于在锁键中区分不同业务场景。
   * <p>
   * 与{@link #lockPrefix()}和{@link #keyExpression()}组合生成完整锁键。
   *
   * @return 业务标识字符串，默认"biz"
   */
  String lockBizKeyFlag() default "biz";

  /**
   * 业务ID的SpEL表达式，用于动态生成锁的唯一标识部分。
   * <p>
   * 格式示例： - 单个参数：#orderId - 对象字段：#orderDTO.id - 多个参数组合：#userId + ':' + #orderId
   *
   * @return SpEL表达式字符串，默认空字符串
   */
  String keyExpression() default "";

  /**
   * 锁的超时时间，单位为毫秒。
   * <p>
   * 超过此时间后自动释放锁，防止死锁。
   *
   * @return 超时时间（毫秒），默认30000L（即30秒）
   */
  long lockTimeOut() default 30000L;

  /**
   * 是否等待获取锁直到成功或超时。
   * <p>
   * true表示会等待锁释放，false表示获取不到锁时立即返回失败。
   *
   * @return 是否等待，默认true
   */
  boolean isWait() default true;

  /**
   * 业务处理完成后是否删除锁键。
   * <p>
   * true表示业务完成后主动删除锁，false表示等待超时自动释放。
   *
   * @return 是否主动删除，默认true
   */
  boolean afterDeleteKey() default true;
}
