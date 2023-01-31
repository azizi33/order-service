package com.melita.erp.orderservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.Date;

@RestControllerAdvice
@Slf4j
public class AppExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorDetails errorDetails=new ErrorDetails(new Date(),status.value(),status.toString(),ex.getMessage(),ex.getMessage());
        return  ResponseEntity.status(status.value()).body(errorDetails);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorDetails errorDetails=new ErrorDetails(new Date(),status.value(),status.toString(),ex.getMessage(),ex.getMessage());
        return  ResponseEntity.status(status.value()).body(errorDetails);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorDetails errorDetails=new ErrorDetails(new Date(),status.value(),status.toString(),ex.getMessage(), Collections.singletonList(ex.getMessage()));
        return  ResponseEntity.status(status.value()).body(errorDetails);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<?> unknownException(Exception ex, WebRequest req) {
        ErrorDetails errorDetails=new ErrorDetails(new Date(),500,HttpStatus.INTERNAL_SERVER_ERROR.name(),ex.getMessage(),ex.getMessage());
        return  ResponseEntity.status(500).body(errorDetails);
    }
}
