package com.olivia.sdk.ann;

import java.lang.annotation.*;

/**
 * 操作日志注解，用于标记需要记录操作日志的类或方法。
 * <p>
 * 可用于记录操作内容、访问URL、业务标识等信息，便于系统审计和追踪。
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Oplog {

  /**
   * 操作内容描述。
   *
   * @return 操作内容字符串，默认空字符串
   */
  String content() default "";

  /**
   * 操作访问的URL。
   *
   * @return URL字符串，默认空字符串
   */
  String url() default "";

  /**
   * 业务唯一标识字段名。
   *
   * @return 业务键字段名，默认空字符串
   */
  String businessKey() default "";

  /**
   * 操作参数名称。
   *
   * @return 参数名称字符串，默认空字符串
   */
  String paramName() default "";

  /**
   * 操作备注信息。
   *
   * @return 备注字符串，默认空字符串
   */
  String remark() default "";

  /**
   * 业务类型。
   *
   * @return 业务类型字符串，默认空字符串
   */
  String businessType() default "";

  /**
   * 请求中租户ID的字段名。
   *
   * @return 租户ID字段名，默认空字符串
   */
  String reqTenantIdFieldName() default "";
}
