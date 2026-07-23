package com.example.order_services.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoudException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFoundExcpetion(OrderNotFoudException exception) {
        ErrorResponse response = new ErrorResponse(
                exception.getMessage(),
                "NOT FOUND",
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
