package com.olivia.sdk.ann;

import java.lang.annotation.*;

/**
 * 计时注解，用于标记需要记录执行时间的类或方法。
 * <p>
 * 应用此注解后，框架会自动记录目标类中所有方法或特定方法的执行耗时， 通常用于性能监控、耗时分析和优化。
 * <p>
 * 注解在类上时，对该类中所有方法生效；注解在方法上时，仅对该方法生效。
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Timed {

}
