package com.olivia.peanut.store.service.entity;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SelectAroundPoiReq implements GdBaseReq {

  private String keywords;
  private List<String> types;
  private String locationLngLat;
  private Integer radius;
  // citycode 或 adcode;
  private String region;

  //按距离排序：distance；综合排序：weight
  private String sortrule;

  private Integer pageNum;
  private Integer pageSize;


}
