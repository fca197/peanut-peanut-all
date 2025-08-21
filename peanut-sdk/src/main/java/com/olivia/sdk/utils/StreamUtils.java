package com.olivia.sdk.utils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 流处理工具类，提供增强的集合转换功能，支持空键映射和高效集合转换 基于JDK21特性优化，提供更安全、高效的流操作方法
 */
public final class StreamUtils {

  /**
   * 私有构造函数，防止工具类实例化
   */
  private StreamUtils() {
    throw new AssertionError("工具类不允许实例化");
  }

  /**
   * 将列表转换为支持空键的Map JDK21优化：利用LinkedHashMap的性能提升，支持null键存储（默认Collectors.toMap不支持）
   *
   * @param list        源列表
   * @param keyMapper   键映射函数
   * @param valueMapper 值映射函数
   * @param <T>         源列表元素类型
   * @param <K>         映射后键类型
   * @param <U>         映射后值类型
   * @return 包含所有元素映射结果的Map，支持null键
   */
  public static <T, K, U> Map<K, U> toMapWithNullKeys(List<T> list, Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper) {
    // JDK21增强：使用List.isEmpty()进行空判断，性能更优
    if (list == null || list.isEmpty()) {
      return new LinkedHashMap<>(0); // 返回空集合而非null，避免NPE
    }
    return list.stream().collect(toMapWithNullKeys(keyMapper, valueMapper));
  }

  /**
   * 创建支持空键的Collector，用于Stream.collect()方法 JDK21优化：利用Collector特性增强，提升并行流处理效率
   *
   * @param keyMapper   键映射函数
   * @param valueMapper 值映射函数
   * @param <T>         源元素类型
   * @param <K>         键类型
   * @param <U>         值类型
   * @return 支持空键的Collector实例
   */
  public static <T, K, U> Collector<T, ?, Map<K, U>> toMapWithNullKeys(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper) {
    // JDK21优化：使用LinkedHashMap作为容器，保持插入顺序且支持null键
    return Collector.of(LinkedHashMap::new, (map, element) -> {
          K key = keyMapper.apply(element);
          U value = valueMapper.apply(element);
          map.put(key, value);
        }, (left, right) -> {
          left.putAll(right);
          return left;
        }, Collector.Characteristics.IDENTITY_FINISH, Collector.Characteristics.UNORDERED // 允许无序合并提升性能
    );
  }

  /**
   * 将列表转换为另一种类型的列表（不可修改） JDK21优化：利用Stream.toList()返回的不可变列表，减少内存占用
   *
   * @param list   源列表
   * @param mapper 转换函数
   * @param <I>    源元素类型
   * @param <R>    目标元素类型
   * @return 转换后的不可修改列表
   */
  public static <I, R> List<R> list2List(List<I> list, Function<I, R> mapper) {
    return list2List(list, mapper, false);
  }

  /**
   * 将列表转换为另一种类型的列表，可指定是否允许修改 JDK21优化：增强空集合处理，使用ArrayList的容量优化构造函数
   *
   * @param list      源列表
   * @param mapper    转换函数
   * @param canModify 是否允许修改返回的列表
   * @param <I>       源元素类型
   * @param <R>       目标元素类型
   * @return 转换后的列表（不可修改或可修改）
   */
  public static <I, R> List<R> list2List(List<I> list, Function<I, R> mapper, boolean canModify) {
    // JDK21增强：处理null和空列表的边界情况
    if (list == null || list.isEmpty()) {
      return canModify ? new ArrayList<>(0) : List.of();
    }

    // JDK21优化：Stream.toList()返回的列表更高效（不可变）
    List<R> result = list.stream().map(mapper).toList();

    // 如需可修改列表，使用ArrayList的容量优化构造函数（JDK21增强）
    return canModify ? new ArrayList<>(result) : result;
  }

  /**
   * 将列表转换为Set集合 JDK21优化：利用Collectors.toSet()的性能提升，自动去重
   *
   * @param list   源列表
   * @param mapper 转换函数
   * @param <I>    源元素类型
   * @param <R>    目标元素类型
   * @return 转换后的Set集合
   */
  public static <I, R> Set<R> list2set(List<I> list, Function<I, R> mapper) {
    // JDK21增强：空集合处理，返回不可变空集而非null
    if (list == null || list.isEmpty()) {
      return Set.of();
    }
    return list.stream().map(mapper).collect(Collectors.toSet());
  }
}
