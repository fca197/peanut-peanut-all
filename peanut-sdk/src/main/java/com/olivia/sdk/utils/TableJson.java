package com.olivia.sdk.utils;


import com.olivia.sdk.utils.DynamicsPage.Header;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 *
 */
public class TableJson {

  public static Map<String, Object> toJson(List<Header> headerList, List<? extends Object> dataList) {

    Map<String, Object> map = new HashMap<>();
    map.put("headerList", headerList);
    map.put("dataList", dataList);
    return map;
  }


}
