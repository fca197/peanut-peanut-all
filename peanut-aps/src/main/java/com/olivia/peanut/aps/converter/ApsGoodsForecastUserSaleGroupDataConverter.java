package com.olivia.peanut.aps.converter;

import com.olivia.peanut.aps.api.entity.apsGoodsForecastUserSaleGroupData.*;
import com.olivia.peanut.aps.model.ApsGoodsForecastUserSaleGroupData;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApsGoodsForecastUserSaleGroupDataConverter {

  ApsGoodsForecastUserSaleGroupDataConverter INSTANCE = Mappers.getMapper(ApsGoodsForecastUserSaleGroupDataConverter.class);

  ApsGoodsForecastUserSaleGroupData insertReq(ApsGoodsForecastUserSaleGroupDataInsertReq req);

  ApsGoodsForecastUserSaleGroupData updateReq(ApsGoodsForecastUserSaleGroupDataUpdateByIdReq req);

  List<ApsGoodsForecastUserSaleGroupDataDto> queryListRes(List<ApsGoodsForecastUserSaleGroupData> list);

  List<ApsGoodsForecastUserSaleGroupDataExportQueryPageListInfoRes> queryPageListRes(List<ApsGoodsForecastUserSaleGroupData> list);

  List<ApsGoodsForecastUserSaleGroupData> importReq(List<ApsGoodsForecastUserSaleGroupDataImportReq> reqList);
}

