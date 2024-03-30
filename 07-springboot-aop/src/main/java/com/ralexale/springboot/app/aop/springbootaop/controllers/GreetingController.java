package com.ralexale.springboot.app.aop.springbootaop.controllers;

import com.ralexale.springboot.app.aop.springbootaop.services.GreetingService;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

  @Autowired
  private GreetingService greetingService;

  @GetMapping("/greeting")
  public ResponseEntity<?> greetingEntity() {
    return ResponseEntity.ok(
      Collections.singletonMap(
        "greetings",
        greetingService.sayHello("Alejandro", "good morning")
      )
    );
  }

  @GetMapping("/greeting-error")
  public ResponseEntity<?> greetingErrorEntity() {
    return ResponseEntity.ok(
      Collections.singletonMap(
        "greetings",
        greetingService.sayHelloError("Alejandro", "good morning")
      )
    );
  }
}
