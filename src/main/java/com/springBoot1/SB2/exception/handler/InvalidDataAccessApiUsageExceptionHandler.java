package com.springBoot1.SB2.exception.handler;

import com.springBoot1.SB2.util.ApiResponseUtil;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice

public class InvalidDataAccessApiUsageExceptionHandler {

    @ExceptionHandler(value = {InvalidDataAccessApiUsageException.class})
    public ResponseEntity<?> handle(InvalidDataAccessApiUsageException exception) {
        Map<String, String> map = new HashMap<>();
        map.put("Data invalid access", exception.getMessage());
        return ApiResponseUtil.errorPayload(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
