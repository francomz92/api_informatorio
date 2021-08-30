package com.actividad_final.api_informatorio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiHandlerExceptions {


   @ResponseStatus(HttpStatus.NOT_FOUND)
   @ExceptionHandler(value = {ResourceNotFound.class})
   @ResponseBody
   public ApiErrorMessage carritoNotFound(RuntimeException ex, HttpServletRequest request) {
      return new ApiErrorMessage(ex, request.getRequestURI());
   }
}
