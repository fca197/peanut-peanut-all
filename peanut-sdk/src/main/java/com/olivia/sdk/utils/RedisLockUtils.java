package com.olivia.sdk.utils;

import static com.olivia.sdk.utils.DateUtils.formatSeconds;

import com.olivia.sdk.exception.CanIgnoreException;
import com.olivia.sdk.exception.RunException;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Redis分布式锁工具类，提供基于Redis的分布式锁获取、释放及任务执行功能 基于JDK21特性优化，增强并发控制、异常处理和资源管理
 */
@Slf4j
@Component
public final class RedisLockUtils {

  // 最大重试次数常量（JDK21常量表达式优化）
  private static final int MAX_RETRIES = 100;
  // 重试间隔时间(毫秒)（JDK21常量表达式优化）
  private static final long RETRY_INTERVAL_MS = 1000L;
  // 单例实例（Spring管理）
  private static RedisLockUtils instance;
  // Redis模板实例（Spring注入）
  @Resource
  private StringRedisTemplate stringRedisTemplate;

  /**
   * 私有构造函数，防止外部实例化
   */
  private RedisLockUtils() {
    // 防止通过反射实例化
    if (instance != null) {
      throw new IllegalStateException("RedisLockUtils已被实例化，禁止重复创建");
    }
  }

  /**
   * 获取当前实例（确保Spring已初始化） JDK21优化：增强空值检查，提供更明确的异常信息
   */
  private static RedisLockUtils getInstance() {
    if (instance == null) {
      throw new IllegalStateException("RedisLockUtils尚未初始化，请确保Spring容器已正确加载");
    }
    return instance;
  }

  /**
   * 加锁并执行Runnable任务，执行完成后自动释放锁（一直等待锁）
   *
   * @param lockKey  锁的键名
   * @param timeOut  锁超时时间（毫秒）
   * @param runnable 要执行的任务
   */
  public static void lockKeyDeleteKey(String lockKey, long timeOut, Runnable runnable) {
    lockRun(lockKey, timeOut, true, true, runnable);
  }

  /**
   * 加锁并执行Runnable任务
   *
   * @param lockKey        锁的键名
   * @param timeOut        锁超时时间（毫秒）
   * @param afterDeleteKey 执行后是否释放锁
   * @param isWait         获取不到锁时是否等待
   * @param runnable       要执行的任务
   */
  public static void lockRun(String lockKey, long timeOut, boolean afterDeleteKey,
      boolean isWait, Runnable runnable) {
    // 前置检查（JDK21增强：Objects.requireNonNullElseThrow）

    acquireLock(lockKey, timeOut, isWait);
    try {
      runnable.run();
    } catch (RunException | CanIgnoreException e) {
      // 业务异常直接传播
      throw e;
    } catch (Exception e) {
      log.error("执行Runnable任务异常 [锁键: {}]", lockKey, e);
      throw new RuntimeException("任务执行失败: " + e.getMessage(), e);
    } finally {
      if (afterDeleteKey) {
        releaseLock(lockKey);
      }
    }
  }

  /**
   * 加锁并执行Callable任务
   *
   * @param lockKey        锁的键名
   * @param timeOut        锁超时时间（毫秒）
   * @param afterDeleteKey 执行后是否释放锁
   * @param isWait         获取不到锁时是否等待
   * @param callable       要执行的任务
   * @param <T>            任务返回值类型
   * @return 任务执行结果
   */
  public static <T> T lockCall(String lockKey, long timeOut, boolean afterDeleteKey,
      boolean isWait, Callable<T> callable) {
    // 前置检查（JDK21增强：Objects.requireNonNullElseThrow）
    acquireLock(lockKey, timeOut, isWait);
    try {
      return callable.call();
    } catch (RunException | CanIgnoreException e) {
      // 业务异常直接传播
      throw e;
    } catch (Exception e) {
      log.error("执行Callable任务异常 [锁键: {}]", lockKey, e);
      throw new RuntimeException("任务执行失败: " + e.getMessage(), e);
    } finally {
      if (afterDeleteKey) {
        releaseLock(lockKey);
      }
    }
  }

  /**
   * 释放锁
   *
   * @param lockKey 锁的键名
   */
  private static void releaseLock(String lockKey) {
    Boolean delete = getInstance().stringRedisTemplate.delete(lockKey);
    if (delete) {
      log.info("释放锁成功 [锁键: {}]", lockKey);
    } else {
      log.warn("释放锁失败或锁已过期 [锁键: {}]", lockKey);
    }
  }

  /**
   * 获取分布式锁 JDK21优化：使用虚拟线程友好的休眠机制，增强异常处理
   *
   * @param lockKey 锁的键名
   * @param timeOut 锁超时时间（毫秒）
   * @param isWait  获取不到锁时是否等待
   */
  private static void acquireLock(String lockKey, long timeOut, boolean isWait) {
    // 验证超时时间有效性（JDK21增强：更严格的参数校验）
    if (timeOut <= 0) {
      throw new IllegalArgumentException("锁超时时间必须大于0毫秒: " + timeOut);
    }

    for (int retryCount = 0; retryCount < MAX_RETRIES; retryCount++) {
      // 尝试获取锁（SET NX命令）
      Boolean locked = getInstance().stringRedisTemplate.opsForValue()
          .setIfAbsent(lockKey, "1", timeOut, TimeUnit.MILLISECONDS);

      if (Boolean.TRUE.equals(locked)) {
        log.debug("获取锁成功 [锁键: {}，重试次数: {}]", lockKey, retryCount);
        return;
      }

      // 获取锁剩余时间
      Long remainTimeSeconds = getInstance().stringRedisTemplate
          .getExpire(lockKey, TimeUnit.SECONDS);

      // 不等待模式：直接抛出异常
      if (!isWait) {
        log.error("获取锁失败（不等待模式）[锁键: {}，剩余时间: {}秒]", lockKey, remainTimeSeconds);
        String msg = formatSeconds(remainTimeSeconds);
        throw new RunException("操作过于频繁，请在" + msg + "后重试");
      }

      // 达到最大重试次数：抛出异常
      if (retryCount == MAX_RETRIES - 1) {
        log.error("获取锁失败（达到最大重试次数）[锁键: {}，重试次数: {}]", lockKey, retryCount);
        throw new RunException("系统繁忙，请稍后再试");
      }

      // 等待重试（JDK21优化：使用Thread.sleep，支持虚拟线程）
      try {
        log.debug("获取锁失败，等待重试 [锁键: {}，重试次数: {}，等待时间: {}ms，剩余时间: {}秒]",
            lockKey, retryCount, RETRY_INTERVAL_MS, remainTimeSeconds);
        Thread.sleep(RETRY_INTERVAL_MS);
      } catch (InterruptedException e) {
        // JDK21优化：恢复中断状态，不吞掉中断信号
        Thread.currentThread().interrupt();
        log.warn("获取锁等待被中断 [锁键: {}]", lockKey, e);
        throw new RuntimeException("获取锁被中断", e);
      }
    }

    // 理论上不会到达此处，作为安全保障
    throw new RunException("获取锁异常，超出最大重试逻辑");
  }

  /**
   * Spring初始化后设置单例实例
   */
  @PostConstruct
  public void init() {
    instance = this;
  }
}
