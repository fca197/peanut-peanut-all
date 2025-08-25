package com.olivia.peanut.base.converter;

import com.olivia.peanut.base.api.entity.brand.*;
import com.olivia.peanut.base.model.Brand;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandConverter {

  BrandConverter INSTANCE = Mappers.getMapper(BrandConverter.class);

  Brand insertReq(BrandInsertReq req);

  Brand updateReq(BrandUpdateByIdReq req);

  List<BrandDto> queryListRes(List<Brand> list);

  List<BrandExportQueryPageListInfoRes> queryPageListRes(List<Brand> list);

  List<Brand> importReq(List<BrandImportReq> reqList);
}

