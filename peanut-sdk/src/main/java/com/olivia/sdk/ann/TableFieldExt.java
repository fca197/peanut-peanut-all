package com.olivia.sdk.ann;

import java.lang.annotation.*;

/**
 * 表字段扩展注解，用于标记实体类、方法或字段的特殊属性。
 * <p>
 * 主要用于扩展表字段的元数据信息，如标识关键字字段等， 可辅助ORM框架或自定义处理逻辑识别特殊字段。
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableFieldExt {

  /**
   * 是否为关键字字段。
   * <p>
   * 用于标识当前字段是否为业务关键字字段（非数据库主键）， 可用于数据校验、索引构建等场景。
   *
   * @return true表示是关键字字段，默认false
   */
  boolean isKey() default false;
}
