package com.olivia.peanut.store.api.entity.storeBusinessDistrictLevel;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 商圈级别(StoreBusinessDistrictLevel)根据ID删除多个反参
 *
 * @author admin
 * @since 2025-08-24 21:10:16
 */
@Accessors(chain = true)
@Getter
@Setter
public class StoreBusinessDistrictLevelDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

