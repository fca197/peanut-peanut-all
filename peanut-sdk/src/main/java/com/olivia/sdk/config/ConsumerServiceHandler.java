package com.olivia.sdk.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.google.common.base.Preconditions;
import com.olivia.sdk.service.ConsumerService;
import com.olivia.sdk.service.impl.PrintConsoleConsumerServiceImpl;
import com.olivia.sdk.service.pojo.ConsumerReq;
import com.olivia.sdk.utils.RunUtils;
import jakarta.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 消费者服务处理器 负责管理所有ConsumerService实现类，并根据业务键路由消费请求
 */
@Slf4j
@Component
public class ConsumerServiceHandler {

  /**
   * 存储业务键与对应消费者服务的映射关系 使用ConcurrentHashMap替代synchronizedMap，提供更好的并发性能
   */
  private static final Map<String, ConsumerService> CONSUMER_SERVICES = new ConcurrentHashMap<>();

  /**
   * 消费数据 根据请求中的业务键列表，路由到对应的消费者服务进行处理
   *
   * @param req 消费请求对象，包含业务键列表和待消费数据
   */
  public static void consume(ConsumerReq req) {
    // 使用Google Preconditions进行参数校验，增强代码可读性
    Preconditions.checkArgument(Objects.nonNull(req), "消费请求对象(req)不能为null");
    Preconditions.checkArgument(CollUtil.isNotEmpty(req.getBizKeyList()), "业务键列表(bizKey)不能为null或空");

    List<String> keyList = req.getBizKeyList();
    List<Runnable> tasks = new ArrayList<>(keyList.size());

    // JDK 21增强的for-each循环，结合方法引用提升代码简洁性
    keyList.forEach(key -> tasks.add(() -> getConsumerService(key).consumer(req)));

    // 执行所有消费任务
    RunUtils.run("consumer:" + String.join(",", keyList), tasks);
  }

  /**
   * 获取指定业务键对应的消费者服务 如果未找到，则返回默认的控制台打印服务
   *
   * @param bizKey 业务键
   * @return 对应的消费者服务
   */
  private static ConsumerService getConsumerService(String bizKey) {
    Preconditions.checkNotNull(bizKey, "业务键(bizKey)不能为null");

    // 查找对应的服务，未找到则使用默认服务
    return CONSUMER_SERVICES.getOrDefault(bizKey, SpringUtil.getBean(PrintConsoleConsumerServiceImpl.class));
  }

  /**
   * 供测试或调试使用，获取所有注册的消费者服务
   *
   * @return 不可修改的消费者服务映射表
   */
  public static Map<String, ConsumerService> getConsumerServices() {
    return Collections.unmodifiableMap(CONSUMER_SERVICES);
  }

  /**
   * 初始化方法，在Spring容器启动时加载所有ConsumerService实现类 使用@PostConstruct确保在依赖注入完成后执行
   */
  @PostConstruct
  public void init() {
    // 双重检查锁定，避免重复初始化
    if (CONSUMER_SERVICES.isEmpty()) {
      synchronized (ConsumerServiceHandler.class) {
        if (CONSUMER_SERVICES.isEmpty()) {
          loadConsumerServices();
          log.info("消费者服务初始化完成，共加载 {} 个服务", CONSUMER_SERVICES.size());
        }
      }
    }
  }

  /**
   * 加载所有ConsumerService实现类并注册到映射表中
   */
  private void loadConsumerServices() {
    // 获取Spring容器中所有ConsumerService类型的Bean
    Collection<ConsumerService> consumerServices = SpringUtil.getBeansOfType(ConsumerService.class).values();
    if (CollUtil.isEmpty(consumerServices)) {
      log.warn("未发现任何ConsumerService实现类");
      return;
    }

    // 注册每个消费者服务，处理可能的键冲突
    consumerServices.forEach(service -> {
      String bizKey = service.bizKey();
      Preconditions.checkNotNull(bizKey, "ConsumerService %s 的bizKey不能为null", service.getClass().getName());

      // 检查是否有键冲突
      ConsumerService existingService = CONSUMER_SERVICES.putIfAbsent(bizKey, service);
      if (Objects.nonNull(existingService)) {
        log.warn("业务键冲突: 键[{}]已被服务[{}]占用，新服务[{}]将覆盖旧服务", bizKey, existingService.getClass().getSimpleName(), service.getClass().getSimpleName());
        CONSUMER_SERVICES.put(bizKey, service);
      }
    });
  }
}
