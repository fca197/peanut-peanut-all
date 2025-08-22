package com.olivia.peanut.base.api;

import org.springframework.validation.annotation.Validated;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.base.api.entity.districtCodeBoundary.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.web.multipart.MultipartFile;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * 地区边界(DistrictCodeBoundary)对外API
 *
 * @author admin
 * @since 2025-08-22 13:33:37
 */
// @FeignClient(value = "",contextId = "districtCodeBoundary-api",url = "${ portal..center.endpoint:}")
public interface DistrictCodeBoundaryApi {

  /**
   * 保存 地区边界
   */
  @PostMapping("/districtCodeBoundary/insert")
  DistrictCodeBoundaryInsertRes insert(@RequestBody @Validated(InsertCheck.class) DistrictCodeBoundaryInsertReq req);

  /**
   * 根据ID 删除 地区边界
   */
  @PostMapping("/districtCodeBoundary/deleteByIdList")
  DistrictCodeBoundaryDeleteByIdListRes deleteByIdList(@RequestBody @Valid DistrictCodeBoundaryDeleteByIdListReq req);

  /**
   * 查询 地区边界
   */
  @PostMapping("/districtCodeBoundary/queryList")
  DistrictCodeBoundaryQueryListRes queryList(@RequestBody @Valid DistrictCodeBoundaryQueryListReq req);

  /**
   * 根据ID 更新 地区边界
   */
  @PostMapping("/districtCodeBoundary/updateById")
  DistrictCodeBoundaryUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) DistrictCodeBoundaryUpdateByIdReq req);

  /**
   * 分页查询 地区边界
   */
  @PostMapping("/districtCodeBoundary/queryPageList")
  DynamicsPage<DistrictCodeBoundaryExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid DistrictCodeBoundaryExportQueryPageListReq req);

  /**
   * 导出 地区边界
   */
  @PostMapping("/districtCodeBoundary/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid DistrictCodeBoundaryExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/districtCodeBoundary/importData")
  DistrictCodeBoundaryImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/districtCodeBoundary/queryByIdList")
  DistrictCodeBoundaryQueryByIdListRes queryByIdListRes(@RequestBody @Valid DistrictCodeBoundaryQueryByIdListReq req);


}
