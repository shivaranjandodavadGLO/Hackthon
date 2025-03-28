package com.hackthon.Orders.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExceptionDetails.class)
    public ResponseEntity<ErrorInfo> invalidId(ExceptionDetails invalidIdException) {
        ErrorInfo errorInfo = new ErrorInfo(invalidIdException.getMessage(), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.OK);
    }

}
