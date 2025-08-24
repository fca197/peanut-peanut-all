package com.olivia.peanut.store.api.impl;

import static com.olivia.peanut.store.converter.StoreBusinessDistrictTypeConverter.INSTANCE;

import com.olivia.peanut.store.api.StoreBusinessDistrictTypeApi;
import com.olivia.peanut.store.api.entity.storeBusinessDistrictType.*;
import com.olivia.peanut.store.api.impl.listener.StoreBusinessDistrictTypeImportListener;
import com.olivia.peanut.store.model.StoreBusinessDistrictType;
import com.olivia.peanut.store.service.StoreBusinessDistrictTypeService;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 商圈类型(StoreBusinessDistrictType)表服务实现类
 *
 * @author admin
 * @since 2025-08-24 21:10:17
 */
@RestController
public class StoreBusinessDistrictTypeApiImpl implements StoreBusinessDistrictTypeApi {

  private @Resource StoreBusinessDistrictTypeService storeBusinessDistrictTypeService;

  /****
   * insert
   *
   */
  public @Override StoreBusinessDistrictTypeInsertRes insert(StoreBusinessDistrictTypeInsertReq req) {
    StoreBusinessDistrictType storeBusinessDistrictType = INSTANCE.insertReq(req);
    this.storeBusinessDistrictTypeService.save(storeBusinessDistrictType);
    return new StoreBusinessDistrictTypeInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override StoreBusinessDistrictTypeDeleteByIdListRes deleteByIdList(StoreBusinessDistrictTypeDeleteByIdListReq req) {
    storeBusinessDistrictTypeService.removeByIds(req.getIdList());
    return new StoreBusinessDistrictTypeDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override StoreBusinessDistrictTypeQueryListRes queryList(StoreBusinessDistrictTypeQueryListReq req) {
    return storeBusinessDistrictTypeService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override StoreBusinessDistrictTypeUpdateByIdRes updateById(StoreBusinessDistrictTypeUpdateByIdReq req) {
    storeBusinessDistrictTypeService.updateById(INSTANCE.updateReq(req));
    return new StoreBusinessDistrictTypeUpdateByIdRes();

  }

  public @Override DynamicsPage<StoreBusinessDistrictTypeExportQueryPageListInfoRes> queryPageList(StoreBusinessDistrictTypeExportQueryPageListReq req) {
    return storeBusinessDistrictTypeService.queryPageList(req);
  }

  public @Override void queryPageListExport(StoreBusinessDistrictTypeExportQueryPageListReq req) {
    DynamicsPage<StoreBusinessDistrictTypeExportQueryPageListInfoRes> page = queryPageList(req);
    List<StoreBusinessDistrictTypeExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    PoiExcelUtil.export(StoreBusinessDistrictTypeExportQueryPageListInfoRes.class, list, "商圈类型");
  }

  public @Override StoreBusinessDistrictTypeImportRes importData(@RequestParam("file") MultipartFile file) {
    List<StoreBusinessDistrictTypeImportReq> reqList = PoiExcelUtil.readData(file, new StoreBusinessDistrictTypeImportListener(),
        StoreBusinessDistrictTypeImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<StoreBusinessDistrictType> readList = INSTANCE.importReq(reqList);
    boolean bool = storeBusinessDistrictTypeService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new StoreBusinessDistrictTypeImportRes().setCount(c);
  }

  public @Override StoreBusinessDistrictTypeQueryByIdListRes queryByIdListRes(StoreBusinessDistrictTypeQueryByIdListReq req) {

    List<StoreBusinessDistrictType> list = this.storeBusinessDistrictTypeService.listByIds(req.getIdList());
    List<StoreBusinessDistrictTypeDto> dataList = INSTANCE.queryListRes(list);
    this.storeBusinessDistrictTypeService.setName(dataList);
    return new StoreBusinessDistrictTypeQueryByIdListRes().setDataList(dataList);
  }
}
