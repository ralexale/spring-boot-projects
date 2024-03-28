package com.ralexale.springboot.interceptor.springboothttpinterceptors.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("timeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(
    LoadingTimeInterceptor.class
  );

  @SuppressWarnings("null")
  @Override
  public boolean preHandle(
    HttpServletRequest request,
    HttpServletResponse response,
    Object handler
  ) throws Exception {
    // hacemos esto para obtener el nombre del método o  el endpoint
    HandlerMethod controller = ((HandlerMethod) handler);

    logger.info(
      "LoadingTimeInterceptor: preHandle() entrando ... " +
      controller.getMethod().getName()
    );

    long start = System.currentTimeMillis();

    request.setAttribute("start", start);

    // estamos creado un sleep para aumentar el tiempo de la request
    Random random = new Random();
    int delay = random.nextInt(500);
    Thread.sleep(delay);

    // creamos el objeto que vamos a retornar en la response
    Map<String, String> json = new HashMap<>();
    // le damos el nombre a la key y el value
    json.put("error", "no tienes acceso a esta página");
    json.put("date", new Date().toString());

    // luego lo convertimos a un texto con estructura json
    // o sea un JSON.stringify
    ObjectMapper mapper = new ObjectMapper();
    String jsonString = mapper.writeValueAsString(json);

    response.setContentType("application/json");
    response.setStatus(401);
    response.getWriter().write(jsonString);

    // return true;
    return false;
  }

  @SuppressWarnings("null")
  @Override
  public void postHandle(
    HttpServletRequest request,
    HttpServletResponse response,
    Object handler,
    ModelAndView modelAndView
  ) throws Exception {
    long end = System.currentTimeMillis();

    // obtenemos los datos de la request
    long start = (long) request.getAttribute("start");
    long result = end - start;

    logger.info("El tiempo en el cual se realizó la request fue en: " + result);
    logger.info(
      "LoadingTimeInterceptor: postHandle() saliendo ... " +
      ((HandlerMethod) handler).getMethod().getName()
    );
  }
}
