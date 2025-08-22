package com.olivia.peanut.base.converter;

import com.olivia.peanut.base.api.entity.districtCodeBoundary.*;
import com.olivia.peanut.base.model.DistrictCodeBoundary;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DistrictCodeBoundaryConverter {

  DistrictCodeBoundaryConverter INSTANCE = Mappers.getMapper(DistrictCodeBoundaryConverter.class);

  DistrictCodeBoundary insertReq(DistrictCodeBoundaryInsertReq req);

  DistrictCodeBoundary updateReq(DistrictCodeBoundaryUpdateByIdReq req);

  List<DistrictCodeBoundaryDto> queryListRes(List<DistrictCodeBoundary> list);

  List<DistrictCodeBoundaryExportQueryPageListInfoRes> queryPageListRes(List<DistrictCodeBoundary> list);

  List<DistrictCodeBoundary> importReq(List<DistrictCodeBoundaryImportReq> reqList);
}

