package com.glinka.empik.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({NumberFormatException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponse wrongFormatException(NumberFormatException ex, HttpServletRequest request){
        ExceptionResponse error =  new ExceptionResponse();
        error.setErrorMessage(ex.getMessage());
        error.setRequestedURI(request.getRequestURI());
        return error;
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponse illegalArgumentException(IllegalArgumentException ex, HttpServletRequest request){
        ExceptionResponse error =  new ExceptionResponse();
        error.setErrorMessage(ex.getMessage());
        error.setRequestedURI(request.getRequestURI());
        return error;
    }

}
