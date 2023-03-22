package com.springBoot1.SB2.exception.handler;

import com.springBoot1.SB2.util.ApiResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class BindExceptionHandler {
    @ExceptionHandler(value = {BindException.class})
    public ResponseEntity<?> handleInvalidArgument(BindException exception) {
        Map<String, String> map = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach((err) -> {
            map.put(err.getField(), err.getDefaultMessage());
        });
        return ApiResponseUtil.errorPayload(map, HttpStatus.BAD_REQUEST);
    }
}
