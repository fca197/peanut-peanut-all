package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.olivia.peanut.aps.model.ApsOrderGoodsSaleConfig;
import com.olivia.peanut.aps.model.ApsOrderUser;
import com.olivia.peanut.aps.service.*;
import com.olivia.sdk.model.KVEntity;
import com.olivia.sdk.utils.BaseEntity;
import jakarta.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ApsOrderFieldServiceImpl implements ApsOrderFieldService {

  @Resource
  ApsOrderUserService apsOrderUserService;

  @Resource
  ApsOrderGoodsSaleConfigService apsOrderGoodsSaleConfigService;

  private static Map<String, Object> changeMapKey(ApsOrderUser t, String keyTmp) {
    Map<String, Object> beanToMap = BeanUtil.beanToMap(t, false, true);
    if (StringUtils.isBlank(keyTmp)) {
      return beanToMap;
    }
    return beanToMap.entrySet().stream()
        .collect(Collectors.toMap(
            entry -> keyTmp + entry.getKey(),
            Map.Entry::getValue,
            (oldValue, newValue) -> oldValue, // 冲突时保留旧值
            HashMap::new
        ));
  }

  @Override
  public Map<Long, Map<String, Object>> orderFieldSetValue(List<Long> orderIdList,
      List<KVEntity> orderUserFieldList, List<KVEntity> saleFieldList,
      Map<Long, Map<String, Object>> orderExtMap) {
    if (CollUtil.isEmpty(orderIdList)) {
      return Map.of();
    }
    Map<Long, Map<String, Object>> allOrderMap = SpringUtil.getBean(ApsOrderService.class).listByIds(orderIdList)
        .stream()
        .collect(Collectors.toMap(BaseEntity::getId, t -> BeanUtil.beanToMap(t, false, true)));
    Map<Long, Map<String, Object>> retMap = new HashMap<>(allOrderMap.size());

    Map<Long, Map<String, Object>> userMap = new HashMap<>();
    if (CollUtil.isNotEmpty(orderUserFieldList)) {
      userMap.putAll(this.apsOrderUserService.list(
              new LambdaQueryWrapper<ApsOrderUser>().in(ApsOrderUser::getOrderId, orderIdList)).stream()
          .collect(Collectors.toMap(ApsOrderUser::getOrderId,
              t -> changeMapKey(t, orderUserFieldList.getLast().getKeyTmp()))));
    }
    Map<Long, List<ApsOrderGoodsSaleConfig>> saleMap = new HashMap<>();

    if (CollUtil.isNotEmpty(saleFieldList)) {
      saleMap.putAll(this.apsOrderGoodsSaleConfigService.list(
              new LambdaQueryWrapper<ApsOrderGoodsSaleConfig>().in(ApsOrderGoodsSaleConfig::getOrderId,
                  orderIdList).in(ApsOrderGoodsSaleConfig::getConfigParentId,
                  saleFieldList.stream().map(KVEntity::getValue).toList())).stream()
          .collect(Collectors.groupingBy(ApsOrderGoodsSaleConfig::getOrderId)));
    }
    orderIdList.forEach(orderId -> {
      Map<String, Object> orderMap = allOrderMap.get(orderId);
      // 合并扩展数据
      if (orderExtMap != null) {
        orderMap.putAll(orderExtMap.getOrDefault(orderId, Map.of()));
      }

      // 合并用户数据（使用空安全操作）
      orderMap.putAll(userMap.getOrDefault(orderId, Map.of()));

      // 合并销售配置数据
      saleMap.getOrDefault(orderId, List.of()).forEach(config -> {
        orderMap.put("sale_" + config.getConfigParentId(), config.getConfigId());
        orderMap.put("sale_" + config.getConfigParentId() + "_name", config.getConfigName());
      });

      retMap.put(orderId, orderMap);
    });

    return retMap;
  }
}
