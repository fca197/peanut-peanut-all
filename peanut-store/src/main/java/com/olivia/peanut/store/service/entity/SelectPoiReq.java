package com.olivia.peanut.store.service.entity;

import java.util.List;
import lombok.Data;

@Data
public class SelectPoiReq {

  private String keywords;
  private List<String> poiList;

  private Integer pageNum = 1;
  private Integer pageSize = 10;
}
