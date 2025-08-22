package com.olivia.peanut.base.api.entity.districtCodeBoundary;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 地区边界(DistrictCodeBoundary)根据ID删除多个反参
 *
 * @author admin
 * @since 2025-08-22 13:33:38
 */
@Accessors(chain = true)
@Getter
@Setter
public class DistrictCodeBoundaryDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

