package com.bookmark.infrastructure.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
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

  @Around("restControllerMethods()")
  public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
    var attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

    var request = attributes.getRequest();

    logger.info("{} {}", request.getMethod(), request.getRequestURI());

    return joinPoint.proceed();
  }
}
