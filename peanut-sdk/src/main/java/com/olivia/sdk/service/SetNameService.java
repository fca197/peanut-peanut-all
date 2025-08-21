package com.olivia.sdk.service;

import com.olivia.sdk.service.pojo.SetNamePojo;
import java.util.List;
import java.util.Objects;

/**
 * 提供对象名称设置功能的服务接口，用于批量为对象集合设置名称属性。
 *
 * <p>通过{@link SetNamePojo}定义的映射关系，将指定的名称值设置到目标对象的对应属性中。
 */
public interface SetNameService {

  /**
   * 为对象集合批量设置名称属性。
   *
   * @param list            待设置名称的对象集合，不能为null
   * @param setNamePojoList 名称设置规则集合，包含属性映射和值来源，不能为null
   * @throws NullPointerException 如果list或setNamePojoList为null
   */
  void setName(List<?> list, List<SetNamePojo> setNamePojoList);

  /**
   * 为对象集合批量设置名称属性（可变参数重载）。
   *
   * @param list            待设置名称的对象集合，不能为null
   * @param setNamePojoList 名称设置规则可变参数，包含属性映射和值来源
   * @throws NullPointerException 如果list为null
   */
  default void setName(List<?> list, SetNamePojo... setNamePojoList) {
    Objects.requireNonNull(list, "待设置名称的对象集合不能为null");
    setName(list, List.of(setNamePojoList));
  }
}
