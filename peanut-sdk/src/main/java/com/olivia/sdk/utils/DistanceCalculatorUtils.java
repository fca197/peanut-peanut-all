package com.olivia.sdk.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DistanceCalculatorUtils {

  // 地球半径，单位为米
  private static final double EARTH_RADIUS = 6371000;


  public static BigDecimal haversineDistance(BigDecimal lat1, BigDecimal lon1, BigDecimal lat2, BigDecimal lon2) {
    return haversineDistance(lat1.doubleValue(), lon1.doubleValue(), lat2.doubleValue(), lon2.doubleValue());
  }

  /**
   * 计算两个经纬度点之间的距离（米）
   *
   * @param lat1 第一个点的纬度
   * @param lon1 第一个点的经度
   * @param lat2 第二个点的纬度
   * @param lon2 第二个点的经度
   * @return 两点之间的距离（米）
   */
  public static BigDecimal haversineDistance(double lat1, double lon1, double lat2, double lon2) {
    // 将角度转换为弧度
    double dLat = Math.toRadians(lat2 - lat1);
    double dLon = Math.toRadians(lon2 - lon1);
    lat1 = Math.toRadians(lat1);
    lat2 = Math.toRadians(lat2);

    // 应用Haversine公式
    double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    return new BigDecimal(EARTH_RADIUS * c).setScale(6, RoundingMode.HALF_UP);
  }
}