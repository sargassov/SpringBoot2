package com.example.springboot1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TerminatorExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ApplicationError> catchProductNotFoundException(ProductNotFoundException pnfe){
        return new ResponseEntity<>(new ApplicationError(HttpStatus.NOT_FOUND.value(), pnfe.getMessage()), HttpStatus.NOT_FOUND);
    }
}
