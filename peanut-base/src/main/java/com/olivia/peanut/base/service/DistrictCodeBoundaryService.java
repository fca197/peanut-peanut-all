package com.olivia.peanut.base.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.base.model.DistrictCodeBoundary;
import java.util.List;
import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.base.api.entity.districtCodeBoundary.*;

/**
 * 地区边界(DistrictCodeBoundary)表服务接口
 *
 * @author admin
 * @since 2025-08-22 13:33:38
 */
public interface DistrictCodeBoundaryService extends MPJBaseService<DistrictCodeBoundary> {

  DistrictCodeBoundaryQueryListRes queryList(DistrictCodeBoundaryQueryListReq req);

  DynamicsPage<DistrictCodeBoundaryExportQueryPageListInfoRes> queryPageList(DistrictCodeBoundaryExportQueryPageListReq req);


  void setName(List<? extends DistrictCodeBoundaryDto> districtCodeBoundaryDtoList);
}

