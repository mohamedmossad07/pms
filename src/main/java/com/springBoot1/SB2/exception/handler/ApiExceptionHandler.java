package com.springBoot1.SB2.exception.handler;

import com.springBoot1.SB2.exception.api.ApiException;
import com.springBoot1.SB2.util.ApiResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiException.class})
    public ResponseEntity<?> handle(ApiException exception) {
        return ApiResponseUtil.errorPayload(exception.getMessage(), exception.getHttpStatus());
    }
}
