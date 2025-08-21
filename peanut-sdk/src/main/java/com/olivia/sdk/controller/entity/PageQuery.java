package com.olivia.sdk.controller.entity;

import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 通用分页查询实体类 封装分页参数（页码、每页条数）和查询条件实体
 *
 * @param <T> 查询条件实体类型，需继承自BaseEntity
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class PageQuery<T extends BaseEntity<T>> {

  /**
   * 每页记录数 默认值为10，最小为1
   */
  private Long pageSize = 10L;

  /**
   * 页码 默认值为1，最小为1
   */
  private Long pageNum = 1L;

  /**
   * 查询条件实体 存储具体的业务查询参数
   */
  private T data;

  /**
   * 构造方法，初始化分页参数和查询条件
   *
   * @param pageNum  页码
   * @param pageSize 每页记录数
   * @param data     查询条件实体
   */
  public PageQuery(Long pageNum, Long pageSize, T data) {
    this.setPageNum(pageNum);
    this.setPageSize(pageSize);
    this.data = data;
  }

  /**
   * 设置每页记录数 确保每页记录数不小于1
   *
   * @param pageSize 每页记录数
   */
  public void setPageSize(Long pageSize) {
    this.pageSize = (pageSize == null || pageSize < 1) ? 10L : pageSize;
  }

  /**
   * 设置页码 确保页码不小于1
   *
   * @param pageNum 页码
   */
  public void setPageNum(Long pageNum) {
    this.pageNum = (pageNum == null || pageNum < 1) ? 1L : pageNum;
  }

  /**
   * 计算分页查询的偏移量（从第几条记录开始查询）
   *
   * @return 偏移量
   */
  public long getOffset() {
    return (pageNum - 1) * pageSize;
  }

  /**
   * 获取TySQL分页查询的limit参数
   *
   * @return 分页参数数组，第一个元素为偏移量，第二个元素为每页记录数
   */
  public long[] getLimitParams() {
    return new long[]{getOffset(), pageSize};
  }
}
