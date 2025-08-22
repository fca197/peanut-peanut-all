package com.olivia.peanut.store.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.peanut.store.api.entity.storePoi.*;
import com.olivia.peanut.store.converter.StorePoiConverter;
import com.olivia.peanut.store.mapper.StorePoiMapper;
import com.olivia.peanut.store.model.StorePoi;
import com.olivia.peanut.store.service.StorePoiService;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.*;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * store poi(StorePoi)表服务实现类
 *
 * @author admin
 * @since 2025-08-22 16:10:27
 */
@Service("storePoiService")
@Transactional
public class StorePoiServiceImpl extends MPJBaseServiceImpl<StorePoiMapper, StorePoi> implements StorePoiService {

  // final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override StorePoiQueryListRes queryList(StorePoiQueryListReq req) {

    MPJLambdaWrapper<StorePoi> q = getWrapper(req.getData());
    List<StorePoi> list = this.list(q);

    List<StorePoiDto> dataList = StorePoiConverter.INSTANCE.queryListRes(list);
    ((StorePoiService) AopContext.currentProxy()).setName(dataList);
    return new StorePoiQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<StorePoiExportQueryPageListInfoRes> queryPageList(StorePoiExportQueryPageListReq req) {

    DynamicsPage<StorePoi> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<StorePoi> q = getWrapper(req.getData());
    List<StorePoiExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<StorePoi> list = this.page(page, q);
      IPage<StorePoiExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, StorePoiExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = StorePoiConverter.INSTANCE.queryPageListRes(this.list(q));
    }

    // 类型转换，  更换枚举 等操作 

    ((StorePoiService) AopContext.currentProxy()).setName(records);
    return DynamicsPage.init(page, records);
  }


  public @Override StorePoiSelectTreeRes storePoiDtoList(StorePoiSelectTreeReq req) {
    List<StorePoi> storePoiList = this.list();
    List<StorePoiDto> storePoiDtoList = StorePoiConverter.INSTANCE.queryListRes(storePoiList);
    List<StorePoiDto> poiDtoList = storePoiDtoList.stream().filter(t -> t.getPoiParentCode().equals(StorePoi.MAX_PARENT_CODE)).toList();
//    setChildPoiDtoList(poiDtoList, storePoiDtoList);

    List<StorePoiDto> retList = ListToTreeUtil.builder(StorePoiDto::getPoiCode, StorePoiDto::getPoiParentCode, StorePoiDto::getChildPoiDtoList,
        StorePoiDto::setChildPoiDtoList, StorePoi.MAX_PARENT_CODE).convert(poiDtoList);
    return new StorePoiSelectTreeRes().setDataList(retList);
  }


  // 以下为私有对象封装
  public @Override void setName(List<? extends StorePoiDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  // @SuppressWarnings("unchecked")
  private MPJLambdaWrapper<StorePoi> getWrapper(StorePoiDto obj) {
    MPJLambdaWrapper<StorePoi> q = new MPJLambdaWrapper<>();

    LambdaQueryUtil.lambdaQueryWrapper(q, obj, StorePoi.class
        // 查询条件
        , BaseEntity::getId // id
        , StorePoi::getPoiParentCode // 上级编码
        , StorePoi::getPoiCode // poi编码
        , StorePoi::getPoiName // poi名称
        , StorePoi::getPoiLevel // 层级
        , StorePoi::getPoiPath // poi路径
        , StorePoi::getCreateUserName // 创建人姓名
        , StorePoi::getUpdateUserName // 修改人姓名
    );

    q.orderByDesc(StorePoi::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<StorePoi> page) {

    tableHeaderService.listByBizKey(page, "StorePoiService#queryPageList");

  }


}

