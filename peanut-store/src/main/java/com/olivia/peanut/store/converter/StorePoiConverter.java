package com.olivia.peanut.store.converter;

import com.olivia.peanut.store.api.entity.storePoi.*;
import com.olivia.peanut.store.model.StorePoi;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StorePoiConverter {

  StorePoiConverter INSTANCE = Mappers.getMapper(StorePoiConverter.class);

  StorePoi insertReq(StorePoiInsertReq req);

  StorePoi updateReq(StorePoiUpdateByIdReq req);

  List<StorePoiDto> queryListRes(List<StorePoi> list);

  List<StorePoiExportQueryPageListInfoRes> queryPageListRes(List<StorePoi> list);

  List<StorePoi> importReq(List<StorePoiImportReq> reqList);
}

