package com.olivia.sdk.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class SoLoad {

  public static void loadJNILibrary(String libraryPath) {
    try {
      if (StringUtils.isAnyBlank(libraryPath)) {
        log.error("  libraryPath  {} is null or empty ", libraryPath);
        return;
      }
      Runtime.getRuntime().load(libraryPath);
//      System.load(libraryPath);
      log.info("库文件 libraryPath :{}  加载成功 ", libraryPath);
    } catch (Exception e) {
      log.error("加载库文件失败: {}", e.getMessage(), e);
    }
  }
}
