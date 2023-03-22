package com.springBoot1.SB2.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static com.springBoot1.SB2.util.ApiResponsePayloadFieldEnumUtil.*;

public class ApiResponseUtil {
    public static ResponseEntity<?> successPayload(Object payload, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put(SUCCESS.field(), Boolean.TRUE);
        map.put(STATUS.field(), status.value());
        map.put(PAYLOAD.field(), payload);
        return new ResponseEntity<>(map, status);
    }

    public static ResponseEntity<?> errorPayload(Object errors, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put(SUCCESS.field(), Boolean.FALSE);
        map.put(STATUS.field(), status.value());
        map.put(MESSAGE.field(), errors);
        return new ResponseEntity<>(map, status);
    }
}
