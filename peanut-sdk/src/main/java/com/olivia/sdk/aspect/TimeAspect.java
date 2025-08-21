package com.olivia.sdk.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 方法执行时间记录切面，用于监控标注了{@link com.olivia.sdk.ann.Timed}注解的方法执行耗时。
 * <p>
 * 通过AOP环绕通知记录方法的开始和结束时间，计算并输出执行耗时， 主要用于性能分析和慢方法监控。
 */
@Aspect
@Order(-1)
@Component
@Slf4j
public class TimeAspect {

  /**
   * 定义切入点：匹配所有标注了{@link com.olivia.sdk.ann.Timed}注解的方法
   */
  @Pointcut("@annotation(com.olivia.com.olivia.ann.sdk.Timed)")
  public void timedPointcut() {
    // 切入点定义，无具体实现
  }

  /**
   * 环绕通知：记录方法执行时间
   *
   * @param joinPoint 连接点对象，包含目标方法信息
   * @return 目标方法的执行结果
   * @throws Throwable 目标方法可能抛出的异常
   */
  @Around("timedPointcut()")
  public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
    // 记录方法开始时间
    long startTime = System.currentTimeMillis();

    try {
      // 执行目标方法
      return joinPoint.proceed();
    } finally {
      // 计算并记录执行时间
      long executionTime = System.currentTimeMillis() - startTime;

      // 输出调试日志
      if (log.isDebugEnabled()) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        log.debug("方法执行时间 - {}.{}: {}ms", className, methodName, executionTime);
      }
    }
  }
}
