package com.olivia.sdk.utils.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 日间时间信息模型类 用于记录和计算某一天的工作时间信息
 */
@Setter
@Getter
@Accessors(chain = true)
public class DayTimeInfo {

  /**
   * 当前日期
   */
  private LocalDate currentDate;

  /**
   * 开始时间
   */
  private LocalTime beginTime;

  /**
   * 结束时间
   */
  private LocalTime endTime;

  /**
   * 当日工作秒数
   */
  private Long dayWorkSecond;

  /**
   * 累计工作秒数
   */
  private Long workSum;

  /**
   * 计算当日工作时长（秒） 基于开始时间和结束时间自动计算
   *
   * @return 当日工作秒数，开始或结束时间为null时返回null
   */
  public Long calculateDayWorkSecond() {
    if (beginTime == null || endTime == null) {
      return null;
    }

    // 确保结束时间不早于开始时间
    if (endTime.isBefore(beginTime)) {
      throw new IllegalArgumentException("结束时间不能早于开始时间");
    }

    return Duration.between(beginTime, endTime).getSeconds();
  }

  /**
   * 自动计算并设置当日工作秒数
   *
   * @return 当前对象，支持链式调用
   */
  public DayTimeInfo computeDayWorkSecond() {
    this.dayWorkSecond = calculateDayWorkSecond();
    return this;
  }

  /**
   * 验证时间的有效性
   *
   * @return 有效返回true，否则返回false
   */
  public boolean isValid() {
    if (currentDate == null) {
      return false;
    }

    // 开始时间和结束时间要么都存在，要么都不存在
    if ((beginTime == null && endTime != null) || (beginTime != null && endTime == null)) {
      return false;
    }

    // 验证结束时间不早于开始时间
    return beginTime == null || !endTime.isBefore(beginTime);
  }

  /**
   * 获取当日工作时长的小时表示
   *
   * @return 工作小时数（保留两位小数）
   */
  public Double getDayWorkHour() {
    if (dayWorkSecond == null || dayWorkSecond <= 0) {
      return 0.0;
    }
    return dayWorkSecond / 3600.0;
  }

  /**
   * 获取累计工作时长的小时表示
   *
   * @return 累计工作小时数（保留两位小数）
   */
  public Double getWorkSumHour() {
    if (workSum == null || workSum <= 0) {
      return 0.0;
    }
    return workSum / 3600.0;
  }
}
