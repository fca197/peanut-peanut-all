package com.olivia.peanut.store.api.entity.storePoi;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * store poi(StorePoi)保存返回
 *
 * @author admin
 * @since 2025-08-22 16:10:27
 */
@Accessors(chain = true)
@Getter
@Setter
public class StorePoiInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

