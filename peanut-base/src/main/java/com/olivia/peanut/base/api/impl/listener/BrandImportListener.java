package com.olivia.peanut.base.api.impl.listener;


import com.olivia.peanut.base.model.Brand;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.olivia.peanut.base.api.entity.brand.*;
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
 * 品牌表(Brand)文件导入监听
 *
 * @author admin
 * @since 2025-08-25 15:03:19
 */
@Slf4j
public class BrandImportListener extends AbstractImportListener<BrandImportReq> {

  @Override
  public void invoke(BrandImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("BrandImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
