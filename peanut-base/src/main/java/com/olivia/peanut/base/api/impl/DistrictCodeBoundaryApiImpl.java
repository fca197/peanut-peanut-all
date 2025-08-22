package com.olivia.peanut.base.api.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.olivia.peanut.base.model.DistrictCodeBoundary;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import com.olivia.peanut.base.api.entity.districtCodeBoundary.*;
import com.olivia.peanut.base.service.DistrictCodeBoundaryService;
import com.olivia.peanut.base.model.*;
import com.baomidou.mybatisplus.core.conditions.query.*;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.base.api.DistrictCodeBoundaryApi;

import static com.olivia.peanut.base.converter.DistrictCodeBoundaryConverter.*;

import com.olivia.peanut.base.api.impl.listener.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 地区边界(DistrictCodeBoundary)表服务实现类
 *
 * @author admin
 * @since 2025-08-22 13:33:37
 */
@RestController
public class DistrictCodeBoundaryApiImpl implements DistrictCodeBoundaryApi {

  private @Autowired DistrictCodeBoundaryService districtCodeBoundaryService;

  /****
   * insert
   *
   */
  public @Override DistrictCodeBoundaryInsertRes insert(DistrictCodeBoundaryInsertReq req) {
    DistrictCodeBoundary districtCodeBoundary = INSTANCE.insertReq(req);
    this.districtCodeBoundaryService.save(districtCodeBoundary);
    return new DistrictCodeBoundaryInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override DistrictCodeBoundaryDeleteByIdListRes deleteByIdList(DistrictCodeBoundaryDeleteByIdListReq req) {
    districtCodeBoundaryService.removeByIds(req.getIdList());
    return new DistrictCodeBoundaryDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override DistrictCodeBoundaryQueryListRes queryList(DistrictCodeBoundaryQueryListReq req) {
    return districtCodeBoundaryService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override DistrictCodeBoundaryUpdateByIdRes updateById(DistrictCodeBoundaryUpdateByIdReq req) {
    districtCodeBoundaryService.updateById(INSTANCE.updateReq(req));
    return new DistrictCodeBoundaryUpdateByIdRes();

  }

  public @Override DynamicsPage<DistrictCodeBoundaryExportQueryPageListInfoRes> queryPageList(DistrictCodeBoundaryExportQueryPageListReq req) {
    return districtCodeBoundaryService.queryPageList(req);
  }

  public @Override void queryPageListExport(DistrictCodeBoundaryExportQueryPageListReq req) {
    DynamicsPage<DistrictCodeBoundaryExportQueryPageListInfoRes> page = queryPageList(req);
    List<DistrictCodeBoundaryExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    PoiExcelUtil.export(DistrictCodeBoundaryExportQueryPageListInfoRes.class, list, "地区边界");
  }

  public @Override DistrictCodeBoundaryImportRes importData(@RequestParam("file") MultipartFile file) {
    List<DistrictCodeBoundaryImportReq> reqList = PoiExcelUtil.readData(file, new DistrictCodeBoundaryImportListener(), DistrictCodeBoundaryImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<DistrictCodeBoundary> readList = INSTANCE.importReq(reqList);
    boolean bool = districtCodeBoundaryService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new DistrictCodeBoundaryImportRes().setCount(c);
  }

  public @Override DistrictCodeBoundaryQueryByIdListRes queryByIdListRes(DistrictCodeBoundaryQueryByIdListReq req) {
    MPJLambdaWrapper<DistrictCodeBoundary> q = new MPJLambdaWrapper<DistrictCodeBoundary>(DistrictCodeBoundary.class)
        .selectAll(DistrictCodeBoundary.class).in(DistrictCodeBoundary::getId, req.getIdList());
    List<DistrictCodeBoundary> list = this.districtCodeBoundaryService.list(q);
    List<DistrictCodeBoundaryDto> dataList = INSTANCE.queryListRes(list);
    this.districtCodeBoundaryService.setName(dataList);
    return new DistrictCodeBoundaryQueryByIdListRes().setDataList(dataList);
  }
}
