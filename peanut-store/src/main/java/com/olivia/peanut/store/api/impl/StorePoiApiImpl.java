package com.olivia.peanut.store.api.impl;

import static com.olivia.peanut.store.converter.StorePoiConverter.INSTANCE;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.store.api.StorePoiApi;
import com.olivia.peanut.store.api.entity.storePoi.*;
import com.olivia.peanut.store.api.impl.listener.StorePoiImportListener;
import com.olivia.peanut.store.model.StorePoi;
import com.olivia.peanut.store.service.StorePoiService;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * store poi(StorePoi)表服务实现类
 *
 * @author admin
 * @since 2025-08-22 16:10:27
 */
@RestController
public class StorePoiApiImpl implements StorePoiApi {

  private @Resource StorePoiService storePoiService;

  /****
   * insert
   *
   */
  public @Override StorePoiInsertRes insert(StorePoiInsertReq req) {
    StorePoi storePoi = INSTANCE.insertReq(req);
    this.storePoiService.save(storePoi);
    return new StorePoiInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override StorePoiDeleteByIdListRes deleteByIdList(StorePoiDeleteByIdListReq req) {
    storePoiService.removeByIds(req.getIdList());
    return new StorePoiDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override StorePoiQueryListRes queryList(StorePoiQueryListReq req) {
    return storePoiService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override StorePoiUpdateByIdRes updateById(StorePoiUpdateByIdReq req) {
    storePoiService.updateById(INSTANCE.updateReq(req));
    return new StorePoiUpdateByIdRes();

  }

  public @Override DynamicsPage<StorePoiExportQueryPageListInfoRes> queryPageList(StorePoiExportQueryPageListReq req) {
    return storePoiService.queryPageList(req);
  }

  public @Override void queryPageListExport(StorePoiExportQueryPageListReq req) {
    DynamicsPage<StorePoiExportQueryPageListInfoRes> page = queryPageList(req);
    List<StorePoiExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    PoiExcelUtil.export(StorePoiExportQueryPageListInfoRes.class, list, "store poi");
  }

  public @Override StorePoiImportRes importData(@RequestParam("file") MultipartFile file) {
    List<StorePoiImportReq> reqList = PoiExcelUtil.readData(file, new StorePoiImportListener(), StorePoiImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<StorePoi> readList = INSTANCE.importReq(reqList);
    boolean bool = storePoiService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new StorePoiImportRes().setCount(c);
  }

  @Override
  public StorePoiSelectTreeRes storePoiDtoList(StorePoiSelectTreeReq req) {
    return this.storePoiService.storePoiDtoList(req);
  }

  public @Override StorePoiQueryByIdListRes queryByIdListRes(StorePoiQueryByIdListReq req) {

    List<StorePoi> list = this.storePoiService.listByIds(req.getIdList());
    List<StorePoiDto> dataList = INSTANCE.queryListRes(list);
    this.storePoiService.setName(dataList);
    return new StorePoiQueryByIdListRes().setDataList(dataList);
  }
}
