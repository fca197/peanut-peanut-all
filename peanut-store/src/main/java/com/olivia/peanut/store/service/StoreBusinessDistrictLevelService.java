package com.olivia.peanut.store.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.store.api.entity.storeBusinessDistrictLevel.*;
import com.olivia.peanut.store.model.StoreBusinessDistrictLevel;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;

/**
 * 商圈级别(StoreBusinessDistrictLevel)表服务接口
 *
 * @author admin
 * @since 2025-08-24 21:10:16
 */
public interface StoreBusinessDistrictLevelService extends MPJBaseService<StoreBusinessDistrictLevel> {

  StoreBusinessDistrictLevelQueryListRes queryList(StoreBusinessDistrictLevelQueryListReq req);

  DynamicsPage<StoreBusinessDistrictLevelExportQueryPageListInfoRes> queryPageList(StoreBusinessDistrictLevelExportQueryPageListReq req);


  void setName(List<? extends StoreBusinessDistrictLevelDto> storeBusinessDistrictLevelDtoList);
}

