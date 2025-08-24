package com.olivia.peanut.store.api.entity.storeBusinessDistrictLevel;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 商圈级别(StoreBusinessDistrictLevel)查询对象返回
 *
 * @author admin
 * @since 2025-08-24 21:10:16
 */
@Accessors(chain = true)
@Getter
@Setter
public class StoreBusinessDistrictLevelQueryListRes {

  /***
   * 返回对象列表
   */
  private List<StoreBusinessDistrictLevelDto> dataList;


}

