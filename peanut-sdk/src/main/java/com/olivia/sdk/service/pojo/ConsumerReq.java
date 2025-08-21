package com.olivia.sdk.service.pojo;

import java.util.List;

/***
 * 消费请求对象
 */
public interface ConsumerReq {

  /**
   * 获取业务标识列表 标识当前请求需要由哪些消费者服务处理，对应ConsumerService中的bizKey返回值
   *
   * @return 业务标识列表，不能为空
   */
  List<String> getBizKeyList();

//  Runnable run();
}
