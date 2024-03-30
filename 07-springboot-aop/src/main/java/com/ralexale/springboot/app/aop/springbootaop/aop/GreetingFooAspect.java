package com.ralexale.springboot.app.aop.springbootaop.aop;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
@Aspect
public class GreetingFooAspect {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Before(
    "execution(* com.ralexale.springboot.app.aop.springbootaop.services.*.*(..))"
  )
  public void loggerBefore(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();
    String args = Arrays.toString(joinPoint.getArgs());

    logger.info(
      "Before the method : " + method + " invoke with the args: " + args
    );
  }

  @After(
    "execution(* com.ralexale.springboot.app.aop.springbootaop.services.GreetingService.*(..))"
  )
  public void loggerAfter(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();
    String args = Arrays.toString(joinPoint.getArgs());

    logger.info(
      "After the method : " + method + "invoke with the args: " + args
    );
  }
}
