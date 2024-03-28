package com.ralexale.springboot.calendar.interceptor.springbootcalendar;

import com.ralexale.springboot.calendar.interceptor.springbootcalendar.interceptors.CalendarInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

  @Autowired
  private CalendarInterceptor calendarInterceptor;

  @SuppressWarnings("null")
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(calendarInterceptor);
  }
}
