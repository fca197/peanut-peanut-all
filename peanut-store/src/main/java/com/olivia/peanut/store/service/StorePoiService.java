package com.olivia.peanut.store.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.store.model.StorePoi;
import java.util.List;
import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.store.api.entity.storePoi.*;

/**
 * store poi(StorePoi)表服务接口
 *
 * @author admin
 * @since 2025-08-22 16:10:27
 */
public interface StorePoiService extends MPJBaseService<StorePoi> {

  StorePoiQueryListRes queryList(StorePoiQueryListReq req);

  DynamicsPage<StorePoiExportQueryPageListInfoRes> queryPageList(StorePoiExportQueryPageListReq req);


  void setName(List<? extends StorePoiDto> storePoiDtoList);

  StorePoiSelectTreeRes storePoiDtoList(StorePoiSelectTreeReq req);
}

