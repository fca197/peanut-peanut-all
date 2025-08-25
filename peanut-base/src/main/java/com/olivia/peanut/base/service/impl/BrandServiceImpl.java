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
import com.olivia.peanut.base.mapper.BrandMapper;
import com.olivia.peanut.base.model.Brand;
import com.olivia.peanut.base.converter.BrandConverter;
import com.olivia.peanut.base.service.BrandService;
import cn.hutool.core.collection.CollUtil;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.peanut.base.api.entity.brand.*;
import com.olivia.sdk.service.SetNameService;

/**
 * 品牌表(Brand)表服务实现类
 *
 * @author admin
 * @since 2025-08-25 15:03:19
 */
@Service("brandService")
@Transactional
public class BrandServiceImpl extends MPJBaseServiceImpl<BrandMapper, Brand> implements BrandService {

  // final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override BrandQueryListRes queryList(BrandQueryListReq req) {

    MPJLambdaWrapper<Brand> q = getWrapper(req.getData());
    List<Brand> list = this.list(q);

    List<BrandDto> dataList = BrandConverter.INSTANCE.queryListRes(list);
    ((BrandService) AopContext.currentProxy()).setName(dataList);
    return new BrandQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<BrandExportQueryPageListInfoRes> queryPageList(BrandExportQueryPageListReq req) {

    DynamicsPage<Brand> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<Brand> q = getWrapper(req.getData());
    List<BrandExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<Brand> list = this.page(page, q);
      IPage<BrandExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, BrandExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = BrandConverter.INSTANCE.queryPageListRes(this.list(q));
    }

    // 类型转换，  更换枚举 等操作 

    ((BrandService) AopContext.currentProxy()).setName(records);
    return DynamicsPage.init(page, records);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends BrandDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  // @SuppressWarnings("unchecked")
  private MPJLambdaWrapper<Brand> getWrapper(BrandDto obj) {
    MPJLambdaWrapper<Brand> q = new MPJLambdaWrapper<>();

    LambdaQueryUtil.lambdaQueryWrapper(q, obj, Brand.class
        // 查询条件
        , BaseEntity::getId // id
        , Brand::getFactoryId // 工厂ID
        , Brand::getBrandCode // ${column.comment}
        , Brand::getBrandName // ${column.comment}
        , Brand::getBrandStatus // ${column.comment}
        , Brand::getIsUsed // ${column.comment}
        , Brand::getCreateUserName // 创建人姓名
        , Brand::getUpdateUserName // 修改人姓名
    );

    q.orderByDesc(Brand::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<Brand> page) {

    tableHeaderService.listByBizKey(page, "BrandService#queryPageList");

  }


}

