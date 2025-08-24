package com.olivia.peanut.store.api;

import com.olivia.peanut.store.api.entity.storeBusinessDistrictType.*;
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
 * 商圈类型(StoreBusinessDistrictType)对外API
 *
 * @author admin
 * @since 2025-08-24 21:10:17
 */
// @FeignClient(value = "",contextId = "storeBusinessDistrictType-api",url = "${ portal..center.endpoint:}")
public interface StoreBusinessDistrictTypeApi {

  /**
   * 保存 商圈类型
   */
  @PostMapping("/storeBusinessDistrictType/insert")
  StoreBusinessDistrictTypeInsertRes insert(@RequestBody @Validated(InsertCheck.class) StoreBusinessDistrictTypeInsertReq req);

  /**
   * 根据ID 删除 商圈类型
   */
  @PostMapping("/storeBusinessDistrictType/deleteByIdList")
  StoreBusinessDistrictTypeDeleteByIdListRes deleteByIdList(@RequestBody @Valid StoreBusinessDistrictTypeDeleteByIdListReq req);

  /**
   * 查询 商圈类型
   */
  @PostMapping("/storeBusinessDistrictType/queryList")
  StoreBusinessDistrictTypeQueryListRes queryList(@RequestBody @Valid StoreBusinessDistrictTypeQueryListReq req);

  /**
   * 根据ID 更新 商圈类型
   */
  @PostMapping("/storeBusinessDistrictType/updateById")
  StoreBusinessDistrictTypeUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) StoreBusinessDistrictTypeUpdateByIdReq req);

  /**
   * 分页查询 商圈类型
   */
  @PostMapping("/storeBusinessDistrictType/queryPageList")
  DynamicsPage<StoreBusinessDistrictTypeExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid StoreBusinessDistrictTypeExportQueryPageListReq req);

  /**
   * 导出 商圈类型
   */
  @PostMapping("/storeBusinessDistrictType/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid StoreBusinessDistrictTypeExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/storeBusinessDistrictType/importData")
  StoreBusinessDistrictTypeImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/storeBusinessDistrictType/queryByIdList")
  StoreBusinessDistrictTypeQueryByIdListRes queryByIdListRes(@RequestBody @Valid StoreBusinessDistrictTypeQueryByIdListReq req);


}
