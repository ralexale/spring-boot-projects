package com.ralexale.springboot.app.aop.springbootaop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingServicePointCuts {

  @Pointcut(
    "execution(* com.ralexale.springboot.app.aop.springbootaop.services.*.*(..))"
  )
  public void greetingLoggerPointCut() {}
}
