package com.olivia.sdk.utils;

import com.olivia.sdk.exception.CanIgnoreException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import lombok.extern.slf4j.Slf4j;

/**
 * 数量分配工具类，提供按比例分配总数的功能 支持一维分配，根据比例列表或固定份数分配总数
 */
@Slf4j
public final class CountDisperseUtils {

  /**
   * 私有构造函数，防止工具类实例化
   */
  private CountDisperseUtils() {
    throw new AssertionError("工具类不允许实例化");
  }

  /**
   * 一维分配：将总数按照比例列表分配
   *
   * @param count          总数
   * @param percentageList 比例列表
   * @return 每个比例对应的分配数量
   * @throws CanIgnoreException 如果比例列表为空、包含null值或负数
   */
  public static List<Long> unidimensional(Long count, Collection<Double> percentageList) {
    // 校验输入参数
    if (percentageList == null || percentageList.isEmpty()) {
      return List.of();
    }
    if (percentageList.stream().anyMatch(t -> t == null || t < 0)) {
      throw new CanIgnoreException("比例不能为空或者小于0");
    }
    if (count == null || count <= 0) {
      return percentageList.stream().map(t -> 0L).collect(Collectors.toList());
    }

    // 计算比例总和
    double totalPercentage = percentageList.stream().mapToDouble(Double::doubleValue).sum();
    if (totalPercentage <= 0) {
      throw new CanIgnoreException("比例总和必须大于0");
    }

    // 初步按比例分配
    List<Long> distribution = percentageList.stream().map(percent -> (long) (percent * count / totalPercentage)).collect(Collectors.toCollection(ArrayList::new));

    // 处理分配后可能存在的余数
    long allocatedSum = distribution.stream().mapToLong(Long::longValue).sum();
    long remainder = count - allocatedSum;

    if (remainder > 0) {
      // 找到最大比例的索引，将余数分配给它
      List<Double> percentages = new ArrayList<>(percentageList);
      int maxPercentIndex = percentages.indexOf(percentages.stream().max(Double::compareTo).orElseThrow()); // 此处不会为空，因为已校验非空

      distribution.set(maxPercentIndex, distribution.get(maxPercentIndex) + remainder);
    }

    return distribution;
  }

  /**
   * 一维分配：将总数平均分配到指定份数，支持余数分配策略
   *
   * @param count      总数
   * @param percentage 分配份数
   * @param addFirst   余数是否从第一个元素开始分配
   * @return 分配结果列表
   * @throws CanIgnoreException 如果份数小于等于0
   */
  public static List<Long> unidimensional(Long count, long percentage, boolean addFirst) {
    // 校验输入参数
    if (count == null || count <= 0) {
      return List.of();
    }
    if (percentage <= 0) {
      throw new CanIgnoreException("分配份数必须大于0");
    }
    // 处理份数大于总数的情况
    if (percentage > count) {
      return LongStream.range(0, percentage).mapToObj(i -> i < count ? 1L : 0L).collect(Collectors.toList());
    }

    // 计算基础分配量和余数
    long base = count / percentage;
    long remainder = count % percentage;

    // 初始化分配列表
    List<Long> distribution = LongStream.range(0, percentage).mapToObj(i -> base).collect(Collectors.toCollection(ArrayList::new));

    // 分配余数
    if (remainder > 0) {
      if (addFirst) {
        // 从头部开始分配余数
        for (int i = 0; i < remainder; i++) {
          distribution.set(i, distribution.get(i) + 1);
        }
      } else {
        // 从尾部开始分配余数
        for (int i = 0; i < remainder; i++) {
          int index = distribution.size() - 1 - i;
          distribution.set(index, distribution.get(index) + 1);
        }
      }
    }

    if (log.isDebugEnabled()) {
      log.debug("数量分配结果: 总数={}, 份数={}, 分配={}", count, percentage, distribution);
    }
    return distribution;
  }

  /**
   * 一维分配：将总数平均分配到指定份数，余数从第一个元素开始分配
   *
   * @param count      总数
   * @param percentage 分配份数
   * @return 分配结果列表
   * @throws CanIgnoreException 如果份数小于等于0
   */
  public static List<Long> unidimensional(Long count, long percentage) {
    return unidimensional(count, percentage, true);
  }
}
