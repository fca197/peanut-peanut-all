package com.olivia.peanut.base.api.entity.districtCodeBoundary;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 地区边界(DistrictCodeBoundary)查询对象返回
 *
 * @author admin
 * @since 2025-08-22 13:33:38
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class DistrictCodeBoundaryQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<DistrictCodeBoundaryDto> dataList;


}

