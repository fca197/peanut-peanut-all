package com.olivia.sdk.utils.model;

import cn.hutool.core.collection.CollUtil;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.RunUtils;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

/**
 * 异步任务执行与重试工具类 支持带重试机制的异步任务执行，包含异常忽略、指数退避等功能
 */
@Setter
@Getter
@Slf4j
@Accessors(chain = true)
public class AsyncRunAndTry implements Runnable {

  /**
   * 任务标识，用于日志追踪
   */
  private String key;

  /**
   * MDC上下文，用于日志追踪
   */
  @Setter(AccessLevel.NONE)
  private Map<String, String> contextMap;

  /**
   * 核心业务任务
   */
  private Runnable bizRunnable;

  /**
   * 错误处理任务
   */
  private Runnable errorRunnable;

  /**
   * 当前执行次数
   */
  @Setter(AccessLevel.NONE)
  private int currentExecIndex = 0;

  /**
   * 最大重试次数
   */
  private int maxRetryCount = 3;

  /**
   * 初始间隔时间(毫秒)
   */
  private long intervalTimeMill = 1000;

  /**
   * 间隔时间倍数(用于指数退避)
   */
  private double multipleTime = 1.5;

  /**
   * 最终执行结果
   */
  @Setter(AccessLevel.NONE)
  private Boolean finalExecResult;

  /**
   * 忽略的异常类型列表
   */
  private List<Class<? extends Exception>> ignoreExceptionList;

  /**
   * 构造函数，初始化MDC上下文
   */
  public AsyncRunAndTry() {
    this.contextMap = MDC.getCopyOfContextMap();
  }

  /**
   * 设置初始间隔时间
   *
   * @param intervalTimeMill 间隔时间(毫秒)
   * @return 当前对象
   */
  public AsyncRunAndTry setIntervalTimeMill(long intervalTimeMill) {
    $.assertTrueCanIgnoreException(intervalTimeMill > 0, "任务间隔不能小于或等于0");
    this.intervalTimeMill = intervalTimeMill;
    return this;
  }

  /**
   * 设置间隔时间倍数
   *
   * @param multipleTime 倍数(大于等于1.0)
   * @return 当前对象
   */
  public AsyncRunAndTry setMultipleTime(double multipleTime) {
    $.assertTrueCanIgnoreException(multipleTime >= 1.0, "时间倍数不能小于1.0");
    this.multipleTime = multipleTime;
    return this;
  }

  @Override
  public void run() {
    // 恢复MDC上下文
    Map<String, String> originalContext = MDC.getCopyOfContextMap();
    if (contextMap != null) {
      MDC.setContextMap(contextMap);
    }

    try {
      currentExecIndex++;
      log.debug("任务执行 - key: {}, 第{}次执行", key, currentExecIndex);

      // 执行核心业务任务
      if (bizRunnable == null) {
        throw new IllegalStateException("业务任务(bizRunnable)未设置");
      }
      bizRunnable.run();

      // 执行成功处理
      log.info("任务执行成功 - key: {}, 总执行次数: {}", key, currentExecIndex);
      finalExecResult = Boolean.TRUE;

    } catch (Exception e) {
      log.error("任务执行异常 - key: {}, 第{}次执行失败: {}", key, currentExecIndex, e.getMessage(), e);

      // 执行错误处理任务
      if (errorRunnable != null) {
        try {
          errorRunnable.run();
        } catch (Exception ex) {
          log.warn("错误处理任务执行异常 - key: {}", key, ex);
        }
      }

      // 判断是否需要重试
      if (shouldRetry(e)) {
        scheduleNextRetry();
      } else {
        log.error("任务最终执行失败 - key: {}, 总执行次数: {}", key, currentExecIndex);
        finalExecResult = Boolean.FALSE;
      }
    } finally {
      // 恢复原始MDC上下文
      if (originalContext != null) {
        MDC.setContextMap(originalContext);
      } else {
        MDC.clear();
      }
    }
  }

  /**
   * 判断是否需要重试
   *
   * @param e 捕获的异常
   * @return 是否需要重试
   */
  private boolean shouldRetry(Exception e) {
    // 如果是忽略的异常类型，不重试
    if (shouldIgnore(e)) {
      log.warn("任务执行忽略异常 - key: {}, 异常类型: {}", key, e.getClass().getSimpleName());
      return false;
    }

    // 如果已达到最大重试次数，不重试
    if (currentExecIndex >= maxRetryCount) {
      log.warn("任务已达到最大重试次数 - key: {}, 最大次数: {}", key, maxRetryCount);
      return false;
    }

    return true;
  }

  /**
   * 计算下一次重试的间隔时间
   *
   * @return 间隔时间(毫秒)
   */
  private long calculateNextInterval() {
    // 指数退避算法: 初始间隔 * (倍数 ^ (当前次数 - 1))
    return (long) (intervalTimeMill * Math.pow(multipleTime, currentExecIndex - 1));
  }

  /**
   * 调度下一次重试
   */
  private void scheduleNextRetry() {
    long nextInterval = calculateNextInterval();
    log.info("任务将进行下一次重试 - key: {}, 第{}次, 间隔: {}ms", key, currentExecIndex + 1, nextInterval);

    RunUtils.asyncRunAndTry(this);
  }

  /**
   * 判断是否为需要忽略的异常
   *
   * @param e 捕获的异常
   * @return 是否忽略
   */
  public boolean shouldIgnore(Exception e) {
    if (CollUtil.isEmpty(ignoreExceptionList) || Objects.isNull(e)) {
      return false;
    }

    Class<? extends Exception> eClass = e.getClass();
    for (Class<? extends Exception> ignoredType : ignoreExceptionList) {
      if (ignoredType.isAssignableFrom(eClass)) {
        return true;
      }
    }
    return false;
  }
}
