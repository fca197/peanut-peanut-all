package com.olivia.peanut.base.api.entity.brand;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 品牌表(Brand)查询对象入参
 *
 * @author admin
 * @since 2025-08-25 15:03:18
 */
@Accessors(chain = true)
@Getter
@Setter
public class BrandQueryListReq {

  private BrandDto data;
}

