package com.ralexale.springboot.error.springbooterror.controllers;

import com.ralexale.springboot.error.springbooterror.exceptions.UserNotFoundException;
import com.ralexale.springboot.error.springbooterror.models.Error;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class HandlerExceptionController {

  // el signo de pregunta en el genérico es como un any
  @ExceptionHandler({ ArithmeticException.class })
  public ResponseEntity<Error> divisionBy0(Exception exception) {
    Error error = new Error();

    error.setDate(new Date());
    error.setError("Error división por 0");
    error.setMessage(exception.getMessage());
    error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

    // return ResponseEntity
    //   .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
    //   .body(error);

    return ResponseEntity.internalServerError().body(error);
  }

  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<Error> notFoundEx(NoResourceFoundException exception) {
    Error error = new Error();

    error.setDate(new Date());
    error.setMessage(exception.getMessage());
    error.setError("Api rest not found");
    error.setStatus(HttpStatus.NOT_FOUND.value());

    return ResponseEntity.status(404).body(error);
  }

  @ExceptionHandler(NumberFormatException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Map<String, Object> numberFormatEx(NumberFormatException exception) {
    Map<String, Object> error = new HashMap<>();

    error.put("date", new Date().toString());
    error.put("message", exception.getMessage());
    error.put("Error", "Number with wrong format");

    // para convertir un int a un string podemos sumarle un string vació
    // error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value() + "");

    error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value() + "");

    return error;
  }

  @ExceptionHandler(
    { NoSuchElementException.class, UserNotFoundException.class }
  )
  public ResponseEntity<Error> userNotFoundEx(Exception exception) {
    Error error = new Error();

    error.setDate(new Date());
    error.setMessage(exception.getMessage());
    error.setError("Not found user");
    error.setStatus(HttpStatus.NOT_FOUND.value());

    return ResponseEntity.status(404).body(error);
  }

  @ExceptionHandler(HttpMessageNotWritableException.class)
  public ResponseEntity<Error> userWithNoRoleEx(
    HttpMessageNotWritableException exception
  ) {
    Error error = new Error();

    error.setDate(new Date());
    error.setMessage(exception.getMessage());
    error.setError("the user don't have role assigned");
    error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

    return ResponseEntity.status(500).body(error);
  }
}
