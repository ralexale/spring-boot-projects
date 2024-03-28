package com.ralexale.springboot.calendar.interceptor.springbootcalendar.controllers;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

  @GetMapping("/foo")
  public ResponseEntity<?> foo(HttpServletRequest request) {
    Map<String, Object> data = new HashMap<>();

    data.put("title", "bienvenidos al sistema de atención!");
    data.put("date", new Date().toString());
    data.put("message", request.getAttribute("message"));

    return ResponseEntity.ok(data);
  }
}
