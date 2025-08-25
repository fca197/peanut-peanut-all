package com.olivia.peanut.base.api;

import org.springframework.validation.annotation.Validated;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.base.api.entity.brand.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.web.multipart.MultipartFile;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * 品牌表(Brand)对外API
 *
 * @author admin
 * @since 2025-08-25 15:03:18
 */
// @FeignClient(value = "",contextId = "brand-api",url = "${ portal..center.endpoint:}")
public interface BrandApi {

  /**
   * 保存 品牌表
   */
  @PostMapping("/brand/insert")
  BrandInsertRes insert(@RequestBody @Validated(InsertCheck.class) BrandInsertReq req);

  /**
   * 根据ID 删除 品牌表
   */
  @PostMapping("/brand/deleteByIdList")
  BrandDeleteByIdListRes deleteByIdList(@RequestBody @Valid BrandDeleteByIdListReq req);

  /**
   * 查询 品牌表
   */
  @PostMapping("/brand/queryList")
  BrandQueryListRes queryList(@RequestBody @Valid BrandQueryListReq req);

  /**
   * 根据ID 更新 品牌表
   */
  @PostMapping("/brand/updateById")
  BrandUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) BrandUpdateByIdReq req);

  /**
   * 分页查询 品牌表
   */
  @PostMapping("/brand/queryPageList")
  DynamicsPage<BrandExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid BrandExportQueryPageListReq req);

  /**
   * 导出 品牌表
   */
  @PostMapping("/brand/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid BrandExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/brand/importData")
  BrandImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/brand/queryByIdList")
  BrandQueryByIdListRes queryByIdListRes(@RequestBody @Valid BrandQueryByIdListReq req);


}
