package com.olivia.peanut.store.api.entity.storeBusinessDistrictType;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 商圈类型(StoreBusinessDistrictType)查询对象入参
 *
 * @author admin
 * @since 2025-08-24 21:10:18
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class StoreBusinessDistrictTypeQueryByIdListReq {

  private List<Long> idList;

}

