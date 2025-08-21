package com.olivia.sdk.config;


import cn.hutool.extra.spring.SpringUtil;
import com.olivia.sdk.utils.RunUtils;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.AbstractProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class BeanConfig {

  @PostConstruct
  public void init() {

    HikariDataSource hikariDataSource = SpringUtil.getBean(HikariDataSource.class);
    hikariDataSource.setThreadFactory(Thread.ofVirtual().name("hikariDataSource", 1).factory());
//    hikariDataSource.setScheduledExecutor(RunUtils.getVirtualExecutor());
  }

  /**
   * 自定义 Tomcat 协议处理器，使用虚拟线程
   */
  @Bean
  public TomcatProtocolHandlerCustomizer<AbstractProtocol<?>> protocolHandlerCustomizer() {
    return connector -> {
      // 将 Tomcat 的协议处理器线程池替换为虚拟线程工厂
      connector.setExecutor(RunUtils.getVirtualExecutor());
    };
  }
}
