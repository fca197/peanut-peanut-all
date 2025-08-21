package com.olivia.peanut.aps.config;

import cn.hutool.system.SystemUtil;
import com.google.ortools.Loader;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Options;
import com.olivia.sdk.utils.RunUtils;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AutoConfig {

  @PostConstruct
  public void init() {
    RunUtils.asyncRun("loadLib", () -> {
      // 非window下 加载 or-tools 库
      // window下使用修改pom.xml中 ortools-java依赖排除的节点删除,增加window依赖
      log.info("load or-tools {}", SystemUtil.getOsInfo().getName());
      if (!SystemUtil.getOsInfo().isWindows()) {
        Loader.loadNativeLibraries();
      }
      AviatorEvaluator.getInstance().setOption(Options.OPTIMIZE_LEVEL, AviatorEvaluator.COMPILE);
      log.info("load or-tools {} success", SystemUtil.getOsInfo().getName());

    });
  }
}
