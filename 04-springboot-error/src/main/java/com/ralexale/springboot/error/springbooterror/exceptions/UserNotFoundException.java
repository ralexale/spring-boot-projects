package com.ralexale.springboot.error.springbooterror.exceptions;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String message) {
    super(message);
  }
}
