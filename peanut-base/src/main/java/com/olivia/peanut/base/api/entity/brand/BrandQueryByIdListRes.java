package com.olivia.peanut.base.api.entity.brand;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 品牌表(Brand)查询对象返回
 *
 * @author admin
 * @since 2025-08-25 15:03:19
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BrandQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<BrandDto> dataList;


}

