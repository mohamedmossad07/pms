package com.springBoot1.SB2.exception.handler;

import com.springBoot1.SB2.exception.api.CouldntCreateUploadRootPathException;
import com.springBoot1.SB2.util.ApiResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;

@RestControllerAdvice

public class CouldntCreateUploadRootPathExceptionHandler {
    @ExceptionHandler(value = {CouldntCreateUploadRootPathException.class})
    public ResponseEntity<?> handle(CouldntCreateUploadRootPathException exception) {
        Set<String> set = new HashSet<>();
        set.add(exception.getMessage());
        return ApiResponseUtil.errorPayload(set, HttpStatus.BAD_REQUEST);
    }
}
