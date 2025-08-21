package com.olivia.sdk.timer;

import com.olivia.sdk.utils.RunUtils;
import com.olivia.sdk.utils.model.AsyncRunAndTry;
import jakarta.annotation.PreDestroy;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TimerUtils {

  // 静态单例 Timer 对象
  public static final Timer TIMER = new Timer();

  /**
   * 按固定延迟和周期调度任务
   *
   * @param key    任务的唯一标识
   * @param task   要执行的任务
   * @param delay  延迟执行的时间（毫秒）
   * @param period 任务执行的周期（毫秒）
   * @param logLog 是否记录日志
   */
  public static void schedule(String key, Runnable task, long delay, long period, boolean logLog) {
    TIMER.schedule(createTimerTask(key, task, logLog), delay, period);
  }

  /**
   * 按固定延迟和周期调度任务，默认记录日志
   *
   * @param key    任务的唯一标识
   * @param task   要执行的任务
   * @param delay  延迟执行的时间（毫秒）
   * @param period 任务执行的周期（毫秒）
   */
  public static void schedule(String key, Runnable task, long delay, long period) {
    schedule(key, task, delay, period, true);
  }

  /**
   * 按固定延迟调度一次性任务
   *
   * @param key    任务的唯一标识
   * @param task   要执行的任务
   * @param delay  延迟执行的时间（毫秒）
   * @param logLog 是否记录日志
   */
  public static void schedule(String key, Runnable task, long delay, boolean logLog) {
    TIMER.schedule(createTimerTask(key, task, logLog), delay);
  }

  /**
   * 按固定延迟调度一次性任务，默认记录日志
   *
   * @param key   任务的唯一标识
   * @param task  要执行的任务
   * @param delay 延迟执行的时间（毫秒）
   */
  public static void schedule(String key, Runnable task, long delay) {
    schedule(key, task, delay, true);
  }

  /**
   * 创建 TimerTask 对象
   *
   * @param key    任务的唯一标识
   * @param task   要执行的任务
   * @param logLog 是否记录日志
   * @return TimerTask 对象
   */
  private static TimerTask createTimerTask(String key, Runnable task, boolean logLog) {
    return new TimerTask() {
      @Override
      public void run() {
        RunUtils.run(key, task);
      }
    };
  }

  /**
   * 取消所有调度任务并关闭 Timer
   */
  public static void cancel() {
    TIMER.cancel();
  }

  @PreDestroy
  public static void destroy() {
    log.info("destroy TIMER.purge() :{}", TIMER.purge());
  }

  public static void schedule(AsyncRunAndTry req) {
    if (req == null) {
      log.info("req is null return");
      return;
    }
    log.info("AsyncRunAndTry key :{} getCurrentExecIndex: {} getMaxLoopCount :{}", req.getKey(), req.getCurrentExecIndex(), req.getMaxRetryCount());
    if (req.getMaxRetryCount() < req.getCurrentExecIndex()) {
      if (Objects.nonNull(req.getErrorRunnable())) {
        RunUtils.asyncRun(req.getKey(), req.getErrorRunnable());
      }
      return;
    }

    long nextTime = Math.round(req.getCurrentExecIndex() * req.getIntervalTimeMill() * req.getMultipleTime());
    log.info("key {} 下次执行间隔 nextTime {}", req.getKey(), nextTime);
    TimerUtils.schedule(req.getKey(), req, nextTime, false);
  }
}