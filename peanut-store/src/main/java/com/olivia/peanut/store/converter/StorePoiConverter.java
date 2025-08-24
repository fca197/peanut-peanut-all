package com.olivia.peanut.store.converter;

import com.olivia.peanut.store.api.entity.storePoi.*;
import com.olivia.peanut.store.model.StorePoi;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StorePoiConverter {

  StorePoiConverter INSTANCE = Mappers.getMapper(StorePoiConverter.class);

  StorePoi insertReq(StorePoiInsertReq req);

  StorePoi updateReq(StorePoiUpdateByIdReq req);

  List<StorePoiDto> queryListRes(List<StorePoi> list);

  List<StorePoiExportQueryPageListInfoRes> queryPageListRes(List<StorePoi> list);

  List<StorePoi> importReq(List<StorePoiImportReq> reqList);
}

