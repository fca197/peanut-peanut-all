package com.olivia.peanut.base.api.impl.listener;


import com.olivia.peanut.base.model.DistrictCodeBoundary;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.olivia.peanut.base.api.entity.districtCodeBoundary.*;
import com.alibaba.excel.context.AnalysisContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import com.olivia.sdk.listener.AbstractImportListener;

import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 地区边界(DistrictCodeBoundary)文件导入监听
 *
 * @author admin
 * @since 2025-08-22 13:33:38
 */
@Slf4j
public class DistrictCodeBoundaryImportListener extends AbstractImportListener<DistrictCodeBoundaryImportReq> {

  @Override
  public void invoke(DistrictCodeBoundaryImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("DistrictCodeBoundaryImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
