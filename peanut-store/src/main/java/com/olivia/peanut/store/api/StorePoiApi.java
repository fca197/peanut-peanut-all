package com.olivia.peanut.store.api;

import java.util.List;
import org.springframework.validation.annotation.Validated;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.store.api.entity.storePoi.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.web.multipart.MultipartFile;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * store poi(StorePoi)对外API
 *
 * @author admin
 * @since 2025-08-22 16:10:27
 */
// @FeignClient(value = "",contextId = "storePoi-api",url = "${ portal..center.endpoint:}")
public interface StorePoiApi {

  /**
   * 保存 store poi
   */
  @PostMapping("/storePoi/insert")
  StorePoiInsertRes insert(@RequestBody @Validated(InsertCheck.class) StorePoiInsertReq req);

  /**
   * 根据ID 删除 store poi
   */
  @PostMapping("/storePoi/deleteByIdList")
  StorePoiDeleteByIdListRes deleteByIdList(@RequestBody @Valid StorePoiDeleteByIdListReq req);

  /**
   * 查询 store poi
   */
  @PostMapping("/storePoi/queryList")
  StorePoiQueryListRes queryList(@RequestBody @Valid StorePoiQueryListReq req);

  /**
   * 根据ID 更新 store poi
   */
  @PostMapping("/storePoi/updateById")
  StorePoiUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) StorePoiUpdateByIdReq req);

  /**
   * 分页查询 store poi
   */
  @PostMapping("/storePoi/queryPageList")
  DynamicsPage<StorePoiExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid StorePoiExportQueryPageListReq req);

  /**
   * 导出 store poi
   */
  @PostMapping("/storePoi/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid StorePoiExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/storePoi/importData")
  StorePoiImportRes importData(@RequestParam("file") MultipartFile file);


  @PostMapping("/storePoi/selectTree")
  StorePoiSelectTreeRes storePoiDtoList(@RequestBody @Valid StorePoiSelectTreeReq req);

  /**
   * 根据ID 批量查询
   */
  @PostMapping("/storePoi/queryByIdList")
  StorePoiQueryByIdListRes queryByIdListRes(@RequestBody @Valid StorePoiQueryByIdListReq req);


}
