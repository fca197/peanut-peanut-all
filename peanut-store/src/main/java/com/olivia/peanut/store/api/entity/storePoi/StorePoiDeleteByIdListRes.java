package com.olivia.peanut.store.api.entity.storePoi;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * store poi(StorePoi)根据ID删除多个反参
 *
 * @author admin
 * @since 2025-08-22 16:10:27
 */
@Accessors(chain = true)
@Getter
@Setter
public class StorePoiDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

