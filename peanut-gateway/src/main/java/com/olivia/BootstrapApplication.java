package com.olivia;

import com.olivia.sdk.config.ServiceNotice;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/***
 *
 */
@Slf4j
@EnableCaching
//@EnableAspectJAutoProxy
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@MapperScan(basePackages = {"com.olivia.peanut.*.mapper", "com.olivia.sdk.mapper"})
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
@EnableTransactionManagement
public class BootstrapApplication {

  public static void main(String[] args) {
    try {
      SpringApplication.run(BootstrapApplication.class, args);
      log.info(">>>>>>>  BootstrapApplication  start success >>>>>>>");
      ServiceNotice.notifyStart();
    } catch (Exception e) {
      ServiceNotice.notifyErrorStop(e);
    }
  }
}
