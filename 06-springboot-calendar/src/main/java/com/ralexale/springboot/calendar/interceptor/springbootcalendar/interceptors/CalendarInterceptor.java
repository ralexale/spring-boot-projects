package com.ralexale.springboot.calendar.interceptor.springbootcalendar.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class CalendarInterceptor implements HandlerInterceptor {

  @Value("${config.calendar.open}")
  private Integer openHour;

  @Value("${config.calendar.close}")
  private Integer closeHour;

  @SuppressWarnings("null")
  @Override
  public boolean preHandle(
    HttpServletRequest request,
    HttpServletResponse response,
    Object handler
  ) throws Exception {
    Calendar calendar = Calendar.getInstance();
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    System.out.println(hour);

    if (hour >= openHour && hour <= closeHour) {
      StringBuilder message = new StringBuilder(
        "Bienvenidos al horario de atención al cliente"
      );
      message.append(", atendemos desde las ");
      message.append(openHour);
      message.append("hrs. ");
      message.append("hasta las ");
      message.append(closeHour);
      message.append("hrs. ");
      message.append("Gracias por su visita. ");

      request.setAttribute("message", message.toString());

      return true;
    }

    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> json = new HashMap<>();

    StringBuilder message = new StringBuilder(
      "Error El horario de atención es a partir de las "
    );

    message.append(openHour);
    message.append(" a las ");
    message.append(closeHour);
    message.append("hrs.");

    json.put("message", message.toString());
    json.put("date", new Date());

    response.setContentType("application/json");
    response.setStatus(401);
    response.getWriter().write(mapper.writeValueAsString(json));

    return false;
  }

  @SuppressWarnings("null")
  @Override
  public void postHandle(
    HttpServletRequest request,
    HttpServletResponse response,
    Object handler,
    @Nullable ModelAndView modelAndView
  ) throws Exception {}
}
