package com.felipeassisdev.walletpp.controller;

import com.felipeassisdev.walletpp.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ProblemDetail handleCustomException(CustomException e) {
        return e.toProblemDetail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleArgumentNotValidException(MethodArgumentNotValidException e) {
        var fieldErrors = e.getFieldErrors().stream()
                .map(f -> new InvalidParam(f.getField(), f.getDefaultMessage()))
                .toList();

        var problemDetails = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        problemDetails.setTitle("Your request params are invalid");
        problemDetails.setProperty("invalid-params", fieldErrors);
        
        return problemDetails;
    }


    private record InvalidParam(String field, String reason) {
    }
}

