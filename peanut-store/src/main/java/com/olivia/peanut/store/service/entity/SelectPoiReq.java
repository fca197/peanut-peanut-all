package com.olivia.peanut.store.service.entity;

import java.util.List;
import lombok.Data;

@Data
public class SelectPoiReq implements GdBaseReq {

  private String keywords;
  private List<String> types;

  private Integer pageNum = 1;
  private Integer pageSize = 10;
}
