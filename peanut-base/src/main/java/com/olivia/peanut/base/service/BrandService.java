package com.olivia.peanut.base.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.base.model.Brand;
import java.util.List;
import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.base.api.entity.brand.*;

/**
 * 品牌表(Brand)表服务接口
 *
 * @author admin
 * @since 2025-08-25 15:03:19
 */
public interface BrandService extends MPJBaseService<Brand> {

  BrandQueryListRes queryList(BrandQueryListReq req);

  DynamicsPage<BrandExportQueryPageListInfoRes> queryPageList(BrandExportQueryPageListReq req);


  void setName(List<? extends BrandDto> brandDtoList);
}

