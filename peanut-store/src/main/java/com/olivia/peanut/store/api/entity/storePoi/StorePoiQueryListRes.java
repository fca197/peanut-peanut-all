package com.olivia.peanut.store.api.entity.storePoi;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * store poi(StorePoi)查询对象返回
 *
 * @author admin
 * @since 2025-08-22 16:10:27
 */
@Accessors(chain = true)
@Getter
@Setter
public class StorePoiQueryListRes {

  /***
   * 返回对象列表
   */
  private List<StorePoiDto> dataList;


}

