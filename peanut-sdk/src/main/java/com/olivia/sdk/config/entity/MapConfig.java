package com.olivia.sdk.config.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MapConfig {


  /***
   * 高德web key
   *  https://console.amap.com/dev/key/app  Web服务 类型
   */
  private String gaoDeWebKey;

  private Integer aroundMaxPageNum = 3;

}
