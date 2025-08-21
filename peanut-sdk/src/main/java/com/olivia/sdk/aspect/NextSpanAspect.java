package com.olivia.sdk.aspect;

import com.olivia.sdk.utils.MDCUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 分布式追踪Span管理切面，用于自动生成和传递下一级Span ID。
 * <p>
 * 通过AOP环绕通知在服务实现方法调用前后管理MDC中的Span ID， 实现分布式追踪链路的自动维护。
 */
@Slf4j
@Aspect
@Component
public class NextSpanAspect {

  /**
   * 定义切入点：匹配com.olivia.peanut包下所有服务实现类的方法。
   */
  @Pointcut("execution(* com.olivia.peanut.*.service.impl.*.*(..))")
  public void spanIdPointcut() {
    // 切入点定义，无具体实现
  }

  /**
   * 环绕通知：在方法执行前后管理Span ID。
   * <p>
   * 执行流程： 1. 保存当前Span ID 2. 生成下一级Span ID并设置到MDC 3. 执行目标方法 4. 恢复原始Span ID
   *
   * @param joinPoint 连接点对象，包含目标方法信息
   * @return 目标方法的执行结果
   * @throws Throwable 目标方法可能抛出的异常
   */
  @Around("spanIdPointcut()")
  public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
    // 保存当前Span ID
    String originalSpanId = MDCUtils.getSpanId();

    try {
      // 生成并设置下一级Span ID
      MDCUtils.nextSpanId();
      // 执行目标方法
      return joinPoint.proceed();
    } finally {
      // 恢复原始Span ID，确保线程安全
      MDCUtils.setSpanId(originalSpanId);
    }
  }
}
