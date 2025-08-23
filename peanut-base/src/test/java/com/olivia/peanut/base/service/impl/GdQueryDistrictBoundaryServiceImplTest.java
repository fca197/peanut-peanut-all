package com.olivia.peanut.base.service.impl;

import com.olivia.peanut.base.model.DistrictCodeBoundary;
import com.olivia.sdk.config.PeanutProperties;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class GdQueryDistrictBoundaryServiceImplTest extends TestCase {


  @Test
  public void test() {
    GdQueryDistrictBoundaryServiceImpl gdDistrictBoundaryService = new GdQueryDistrictBoundaryServiceImpl();
    gdDistrictBoundaryService.peanutProperties = new PeanutProperties().setGaoDeWebKey("15fd63ae8c86929bce65336807b71778");
    DistrictCodeBoundary districtCodeBoundary = gdDistrictBoundaryService.queryDistrictCodeFormMap("370100");
    log.info(districtCodeBoundary.toString());
  }
}