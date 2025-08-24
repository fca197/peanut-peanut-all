package com.olivia.peanut.store.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.peanut.store.api.entity.storeBusinessDistrictType.*;
import com.olivia.peanut.store.converter.StoreBusinessDistrictTypeConverter;
import com.olivia.peanut.store.mapper.StoreBusinessDistrictTypeMapper;
import com.olivia.peanut.store.model.StoreBusinessDistrictType;
import com.olivia.peanut.store.service.StoreBusinessDistrictTypeService;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.*;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商圈类型(StoreBusinessDistrictType)表服务实现类
 *
 * @author admin
 * @since 2025-08-24 21:10:18
 */
@Service("storeBusinessDistrictTypeService")
@Transactional
public class StoreBusinessDistrictTypeServiceImpl extends MPJBaseServiceImpl<StoreBusinessDistrictTypeMapper, StoreBusinessDistrictType> implements
    StoreBusinessDistrictTypeService {

  // final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override StoreBusinessDistrictTypeQueryListRes queryList(StoreBusinessDistrictTypeQueryListReq req) {

    MPJLambdaWrapper<StoreBusinessDistrictType> q = getWrapper(req.getData());
    List<StoreBusinessDistrictType> list = this.list(q);

    List<StoreBusinessDistrictTypeDto> dataList = StoreBusinessDistrictTypeConverter.INSTANCE.queryListRes(list);
    ((StoreBusinessDistrictTypeService) AopContext.currentProxy()).setName(dataList);
    return new StoreBusinessDistrictTypeQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<StoreBusinessDistrictTypeExportQueryPageListInfoRes> queryPageList(StoreBusinessDistrictTypeExportQueryPageListReq req) {

    DynamicsPage<StoreBusinessDistrictType> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<StoreBusinessDistrictType> q = getWrapper(req.getData());
    List<StoreBusinessDistrictTypeExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<StoreBusinessDistrictType> list = this.page(page, q);
      IPage<StoreBusinessDistrictTypeExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, StoreBusinessDistrictTypeExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = StoreBusinessDistrictTypeConverter.INSTANCE.queryPageListRes(this.list(q));
    }

    // 类型转换，  更换枚举 等操作 

    ((StoreBusinessDistrictTypeService) AopContext.currentProxy()).setName(records);
    return DynamicsPage.init(page, records);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends StoreBusinessDistrictTypeDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  // @SuppressWarnings("unchecked")
  private MPJLambdaWrapper<StoreBusinessDistrictType> getWrapper(StoreBusinessDistrictTypeDto obj) {
    MPJLambdaWrapper<StoreBusinessDistrictType> q = new MPJLambdaWrapper<>();

    LambdaQueryUtil.lambdaQueryWrapper(q, obj, StoreBusinessDistrictType.class
        // 查询条件
        , BaseEntity::getId // id
        , StoreBusinessDistrictType::getBusinessDistrictTypeName // 类型名称
        , StoreBusinessDistrictType::getBusinessDistrictTypeCode // 类型编码
        , StoreBusinessDistrictType::getBusinessDistrictTypeDesc // 类型描述
        , StoreBusinessDistrictType::getCreateUserName // 创建人姓名
        , StoreBusinessDistrictType::getUpdateUserName // 修改人姓名
    );

    q.orderByDesc(StoreBusinessDistrictType::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<StoreBusinessDistrictType> page) {

    tableHeaderService.listByBizKey(page, "StoreBusinessDistrictTypeService#queryPageList");

  }


}

