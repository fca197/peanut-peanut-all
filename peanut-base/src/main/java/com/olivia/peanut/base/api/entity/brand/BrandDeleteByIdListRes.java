package com.olivia.peanut.base.api.entity.brand;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 品牌表(Brand)根据ID删除多个反参
 *
 * @author admin
 * @since 2025-08-25 15:03:18
 */
@Accessors(chain = true)
@Getter
@Setter
public class BrandDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

