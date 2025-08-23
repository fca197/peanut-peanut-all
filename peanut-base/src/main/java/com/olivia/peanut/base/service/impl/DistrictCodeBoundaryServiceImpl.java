package com.olivia.peanut.base.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.thread.ThreadUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.base.api.entity.districtCodeBoundary.*;
import com.olivia.peanut.base.converter.DistrictCodeBoundaryConverter;
import com.olivia.peanut.base.mapper.DistrictCodeBoundaryMapper;
import com.olivia.peanut.base.model.DistrictCode;
import com.olivia.peanut.base.model.DistrictCodeBoundary;
import com.olivia.peanut.base.service.*;
import com.olivia.sdk.ann.RedissonLockAnn;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.*;
import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Resource
  QueryDistrictBoundaryService queryDistrictBoundaryService;

  @Resource
  DistrictCodeService districtCodeService;

  public @Override DistrictCodeBoundaryQueryListRes queryList(DistrictCodeBoundaryQueryListReq req) {

    List<DistrictCode> districtCodeList = this.districtCodeService.getDistrictCodesByParentCode(req.getParentCode());

    if (CollUtil.isEmpty(districtCodeList)) {
      return new DistrictCodeBoundaryQueryListRes();
    }
    List<String> codeList = districtCodeList.stream().map(DistrictCode::getCode).toList();
    MPJLambdaWrapper<DistrictCodeBoundary> q = new MPJLambdaWrapper<>(DistrictCodeBoundary.class).in(DistrictCodeBoundary::getDistrictCode, codeList);
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

  @Override
  @RedissonLockAnn(keyExpression = "#req.districtCode")
  public List<DistrictCodeBoundary> saveBoundary(DistrictCodeBoundary req) {
    List<DistrictCodeBoundary> saveList = new ArrayList<>();

//    DistrictCodeBoundary districtCodeBoundary = this.getOne(
//        new LambdaQueryWrapper<>(DistrictCodeBoundary.class).eq(DistrictCodeBoundary::getDistrictCode, req.getDistrictCode()));
//    if (Objects.nonNull(districtCodeBoundary)) {
//      return saveList;
//    }

    List<DistrictCode> districtCodeList = this.districtCodeService.getDistrictCodesByParentCode(req.getDistrictCode());
    List<String> codeList = districtCodeList.stream().map(DistrictCode::getCode).collect(Collectors.toList());
    codeList.add(req.getDistrictCode());

    Set<String> hasCodeSet = this.list(
            new LambdaQueryWrapper<>(DistrictCodeBoundary.class).select(DistrictCodeBoundary::getDistrictCode).in(DistrictCodeBoundary::getDistrictCode, codeList)).stream()
        .map(DistrictCodeBoundary::getDistrictCode).collect(Collectors.toSet());

    codeList.removeIf(hasCodeSet::contains);

    if (CollUtil.isNotEmpty(codeList)) {
      codeList.forEach(code -> {
        ThreadUtil.sleep(500);
        DistrictCodeBoundary tmp = this.queryDistrictBoundaryService.queryDistrictCodeFormMap(code);
//        saveList.add(tmp);
      });
//      this.saveBatch(saveList);

    }
    return saveList;
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
    );

    q.orderByDesc(DistrictCodeBoundary::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<DistrictCodeBoundary> page) {

    tableHeaderService.listByBizKey(page, "DistrictCodeBoundaryService#queryPageList");

  }


}

