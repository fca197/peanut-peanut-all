package com.olivia.peanut.store.api.entity.storeBusinessDistrictLevel;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 商圈级别(StoreBusinessDistrictLevel)查询对象入参
 *
 * @author admin
 * @since 2025-08-24 21:10:16
 */
@Accessors(chain = true)
@Getter
@Setter
public class StoreBusinessDistrictLevelExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private StoreBusinessDistrictLevelDto data;
}

