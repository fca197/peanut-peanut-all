package com.olivia.peanut.store.api.impl.listener;


import com.olivia.peanut.store.model.StorePoi;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.olivia.peanut.store.api.entity.storePoi.*;
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
 * store poi(StorePoi)文件导入监听
 *
 * @author admin
 * @since 2025-08-22 16:10:27
 */
@Slf4j
public class StorePoiImportListener extends AbstractImportListener<StorePoiImportReq> {

  @Override
  public void invoke(StorePoiImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("StorePoiImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
