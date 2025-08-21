package com.olivia.sdk.utils.model;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class LambdaQuerySelectReq<T, R> {

  /**
   * 实体类字段函数表达式 用于类型安全地指定查询字段，如 Entity::getId
   */
  private SFunction<T, R> function;

  /**
   * 查询选择类型 用于指定该字段的查询处理方式
   */
  private LambdaQueryUtilSelectType selectType;

}
