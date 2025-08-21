package com.olivia.sdk.ann;

import java.lang.annotation.*;

/**
 * 设置用户名注解，用于标记标记需要自动填充创建者/更新者用户名的方法。
 * <p>
 * 用于根据用户ID自动关联并设置对应的用户名，支持单对象和集合对象处理。
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SetUserName {

  /**
   * 是否处理集合类型的返回结果。
   * <p>
   * true表示方法返回值为集合（如List），false表示为单个对象。
   *
   * @return 是否处理集合，默认true
   */
  boolean isList() default true;

  /**
   * 创建者ID的字段名。
   * <p>
   * 用于从对象中获取创建者ID，进而关联用户名。
   *
   * @return 创建者ID字段名，默认"createBy"
   */
  String createBy() default "createBy";

  /**
   * 创建者用户名的目标字段名。
   * <p>
   * 用于指定填充创建者用户名的字段。
   *
   * @return 创建者用户名字段名，默认"createUserName"
   */
  String createUserName() default "createUserName";

  /**
   * 更新者ID的字段名。
   * <p>
   * 用于从对象中获取更新者ID，进而关联用户名。
   *
   * @return 更新者ID字段名，默认"updateBy"
   */
  String updateBy() default "updateBy";

  /**
   * 更新者用户名的目标字段名。
   * <p>
   * 用于指定填充更新者用户名的字段。
   *
   * @return 更新者用户名字段名，默认"updateUserName"
   */
  String updateUserName() default "updateUserName";
}
