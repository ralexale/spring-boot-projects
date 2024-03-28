package com.ralexale.springboot.error.springbooterror.controllers;

import com.ralexale.springboot.error.springbooterror.exceptions.UserNotFoundException;
import com.ralexale.springboot.error.springbooterror.models.domain.User;
import com.ralexale.springboot.error.springbooterror.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppController {

  @Autowired
  UserService userService;

  @GetMapping
  public String index() {
    // int value = 100 / 0;

    int value = Integer.parseInt("10");
    System.out.println(value);

    return "status 200";
  }

  @GetMapping("/users")
  public List<User> getAllUsers() {
    return userService.findAll();
  }

  @GetMapping("/users/{id}")
  public User getUserById(@PathVariable String id) {
    User user = userService
      .findById(id)
      .orElseThrow(() ->
        new UserNotFoundException("not found user with the id: " + id)
      );

    // if (user == null) {
    //   throw new UserNotFoundException("not found user with the id: " + id);
    // }

    // return userService.findById(id);
    return user;
  }
}
