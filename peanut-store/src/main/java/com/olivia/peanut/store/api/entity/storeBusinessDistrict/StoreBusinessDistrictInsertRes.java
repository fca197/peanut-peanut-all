package com.olivia.peanut.store.api.entity.storeBusinessDistrict;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 商圈(StoreBusinessDistrict)保存返回
 *
 * @author admin
 * @since 2025-08-24 21:01:54
 */
@Accessors(chain = true)
@Getter
@Setter
public class StoreBusinessDistrictInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

