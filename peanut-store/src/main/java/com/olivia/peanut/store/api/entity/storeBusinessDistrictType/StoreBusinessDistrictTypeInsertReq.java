package com.olivia.peanut.store.api.entity.storeBusinessDistrictType;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 商圈类型(StoreBusinessDistrictType)保存入参
 *
 * @author admin
 * @since 2025-08-24 21:10:17
 */
@Accessors(chain = true)
@Getter
@Setter
public class StoreBusinessDistrictTypeInsertReq extends StoreBusinessDistrictTypeDto {

  public void checkParam() {
  }
}

