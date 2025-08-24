package com.olivia.peanut.store.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.olivia.peanut.store.service.LbsMapService;
import com.olivia.peanut.store.service.entity.SelectPoiReq;
import com.olivia.peanut.store.service.impl.dto.GdPlaceTextResponse;
import com.olivia.peanut.store.service.impl.dto.GdPlaceTextResponse.PoisDTO;
import com.olivia.sdk.config.PeanutProperties;
import com.olivia.sdk.utils.HttpClientUtils;
import jakarta.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GdLbsMapServiceImpl implements LbsMapService {

  @Resource
  PeanutProperties peanutProperties;

  @Override
  public void selectAndInsertCityPoi(SelectPoiReq req) {

    if (req.getPageNum() > 3) {
      log.info("pageNum={}, pageSize={} >3 break req:{}", req.getPageNum(), req.getPageSize(), req);
      return;
    }
    String url = "https://restapi.amap.com/v5/place/text?";
    Map<String, Object> params = new HashMap<>();
    params.put("keywords", req.getKeywords());
    List<String> poiList = req.getPoiList();
    if (CollUtil.isNotEmpty(poiList)) {
      params.put("types", String.join(",", poiList));
    }
    params.put("key", peanutProperties.getGaoDeWebKey());
    params.put("city_limit", true);
    params.put("page_size", req.getPageSize());
    params.put("page_num", req.getPageNum());

    params.put("show_fields", "children,business,indoor,navi,photos");
    url = url + params.entrySet().stream().map(t -> t.getKey() + "-" + t.getValue()).collect(Collectors.joining("&"));
    GdPlaceTextResponse placeTextResponse = HttpClientUtils.get(url, GdPlaceTextResponse.class);
    if (Objects.isNull(placeTextResponse)) {
      log.info("placeTextResponse is null {}", req);
      return;
    }
    List<PoisDTO> poisDTOList = placeTextResponse.getPois();
    if (CollUtil.isEmpty(poisDTOList)) {
      log.info("poisDTOList is empty {}", req);
      return;
    }

    //TODO: save
    // converter
    //  XXX
    if (Objects.equals(poisDTOList.size(), req.getPageSize())) {
      req.setPageNum(req.getPageNum() + 1);
      selectAndInsertCityPoi(req);
    }
  }
}
