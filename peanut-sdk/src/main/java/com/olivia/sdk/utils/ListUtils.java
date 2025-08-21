package com.olivia.sdk.utils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * 列表操作工具类，提供列表笛卡尔积计算等增强功能 基于JDK21特性优化，提升集合操作的性能和可读性
 */
public final class ListUtils {

  /**
   * 私有构造函数，防止工具类实例化
   */
  private ListUtils() {
    throw new AssertionError("工具类不允许实例化");
  }

  /**
   * 计算多个列表的笛卡尔积 笛卡尔积是指多个集合中所有可能的元素组合，每个组合包含每个集合中的一个元素
   *
   * @param lists 输入的列表集合，每个列表代表一组元素
   * @param <T>   列表元素的类型
   * @return 包含所有可能组合的列表，每个组合是一个列表；如果输入为空则返回空列表
   * @throws NullPointerException 如果输入列表包含null元素
   */
  public static <T> List<List<T>> cartesianProduct(List<List<T>> lists) {
    // 处理空输入（JDK21优化：使用Objects.requireNonNullElse）
    List<List<T>> inputLists = Objects.requireNonNullElse(lists, Collections.emptyList());

    // 检查输入列表中是否包含null元素
    inputLists.forEach(list -> Objects.requireNonNull(list, "输入列表中不能包含null元素"));

    // 处理空列表情况
    if (inputLists.isEmpty()) {
      return Collections.emptyList();
    }

    // 计算笛卡尔积（JDK21 Stream API优化）
    return inputLists.stream()
        // 初始值：包含一个空列表的流
        .reduce(Stream.of(Collections.<T>emptyList()),
            // 累积器：将当前部分结果与下一个列表进行组合
            (partialStream, currentList) -> partialStream.flatMap(partialList ->
                currentList.stream().map(element ->
                    // 组合部分结果和当前元素，创建新列表
                    Stream.concat(partialList.stream(), Stream.of(element)).toList()
                )
            ),
            // 组合器：并行流中合并结果（保持顺序）
            Stream::concat)
        // 转换为不可变列表（JDK21优化：toList()返回不可变列表）
        .toList();
  }
}
