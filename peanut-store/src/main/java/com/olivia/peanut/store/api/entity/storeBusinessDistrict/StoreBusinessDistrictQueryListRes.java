package com.olivia.peanut.store.api.entity.storeBusinessDistrict;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 商圈(StoreBusinessDistrict)查询对象返回
 *
 * @author admin
 * @since 2025-08-24 21:01:55
 */
@Accessors(chain = true)
@Getter
@Setter
public class StoreBusinessDistrictQueryListRes {

  /***
   * 返回对象列表
   */
  private List<StoreBusinessDistrictDto> dataList;


}

