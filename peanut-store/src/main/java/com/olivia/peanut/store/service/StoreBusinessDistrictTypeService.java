package com.olivia.peanut.store.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.store.api.entity.storeBusinessDistrictType.*;
import com.olivia.peanut.store.model.StoreBusinessDistrictType;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;

/**
 * 商圈类型(StoreBusinessDistrictType)表服务接口
 *
 * @author admin
 * @since 2025-08-24 21:10:18
 */
public interface StoreBusinessDistrictTypeService extends MPJBaseService<StoreBusinessDistrictType> {

  StoreBusinessDistrictTypeQueryListRes queryList(StoreBusinessDistrictTypeQueryListReq req);

  DynamicsPage<StoreBusinessDistrictTypeExportQueryPageListInfoRes> queryPageList(StoreBusinessDistrictTypeExportQueryPageListReq req);


  void setName(List<? extends StoreBusinessDistrictTypeDto> storeBusinessDistrictTypeDtoList);
}

