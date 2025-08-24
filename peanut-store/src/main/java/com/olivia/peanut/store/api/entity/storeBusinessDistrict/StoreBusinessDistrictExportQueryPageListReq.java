package com.olivia.peanut.store.api.entity.storeBusinessDistrict;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 商圈(StoreBusinessDistrict)查询对象入参
 *
 * @author admin
 * @since 2025-08-24 21:01:55
 */
@Accessors(chain = true)
@Getter
@Setter
public class StoreBusinessDistrictExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private StoreBusinessDistrictDto data;
}

