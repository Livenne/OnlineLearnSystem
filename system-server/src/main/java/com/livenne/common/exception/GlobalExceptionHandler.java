package com.livenne.common.exception;

import com.livenne.ResponseEntity;
import com.livenne.annotation.ControllerAdvice;
import com.livenne.annotation.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception e) {
        log.error(e.getMessage(),e.getCause());
        return ResponseEntity.failure(e.getMessage());
    }
}
