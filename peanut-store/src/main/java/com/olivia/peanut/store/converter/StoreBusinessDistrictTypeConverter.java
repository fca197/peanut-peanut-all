package com.olivia.peanut.store.converter;

import com.olivia.peanut.store.api.entity.storeBusinessDistrictType.*;
import com.olivia.peanut.store.model.StoreBusinessDistrictType;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StoreBusinessDistrictTypeConverter {

  StoreBusinessDistrictTypeConverter INSTANCE = Mappers.getMapper(StoreBusinessDistrictTypeConverter.class);

  StoreBusinessDistrictType insertReq(StoreBusinessDistrictTypeInsertReq req);

  StoreBusinessDistrictType updateReq(StoreBusinessDistrictTypeUpdateByIdReq req);

  List<StoreBusinessDistrictTypeDto> queryListRes(List<StoreBusinessDistrictType> list);

  List<StoreBusinessDistrictTypeExportQueryPageListInfoRes> queryPageListRes(List<StoreBusinessDistrictType> list);

  List<StoreBusinessDistrictType> importReq(List<StoreBusinessDistrictTypeImportReq> reqList);
}

