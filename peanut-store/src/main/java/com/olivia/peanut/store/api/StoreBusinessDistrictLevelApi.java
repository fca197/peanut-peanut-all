package com.olivia.peanut.store.api;

import com.olivia.peanut.store.api.entity.storeBusinessDistrictLevel.*;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


/**
 * 商圈级别(StoreBusinessDistrictLevel)对外API
 *
 * @author admin
 * @since 2025-08-24 21:10:15
 */
// @FeignClient(value = "",contextId = "storeBusinessDistrictLevel-api",url = "${ portal..center.endpoint:}")
public interface StoreBusinessDistrictLevelApi {

  /**
   * 保存 商圈级别
   */
  @PostMapping("/storeBusinessDistrictLevel/insert")
  StoreBusinessDistrictLevelInsertRes insert(@RequestBody @Validated(InsertCheck.class) StoreBusinessDistrictLevelInsertReq req);

  /**
   * 根据ID 删除 商圈级别
   */
  @PostMapping("/storeBusinessDistrictLevel/deleteByIdList")
  StoreBusinessDistrictLevelDeleteByIdListRes deleteByIdList(@RequestBody @Valid StoreBusinessDistrictLevelDeleteByIdListReq req);

  /**
   * 查询 商圈级别
   */
  @PostMapping("/storeBusinessDistrictLevel/queryList")
  StoreBusinessDistrictLevelQueryListRes queryList(@RequestBody @Valid StoreBusinessDistrictLevelQueryListReq req);

  /**
   * 根据ID 更新 商圈级别
   */
  @PostMapping("/storeBusinessDistrictLevel/updateById")
  StoreBusinessDistrictLevelUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) StoreBusinessDistrictLevelUpdateByIdReq req);

  /**
   * 分页查询 商圈级别
   */
  @PostMapping("/storeBusinessDistrictLevel/queryPageList")
  DynamicsPage<StoreBusinessDistrictLevelExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid StoreBusinessDistrictLevelExportQueryPageListReq req);

  /**
   * 导出 商圈级别
   */
  @PostMapping("/storeBusinessDistrictLevel/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid StoreBusinessDistrictLevelExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/storeBusinessDistrictLevel/importData")
  StoreBusinessDistrictLevelImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/storeBusinessDistrictLevel/queryByIdList")
  StoreBusinessDistrictLevelQueryByIdListRes queryByIdListRes(@RequestBody @Valid StoreBusinessDistrictLevelQueryByIdListReq req);


}
