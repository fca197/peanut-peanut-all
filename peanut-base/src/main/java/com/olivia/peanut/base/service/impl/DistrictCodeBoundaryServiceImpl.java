package com.olivia.peanut.base.service.impl;

import org.springframework.aop.framework.AopContext;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import jakarta.annotation.Resource;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.LambdaQueryUtil;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.olivia.peanut.base.mapper.DistrictCodeBoundaryMapper;
import com.olivia.peanut.base.model.DistrictCodeBoundary;
import com.olivia.peanut.base.converter.DistrictCodeBoundaryConverter;
import com.olivia.peanut.base.service.DistrictCodeBoundaryService;
import cn.hutool.core.collection.CollUtil;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.peanut.base.api.entity.districtCodeBoundary.*;
import com.olivia.sdk.service.SetNameService;

/**
 * 地区边界(DistrictCodeBoundary)表服务实现类
 *
 * @author admin
 * @since 2025-08-22 13:33:38
 */
@Service("districtCodeBoundaryService")
@Transactional
public class DistrictCodeBoundaryServiceImpl extends MPJBaseServiceImpl<DistrictCodeBoundaryMapper, DistrictCodeBoundary> implements DistrictCodeBoundaryService {

  // final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override DistrictCodeBoundaryQueryListRes queryList(DistrictCodeBoundaryQueryListReq req) {

    MPJLambdaWrapper<DistrictCodeBoundary> q = getWrapper(req.getData());
    List<DistrictCodeBoundary> list = this.list(q);

    List<DistrictCodeBoundaryDto> dataList = DistrictCodeBoundaryConverter.INSTANCE.queryListRes(list);
    ((DistrictCodeBoundaryService) AopContext.currentProxy()).setName(dataList);
    return new DistrictCodeBoundaryQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<DistrictCodeBoundaryExportQueryPageListInfoRes> queryPageList(DistrictCodeBoundaryExportQueryPageListReq req) {

    DynamicsPage<DistrictCodeBoundary> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<DistrictCodeBoundary> q = getWrapper(req.getData());
    List<DistrictCodeBoundaryExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<DistrictCodeBoundary> list = this.page(page, q);
      IPage<DistrictCodeBoundaryExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, DistrictCodeBoundaryExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = DistrictCodeBoundaryConverter.INSTANCE.queryPageListRes(this.list(q));
    }

    // 类型转换，  更换枚举 等操作 

    ((DistrictCodeBoundaryService) AopContext.currentProxy()).setName(records);
    return DynamicsPage.init(page, records);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends DistrictCodeBoundaryDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  @SuppressWarnings("unchecked")
  private MPJLambdaWrapper<DistrictCodeBoundary> getWrapper(DistrictCodeBoundaryDto obj) {
    MPJLambdaWrapper<DistrictCodeBoundary> q = new MPJLambdaWrapper<>();

    LambdaQueryUtil.lambdaQueryWrapper(q, obj, DistrictCodeBoundary.class
        // 查询条件
        , BaseEntity::getId // id
        , DistrictCodeBoundary::getDistrictCode // 区域编码
        , DistrictCodeBoundary::getLngList // 经度（Longitude）-180～180
        , DistrictCodeBoundary::getLatList // 纬度（Latitude）0～90
        , DistrictCodeBoundary::getCenterLat // 中心纬度
        , DistrictCodeBoundary::getCenterLon // 中心经度
        , DistrictCodeBoundary::getCreateUserName // 创建人姓名
        , DistrictCodeBoundary::getUpdateUserName // 修改人姓名
    );

    q.orderByDesc(DistrictCodeBoundary::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<DistrictCodeBoundary> page) {

    tableHeaderService.listByBizKey(page, "DistrictCodeBoundaryService#queryPageList");

  }


}

