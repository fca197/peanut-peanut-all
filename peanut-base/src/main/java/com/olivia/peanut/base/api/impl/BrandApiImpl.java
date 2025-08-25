package com.olivia.peanut.base.api.impl;

import java.time.LocalDateTime;

import com.olivia.peanut.base.model.Brand;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import com.olivia.peanut.base.api.entity.brand.*;
import com.olivia.peanut.base.service.BrandService;
import com.olivia.peanut.base.model.*;
import com.baomidou.mybatisplus.core.conditions.query.*;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.base.api.BrandApi;

import static com.olivia.peanut.base.converter.BrandConverter.*;

import com.olivia.peanut.base.api.impl.listener.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.annotation.Resource;

/**
 * 品牌表(Brand)表服务实现类
 *
 * @author admin
 * @since 2025-08-25 15:03:18
 */
@RestController
public class BrandApiImpl implements BrandApi {

  private @Resource BrandService brandService;

  /****
   * insert
   *
   */
  public @Override BrandInsertRes insert(BrandInsertReq req) {
    Brand brand = INSTANCE.insertReq(req);
    this.brandService.save(brand);
    return new BrandInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override BrandDeleteByIdListRes deleteByIdList(BrandDeleteByIdListReq req) {
    brandService.removeByIds(req.getIdList());
    return new BrandDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override BrandQueryListRes queryList(BrandQueryListReq req) {
    return brandService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override BrandUpdateByIdRes updateById(BrandUpdateByIdReq req) {
    brandService.updateById(INSTANCE.updateReq(req));
    return new BrandUpdateByIdRes();

  }

  public @Override DynamicsPage<BrandExportQueryPageListInfoRes> queryPageList(BrandExportQueryPageListReq req) {
    return brandService.queryPageList(req);
  }

  public @Override void queryPageListExport(BrandExportQueryPageListReq req) {
    DynamicsPage<BrandExportQueryPageListInfoRes> page = queryPageList(req);
    List<BrandExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    PoiExcelUtil.export(BrandExportQueryPageListInfoRes.class, list, "品牌表");
  }

  public @Override BrandImportRes importData(@RequestParam("file") MultipartFile file) {
    List<BrandImportReq> reqList = PoiExcelUtil.readData(file, new BrandImportListener(), BrandImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<Brand> readList = INSTANCE.importReq(reqList);
    boolean bool = brandService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new BrandImportRes().setCount(c);
  }

  public @Override BrandQueryByIdListRes queryByIdListRes(BrandQueryByIdListReq req) {

    List<Brand> list = this.brandService.listByIds(req.getIdList());
    List<BrandDto> dataList = INSTANCE.queryListRes(list);
    this.brandService.setName(dataList);
    return new BrandQueryByIdListRes().setDataList(dataList);
  }
}
