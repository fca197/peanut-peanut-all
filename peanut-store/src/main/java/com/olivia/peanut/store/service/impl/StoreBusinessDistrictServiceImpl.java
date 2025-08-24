package com.olivia.peanut.store.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.peanut.store.api.entity.storeBusinessDistrict.*;
import com.olivia.peanut.store.converter.StoreBusinessDistrictConverter;
import com.olivia.peanut.store.mapper.StoreBusinessDistrictMapper;
import com.olivia.peanut.store.model.StoreBusinessDistrict;
import com.olivia.peanut.store.service.StoreBusinessDistrictService;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.*;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商圈(StoreBusinessDistrict)表服务实现类
 *
 * @author admin
 * @since 2025-08-24 21:01:55
 */
@Service("storeBusinessDistrictService")
@Transactional
public class StoreBusinessDistrictServiceImpl extends MPJBaseServiceImpl<StoreBusinessDistrictMapper, StoreBusinessDistrict> implements StoreBusinessDistrictService {

  // final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override StoreBusinessDistrictQueryListRes queryList(StoreBusinessDistrictQueryListReq req) {

    MPJLambdaWrapper<StoreBusinessDistrict> q = getWrapper(req.getData());
    List<StoreBusinessDistrict> list = this.list(q);

    List<StoreBusinessDistrictDto> dataList = StoreBusinessDistrictConverter.INSTANCE.queryListRes(list);
    ((StoreBusinessDistrictService) AopContext.currentProxy()).setName(dataList);
    return new StoreBusinessDistrictQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<StoreBusinessDistrictExportQueryPageListInfoRes> queryPageList(StoreBusinessDistrictExportQueryPageListReq req) {

    DynamicsPage<StoreBusinessDistrict> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<StoreBusinessDistrict> q = getWrapper(req.getData());
    List<StoreBusinessDistrictExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<StoreBusinessDistrict> list = this.page(page, q);
      IPage<StoreBusinessDistrictExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, StoreBusinessDistrictExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = StoreBusinessDistrictConverter.INSTANCE.queryPageListRes(this.list(q));
    }

    // 类型转换，  更换枚举 等操作 

    ((StoreBusinessDistrictService) AopContext.currentProxy()).setName(records);
    return DynamicsPage.init(page, records);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends StoreBusinessDistrictDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  // @SuppressWarnings("unchecked")
  private MPJLambdaWrapper<StoreBusinessDistrict> getWrapper(StoreBusinessDistrictDto obj) {
    MPJLambdaWrapper<StoreBusinessDistrict> q = new MPJLambdaWrapper<>();

    LambdaQueryUtil.lambdaQueryWrapper(q, obj, StoreBusinessDistrict.class
        // 查询条件
        , BaseEntity::getId // id
        , StoreBusinessDistrict::getBrandId // 品牌ID
        , StoreBusinessDistrict::getBusinessDistrictCode // 编码
        , StoreBusinessDistrict::getBusinessDistrictName // 名称
        , StoreBusinessDistrict::getBusinessDistrictDesc // 描述
        , StoreBusinessDistrict::getBusinessDistrictAddress // 地址
        , StoreBusinessDistrict::getCountryCode // 国家编码
        , StoreBusinessDistrict::getProvinceCode // 城市编码
        , StoreBusinessDistrict::getCityCode // 城市编码
        , StoreBusinessDistrict::getAreaCode // 城市编码
        , StoreBusinessDistrict::getCountryName // 国家编码
        , StoreBusinessDistrict::getProvinceName // 城市编码
        , StoreBusinessDistrict::getCityName // 城市编码
        , StoreBusinessDistrict::getAreaName // 城市编码
        , StoreBusinessDistrict::getBusinessDistrictRadius // 半径
        , StoreBusinessDistrict::getBusinessDistrictLevelId // 商圈级别ID
        , StoreBusinessDistrict::getBusinessDistrictTypeId // 商圈类别ID
        , StoreBusinessDistrict::getCenterLat // 纬度
        , StoreBusinessDistrict::getCenterLng // 经度
        , StoreBusinessDistrict::getCreateUserName // 创建人姓名
        , StoreBusinessDistrict::getUpdateUserName // 修改人姓名
    );

    q.orderByDesc(StoreBusinessDistrict::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<StoreBusinessDistrict> page) {

    tableHeaderService.listByBizKey(page, "StoreBusinessDistrictService#queryPageList");

  }


}

