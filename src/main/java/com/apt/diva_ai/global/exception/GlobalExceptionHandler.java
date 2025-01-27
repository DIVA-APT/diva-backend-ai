package com.apt.diva_ai.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> customExceptionHandler(CustomException ex) {
        ErrorResponse response = ex.toResponse();
        log.error("### 예외 발생 ###");

        log.error("message: {}", response.getMessage());

        return ResponseEntity.status(response.getStatus()).body(response);
    }


}
