package com.springBoot1.SB2.exception.handler;

import com.springBoot1.SB2.util.ApiResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice

public class MethodArgumentTypeMismatchExceptionHandler {
    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ResponseEntity<?> handle(MethodArgumentTypeMismatchException exception) {
        Map<String, String> map = new HashMap<>();
        map.put("Method invalid", exception.getMessage());
        return ApiResponseUtil.errorPayload(map, HttpStatus.BAD_REQUEST);
    }
}
