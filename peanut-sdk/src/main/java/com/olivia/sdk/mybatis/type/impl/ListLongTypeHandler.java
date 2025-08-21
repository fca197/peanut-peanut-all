package com.olivia.sdk.mybatis.type.impl;

import com.olivia.sdk.mybatis.type.ListMyBaseTypeHandler;
import com.olivia.sdk.utils.JSON;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

@Slf4j

@MappedTypes(List.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class ListLongTypeHandler extends ListMyBaseTypeHandler<List<Long>> {


  private List<Long> getListLongFromJSON(String val) {
    if (StringUtils.isBlank(val)) {
      return List.of();
    }
    return JSON.readList(val, Long.class);
  }


  @Override
  public List<Long> parse(String json) {
    return getListLongFromJSON(json);
  }


}
