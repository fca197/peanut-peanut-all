package com.olivia.sdk.utils.model;

import com.olivia.sdk.utils.DateUtils;
import com.olivia.sdk.utils.Str;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 非工作日时间信息模型类 用于记录和计算非工作日的时间区间及时长信息
 */
@Setter
@Getter
@Accessors(chain = true)
public class NoWorkDayTimeInfo {

  /**
   * 当前日期
   */
  private LocalDate currentDate;

  /**
   * 开始时间（包含日期）
   */
  private LocalDateTime beginTime;

  /**
   * 结束时间（包含日期）
   */
  private LocalDateTime endTime;

  /**
   * 开始时间的秒级时间戳
   */
  private Long beginTimeSecond;

  /**
   * 结束时间的秒级时间戳
   */
  private Long endTimeSecond;

  /**
   * 非工作日时间总秒数
   */
  private Long noWorkDayTimeSecond;

  /**
   * 构建时间信息，计算时间戳和时长 自动处理特殊时间（如23:59:59）的边界情况
   *
   * @throws IllegalArgumentException 当时间不合法时抛出
   */
  public void buildTime() {
    validateTimeRange();

    LocalDateTime processedEndTime = processEndTime(endTime);

    // 计算时间戳（使用指定时区偏移）
    ZoneOffset zoneOffset = ZoneOffset.of(Str.OFFSET_ID);
    this.beginTimeSecond = beginTime.toEpochSecond(zoneOffset);
    this.endTimeSecond = processedEndTime.toEpochSecond(zoneOffset);

    // 计算非工作日时长（秒）
    this.noWorkDayTimeSecond = calculateDuration(beginTime, processedEndTime);
  }

  /**
   * 验证时间范围的有效性
   *
   * @throws IllegalArgumentException 当开始时间晚于结束时间时抛出
   */
  private void validateTimeRange() {
    if (beginTime == null || endTime == null) {
      throw new IllegalArgumentException("开始时间和结束时间不能为空");
    }

    if (beginTime.isAfter(endTime)) {
      throw new IllegalArgumentException("开始时间不能晚于结束时间: " + beginTime + " -> " + endTime);
    }

    // 验证当前日期与时间的一致性
    if (currentDate != null) {
      LocalDate beginDate = beginTime.toLocalDate();
      LocalDate endDate = endTime.toLocalDate();

      if (!beginDate.equals(currentDate) || !endDate.equals(currentDate)) {
        throw new IllegalArgumentException("时间与当前日期不匹配: " + currentDate + " vs " + beginDate + "-" + endDate);
      }
    }
  }

  /**
   * 处理结束时间，对当天结束时间做特殊处理
   *
   * @param originalEndTime 原始结束时间
   * @return 处理后的结束时间
   */
  private LocalDateTime processEndTime(LocalDateTime originalEndTime) {
    // 如果结束时间是当天结束时间(23:59:59)，则加1秒变为次日00:00:00
    if (originalEndTime.toLocalTime().equals(DateUtils.DAY_END_TIME)) {
      return originalEndTime.plusSeconds(1);
    }
    return originalEndTime;
  }

  /**
   * 计算两个时间之间的时长（秒）
   *
   * @param start 开始时间
   * @param end   结束时间
   * @return 时长（秒）
   */
  private Long calculateDuration(LocalDateTime start, LocalDateTime end) {
    return ChronoUnit.SECONDS.between(start, end);
  }

  /**
   * 获取非工作日时长（小时）
   *
   * @return 时长（小时，保留两位小数）
   */
  public Double getNoWorkDayTimeHour() {
    if (noWorkDayTimeSecond == null || noWorkDayTimeSecond <= 0) {
      return 0.0;
    }
    return noWorkDayTimeSecond / 3600.0;
  }
}
