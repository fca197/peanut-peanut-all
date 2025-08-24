package com.olivia.peanut.store.api.impl;

import static com.olivia.peanut.store.converter.StoreBusinessDistrictLevelConverter.INSTANCE;

import com.olivia.peanut.store.api.StoreBusinessDistrictLevelApi;
import com.olivia.peanut.store.api.entity.storeBusinessDistrictLevel.*;
import com.olivia.peanut.store.api.impl.listener.StoreBusinessDistrictLevelImportListener;
import com.olivia.peanut.store.model.StoreBusinessDistrictLevel;
import com.olivia.peanut.store.service.StoreBusinessDistrictLevelService;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 商圈级别(StoreBusinessDistrictLevel)表服务实现类
 *
 * @author admin
 * @since 2025-08-24 21:10:16
 */
@RestController
public class StoreBusinessDistrictLevelApiImpl implements StoreBusinessDistrictLevelApi {

  private @Resource StoreBusinessDistrictLevelService storeBusinessDistrictLevelService;

  /****
   * insert
   *
   */
  public @Override StoreBusinessDistrictLevelInsertRes insert(StoreBusinessDistrictLevelInsertReq req) {
    StoreBusinessDistrictLevel storeBusinessDistrictLevel = INSTANCE.insertReq(req);
    this.storeBusinessDistrictLevelService.save(storeBusinessDistrictLevel);
    return new StoreBusinessDistrictLevelInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override StoreBusinessDistrictLevelDeleteByIdListRes deleteByIdList(StoreBusinessDistrictLevelDeleteByIdListReq req) {
    storeBusinessDistrictLevelService.removeByIds(req.getIdList());
    return new StoreBusinessDistrictLevelDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override StoreBusinessDistrictLevelQueryListRes queryList(StoreBusinessDistrictLevelQueryListReq req) {
    return storeBusinessDistrictLevelService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override StoreBusinessDistrictLevelUpdateByIdRes updateById(StoreBusinessDistrictLevelUpdateByIdReq req) {
    storeBusinessDistrictLevelService.updateById(INSTANCE.updateReq(req));
    return new StoreBusinessDistrictLevelUpdateByIdRes();

  }

  public @Override DynamicsPage<StoreBusinessDistrictLevelExportQueryPageListInfoRes> queryPageList(StoreBusinessDistrictLevelExportQueryPageListReq req) {
    return storeBusinessDistrictLevelService.queryPageList(req);
  }

  public @Override void queryPageListExport(StoreBusinessDistrictLevelExportQueryPageListReq req) {
    DynamicsPage<StoreBusinessDistrictLevelExportQueryPageListInfoRes> page = queryPageList(req);
    List<StoreBusinessDistrictLevelExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    PoiExcelUtil.export(StoreBusinessDistrictLevelExportQueryPageListInfoRes.class, list, "商圈级别");
  }

  public @Override StoreBusinessDistrictLevelImportRes importData(@RequestParam("file") MultipartFile file) {
    List<StoreBusinessDistrictLevelImportReq> reqList = PoiExcelUtil.readData(file, new StoreBusinessDistrictLevelImportListener(),
        StoreBusinessDistrictLevelImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<StoreBusinessDistrictLevel> readList = INSTANCE.importReq(reqList);
    boolean bool = storeBusinessDistrictLevelService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new StoreBusinessDistrictLevelImportRes().setCount(c);
  }

  public @Override StoreBusinessDistrictLevelQueryByIdListRes queryByIdListRes(StoreBusinessDistrictLevelQueryByIdListReq req) {

    List<StoreBusinessDistrictLevel> list = this.storeBusinessDistrictLevelService.listByIds(req.getIdList());
    List<StoreBusinessDistrictLevelDto> dataList = INSTANCE.queryListRes(list);
    this.storeBusinessDistrictLevelService.setName(dataList);
    return new StoreBusinessDistrictLevelQueryByIdListRes().setDataList(dataList);
  }
}
