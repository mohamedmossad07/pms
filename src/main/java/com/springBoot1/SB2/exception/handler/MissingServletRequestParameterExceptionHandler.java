package com.springBoot1.SB2.exception.handler;

import com.springBoot1.SB2.util.ApiResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MissingServletRequestParameterExceptionHandler {
    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public ResponseEntity<?> handle(MissingServletRequestParameterException exception) {
        Map<String, String> map = new HashMap<>();
        map.put("InvalidParams", exception.getMessage());
        return ApiResponseUtil.errorPayload(map, HttpStatus.BAD_REQUEST);
    }
}
