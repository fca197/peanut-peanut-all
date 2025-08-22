package com.olivia.peanut.base.api.entity.districtCodeBoundary;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 地区边界(DistrictCodeBoundary)保存返回
 *
 * @author admin
 * @since 2025-08-22 13:33:38
 */
@Accessors(chain = true)
@Getter
@Setter
public class DistrictCodeBoundaryInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

