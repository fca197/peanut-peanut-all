package com.olivia.peanut.base.api.entity.districtCodeBoundary;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 地区边界(DistrictCodeBoundary)查询对象入参
 *
 * @author admin
 * @since 2025-08-22 13:33:38
 */
@Accessors(chain = true)
@Getter
@Setter
public class DistrictCodeBoundaryQueryListReq {

  private DistrictCodeBoundaryDto data;
}

