package com.springBoot1.SB2.exception.handler;

import com.springBoot1.SB2.util.ApiResponsePayloadFieldEnumUtil;
import com.springBoot1.SB2.util.ApiResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class SQLIntegrityConstraintViolationExceptionHandler {
    @ExceptionHandler(value = {SQLIntegrityConstraintViolationException.class})
    public ResponseEntity<?> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception) {
        Map<String, String> map = new HashMap<>();
        map.put(ApiResponsePayloadFieldEnumUtil.DATABASE_CONSTRAINTS_ERROR.field(), exception.getMessage());
        return ApiResponseUtil.errorPayload(map, HttpStatus.BAD_REQUEST);
    }
}
