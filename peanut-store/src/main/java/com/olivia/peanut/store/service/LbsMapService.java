package com.olivia.peanut.store.service;

import com.olivia.peanut.store.service.entity.SelectAroundPoiReq;
import com.olivia.peanut.store.service.entity.SelectPoiReq;

public interface LbsMapService {

  //
  void selectAndInsertCityPoi(SelectPoiReq selectPoiReq);

  /****
   * 周边查询
   *
   */
  void selectAndInsertAroundPoi(SelectAroundPoiReq req);
}
