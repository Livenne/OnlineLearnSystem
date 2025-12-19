package com.livenne.common.exception;

import io.github.livenne.ResponseEntity;
import io.github.livenne.annotation.servlet.ControllerAdvice;
import io.github.livenne.annotation.servlet.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity exceptionHandler(Exception e) {
        log.error(e.getMessage(),e.getCause());
        return ResponseEntity.err(e.getMessage());
    }
}
