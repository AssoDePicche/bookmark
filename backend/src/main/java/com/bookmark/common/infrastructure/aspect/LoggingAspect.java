package com.bookmark.common.infrastructure.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggingAspect {
  private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

  @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
  public void restControllerMethods() {}

  @AfterThrowing(pointcut = "execution(* com.bookmark.*.*.*(..))", throwing = "exception")
  public void log(JoinPoint joinPoint, Throwable throwable) {
    String methodName = joinPoint.getSignature().getName();

    String className = joinPoint.getTarget().getClass().getSimpleName();

    logger.error("Exception in {}.{}(): {}", className, methodName, throwable.getMessage());
  }

  @Around("restControllerMethods()")
  public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
    var attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

    var request = attributes.getRequest();

    logger.info("{} {}", request.getMethod(), request.getRequestURI());

    return joinPoint.proceed();
  }
}
