package com.olivia.peanut.aps.converter;

import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetailMachineUseTime.*;
import com.olivia.peanut.aps.model.ApsSchedulingDayConfigVersionDetailMachine;
import com.olivia.peanut.aps.model.ApsSchedulingDayConfigVersionDetailMachineUseTime;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApsSchedulingDayConfigVersionDetailMachineUseTimeConverter {

  ApsSchedulingDayConfigVersionDetailMachineUseTimeConverter INSTANCE = Mappers.getMapper(ApsSchedulingDayConfigVersionDetailMachineUseTimeConverter.class);

  ApsSchedulingDayConfigVersionDetailMachineUseTime insertReq(ApsSchedulingDayConfigVersionDetailMachineUseTimeInsertReq req);

  ApsSchedulingDayConfigVersionDetailMachineUseTime updateReq(ApsSchedulingDayConfigVersionDetailMachineUseTimeUpdateByIdReq req);

  List<ApsSchedulingDayConfigVersionDetailMachineUseTimeDto> queryListRes(List<ApsSchedulingDayConfigVersionDetailMachineUseTime> list);

  List<ApsSchedulingDayConfigVersionDetailMachineUseTimeExportQueryPageListInfoRes> queryPageListRes(List<ApsSchedulingDayConfigVersionDetailMachineUseTime> list);

  List<ApsSchedulingDayConfigVersionDetailMachineUseTime> importReq(List<ApsSchedulingDayConfigVersionDetailMachineUseTimeImportReq> reqList);

}

