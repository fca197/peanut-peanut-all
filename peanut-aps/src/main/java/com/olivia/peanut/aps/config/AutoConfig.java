package com.olivia.peanut.aps.config;

import cn.hutool.system.SystemUtil;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Options;
import com.olivia.sdk.config.PeanutProperties;
import com.olivia.sdk.utils.RunUtils;
import com.olivia.sdk.utils.SoLoad;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AutoConfig {


  @Resource
  PeanutProperties peanutProperties;

  @PostConstruct
  public void init() {
    RunUtils.asyncRun("loadLib", () -> {
      log.info("load or-tools {}", SystemUtil.getOsInfo().getName());
      if (!SystemUtil.getOsInfo().isWindows()) {
        SoLoad.loadJNILibrary(peanutProperties.getOrtoolsSoPath());
      }
      AviatorEvaluator.getInstance().setOption(Options.OPTIMIZE_LEVEL, AviatorEvaluator.COMPILE);
      log.info("load or-tools {} success", SystemUtil.getOsInfo().getName());

    });
  }
}
