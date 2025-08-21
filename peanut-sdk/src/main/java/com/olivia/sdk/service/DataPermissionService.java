package com.olivia.sdk.service;

import com.olivia.sdk.enums.DataPermissionRetType;
import java.io.Serializable;
import java.util.List;

/**
 * 数据权限服务接口，用于处理不同维度的数据权限过滤逻辑。
 *
 * <p>实现类应根据具体权限类型（如用户、部门、角色）提供相应的过滤实现，
 * 并通过{@link #getType()}方法返回唯一的权限类型标识。
 */
public interface DataPermissionService {

  /**
   * 获取权限类型标识。
   *
   * @return 权限类型字符串（非空），如"USER"、"DEPARTMENT"、"ROLE"等
   */
  String getType();

  /**
   * 获取权限过滤结果的数据类型。
   *
   * @return 数据权限返回类型枚举，指定{@link #filterValueList()}返回值的具体形态
   */
  DataPermissionRetType getRetType();

  /**
   * 执行权限过滤并返回有效数据标识列表。
   *
   * @return 经过权限过滤后的可序列化对象列表，不会返回null（空权限时返回空列表）
   */
  List<Serializable> filterValueList();
}
