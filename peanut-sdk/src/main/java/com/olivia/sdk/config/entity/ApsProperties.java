package com.olivia.sdk.config.entity; // 补充合理的包路径

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * APS系统配置属性类 存储APS（Advanced Planning and Scheduling）相关的配置参数
 */
@Getter
@Setter
@Accessors(chain = true)
public class ApsProperties {

  /**
   * 预测主数据前缀 用于构建预测主数据的标识前缀，区分不同类型的预测数据
   */
  private String forecastMainPrefix;

  /**
   * 预测主数据后缀 用于构建预测主数据的标识后缀，补充说明预测数据的特性
   */
  private String forecastMainSuffix;

  /**
   * 获取完整的预测主数据标识（前缀+后缀）
   *
   * @return 组合后的预测主数据标识，如果前缀或后缀为null则返回null
   */
  public String getForecastMainIdentifier() {
    if (forecastMainPrefix == null || forecastMainSuffix == null) {
      return null;
    }
    return forecastMainPrefix + forecastMainSuffix;
  }

  /**
   * 检查预测主数据标识是否完整（前缀和后缀都不为空）
   *
   * @return 如果前缀和后缀都不为空则返回true，否则返回false
   */
  public boolean isForecastMainIdentifierComplete() {
    return forecastMainPrefix != null && !forecastMainPrefix.isEmpty() &&
        forecastMainSuffix != null && !forecastMainSuffix.isEmpty();
  }
}
