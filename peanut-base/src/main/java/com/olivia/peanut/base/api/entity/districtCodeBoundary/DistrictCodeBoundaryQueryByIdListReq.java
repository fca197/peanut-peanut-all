package com.olivia.peanut.base.api.entity.districtCodeBoundary;

import java.time.LocalDateTime;
import java.util.List;
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
@SuppressWarnings("serial")
public class DistrictCodeBoundaryQueryByIdListReq {

  private List<Long> idList;

}

