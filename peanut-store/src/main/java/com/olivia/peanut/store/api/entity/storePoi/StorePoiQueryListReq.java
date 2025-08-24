package com.olivia.peanut.store.api.entity.storePoi;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * store poi(StorePoi)查询对象入参
 *
 * @author admin
 * @since 2025-08-22 16:10:27
 */
@Accessors(chain = true)
@Getter
@Setter
public class StorePoiQueryListReq {

  private StorePoiDto data;
}

