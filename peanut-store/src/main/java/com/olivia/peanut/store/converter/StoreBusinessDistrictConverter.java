package com.olivia.peanut.store.converter;

import com.olivia.peanut.store.api.entity.storeBusinessDistrict.*;
import com.olivia.peanut.store.model.StoreBusinessDistrict;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StoreBusinessDistrictConverter {

  StoreBusinessDistrictConverter INSTANCE = Mappers.getMapper(StoreBusinessDistrictConverter.class);

  StoreBusinessDistrict insertReq(StoreBusinessDistrictInsertReq req);

  StoreBusinessDistrict updateReq(StoreBusinessDistrictUpdateByIdReq req);

  List<StoreBusinessDistrictDto> queryListRes(List<StoreBusinessDistrict> list);

  List<StoreBusinessDistrictExportQueryPageListInfoRes> queryPageListRes(List<StoreBusinessDistrict> list);

  List<StoreBusinessDistrict> importReq(List<StoreBusinessDistrictImportReq> reqList);
}

