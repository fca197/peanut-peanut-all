package com.olivia.sdk.utils.fastjson;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import org.apache.commons.lang3.StringUtils;

public class Str2BooleanConverter implements Converter<Boolean> {


  @Override
  public Boolean convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
    return StringUtils.equalsAnyIgnoreCase(cellData.getStringValue(), "true", "是", "yes", "y", "1", "t");
  }
}
