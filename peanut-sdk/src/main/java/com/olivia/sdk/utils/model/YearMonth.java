package com.olivia.sdk.utils.model;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.time.Duration;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class YearMonth {

  static Cache<String, YearMonth> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(Duration.ofHours(1)).build();

  private Integer year;
  private Integer month;


  public YearMonth() {
  }

  public YearMonth(Integer year, Integer month) {
    this.year = year;
    this.month = month;
  }

  @SneakyThrows
  public static YearMonth of(String yearMonth) {
    return cache.get(yearMonth, () -> {
      String[] split = yearMonth.split("-");
      return new YearMonth(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
    });

  }

  public String getMonthStr() {
    return month > 9 ? month + "" : "0" + month;
  }

  @Override
  public String toString() {
    return this.year + "-" + ((this.month < 10) ? "0" + this.month : this.month);
  }
}
