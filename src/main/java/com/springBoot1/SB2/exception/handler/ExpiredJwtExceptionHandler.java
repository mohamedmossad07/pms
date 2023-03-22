package com.springBoot1.SB2.exception.handler;

import com.springBoot1.SB2.util.ApiResponseUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;

@RestControllerAdvice
public class ExpiredJwtExceptionHandler {
    @ExceptionHandler(value = {ExpiredJwtException.class})
    public ResponseEntity<?> handle(ExpiredJwtException exception) {
        Set<String> st = new HashSet<>();
        st.add(exception.getMessage());
        return ApiResponseUtil.errorPayload(st, HttpStatus.BAD_REQUEST);
    }
}
