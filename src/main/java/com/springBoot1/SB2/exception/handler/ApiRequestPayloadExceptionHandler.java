package com.springBoot1.SB2.exception.handler;

import com.springBoot1.SB2.exception.api.ApiRequestPayloadException;
import com.springBoot1.SB2.util.ApiResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiRequestPayloadExceptionHandler {

    @ExceptionHandler(value = {ApiRequestPayloadException.class})
    public ResponseEntity<?> handleApiRequestPayloadException(ApiRequestPayloadException exception) {
        Map<String, String> map = new HashMap<>();
        map.put(exception.getField(), exception.getMessage());
        return ApiResponseUtil.errorPayload(map, exception.getHttpStatus());
    }
}
