package com.ralexale.springboot.app.aop.springbootaop.aop;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Aspect
@Component
public class GreetingAspect {

  private Logger logger = LoggerFactory.getLogger(getClass());

  //   @Before(
  //     "execution(String com.ralexale.springboot.app.aop.springbootaop.services.GreetingService.sayHello(..))"
  //   )

  @Before("GreetingServicePointCuts.greetingLoggerPointCut()")
  public void loggerBefore(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();
    String args = Arrays.toString(joinPoint.getArgs());

    logger.info("Before the method : " + method + " with the args: " + args);
  }

  @After("GreetingServicePointCuts.greetingLoggerPointCut()")
  public void loggerAfter(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();
    String args = Arrays.toString(joinPoint.getArgs());

    logger.info("After the method : " + method + " with the args: " + args);
  }

  @AfterReturning("GreetingServicePointCuts.greetingLoggerPointCut()")
  public void loggerReturning(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();
    String args = Arrays.toString(joinPoint.getArgs());

    logger.info("After return : " + method + " with the args: " + args);
  }

  @AfterThrowing("GreetingServicePointCuts.greetingLoggerPointCut()")
  public void loggerAfterThrowing(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();
    String args = Arrays.toString(joinPoint.getArgs());

    logger.info(
      "After throw exception : " + method + " with the args: " + args
    );
  }

  @Around("GreetingServicePointCuts.greetingLoggerPointCut()")
  public Object loggerAround(ProceedingJoinPoint joinPoint) throws Throwable {
    String method = joinPoint.getSignature().getName();
    String args = Arrays.toString(joinPoint.getArgs());
    Object result = null;
    try {
      logger.info("The method " + method + "() with the params " + args);
      result = joinPoint.proceed();
      logger.info("The method " + method + "return the result:" + result);
      return result;
    } catch (Throwable e) {
      logger.error("Error in the call of the method " + method + "()");
      throw e;
    }
    // return result;
  }
}
