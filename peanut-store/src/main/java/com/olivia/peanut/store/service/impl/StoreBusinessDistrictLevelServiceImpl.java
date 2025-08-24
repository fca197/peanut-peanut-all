package com.olivia.peanut.store.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.peanut.store.api.entity.storeBusinessDistrictLevel.*;
import com.olivia.peanut.store.converter.StoreBusinessDistrictLevelConverter;
import com.olivia.peanut.store.mapper.StoreBusinessDistrictLevelMapper;
import com.olivia.peanut.store.model.StoreBusinessDistrictLevel;
import com.olivia.peanut.store.service.StoreBusinessDistrictLevelService;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.*;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商圈级别(StoreBusinessDistrictLevel)表服务实现类
 *
 * @author admin
 * @since 2025-08-24 21:10:16
 */
@Service("storeBusinessDistrictLevelService")
@Transactional
public class StoreBusinessDistrictLevelServiceImpl extends MPJBaseServiceImpl<StoreBusinessDistrictLevelMapper, StoreBusinessDistrictLevel> implements
    StoreBusinessDistrictLevelService {

  // final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override StoreBusinessDistrictLevelQueryListRes queryList(StoreBusinessDistrictLevelQueryListReq req) {

    MPJLambdaWrapper<StoreBusinessDistrictLevel> q = getWrapper(req.getData());
    List<StoreBusinessDistrictLevel> list = this.list(q);

    List<StoreBusinessDistrictLevelDto> dataList = StoreBusinessDistrictLevelConverter.INSTANCE.queryListRes(list);
    ((StoreBusinessDistrictLevelService) AopContext.currentProxy()).setName(dataList);
    return new StoreBusinessDistrictLevelQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<StoreBusinessDistrictLevelExportQueryPageListInfoRes> queryPageList(StoreBusinessDistrictLevelExportQueryPageListReq req) {

    DynamicsPage<StoreBusinessDistrictLevel> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<StoreBusinessDistrictLevel> q = getWrapper(req.getData());
    List<StoreBusinessDistrictLevelExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<StoreBusinessDistrictLevel> list = this.page(page, q);
      IPage<StoreBusinessDistrictLevelExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, StoreBusinessDistrictLevelExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = StoreBusinessDistrictLevelConverter.INSTANCE.queryPageListRes(this.list(q));
    }

    // 类型转换，  更换枚举 等操作 

    ((StoreBusinessDistrictLevelService) AopContext.currentProxy()).setName(records);
    return DynamicsPage.init(page, records);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends StoreBusinessDistrictLevelDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  // @SuppressWarnings("unchecked")
  private MPJLambdaWrapper<StoreBusinessDistrictLevel> getWrapper(StoreBusinessDistrictLevelDto obj) {
    MPJLambdaWrapper<StoreBusinessDistrictLevel> q = new MPJLambdaWrapper<>();

    LambdaQueryUtil.lambdaQueryWrapper(q, obj, StoreBusinessDistrictLevel.class
        // 查询条件
        , BaseEntity::getId // id
        , StoreBusinessDistrictLevel::getBusinessDistrictLevelName // 商圈名称
        , StoreBusinessDistrictLevel::getBusinessDistrictLevelDesc // 商圈描述
        , StoreBusinessDistrictLevel::getBusinessDistrictLevelColor // 商圈颜色
        , StoreBusinessDistrictLevel::getCreateUserName // 创建人姓名
        , StoreBusinessDistrictLevel::getUpdateUserName // 修改人姓名
    );

    q.orderByDesc(StoreBusinessDistrictLevel::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<StoreBusinessDistrictLevel> page) {

    tableHeaderService.listByBizKey(page, "StoreBusinessDistrictLevelService#queryPageList");

  }


}

