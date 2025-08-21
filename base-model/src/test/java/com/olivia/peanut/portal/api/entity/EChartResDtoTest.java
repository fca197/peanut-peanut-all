package com.olivia.peanut.portal.api.entity;


import com.olivia.sdk.utils.JSON;
import org.junit.Test;

public class EChartResDtoTest {

  @Test
  public void test() {
    EChartResDto eChartResDto = new EChartResDto();
    String jsonString = JSON.toJSONString(eChartResDto);
    System.out.println(jsonString);
  }
}