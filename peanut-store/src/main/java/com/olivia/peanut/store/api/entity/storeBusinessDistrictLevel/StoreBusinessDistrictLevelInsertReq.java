package com.olivia.peanut.store.api.entity.storeBusinessDistrictLevel;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 商圈级别(StoreBusinessDistrictLevel)保存入参
 *
 * @author admin
 * @since 2025-08-24 21:10:16
 */
@Accessors(chain = true)
@Getter
@Setter
public class StoreBusinessDistrictLevelInsertReq extends StoreBusinessDistrictLevelDto {

  public void checkParam() {
  }
}

