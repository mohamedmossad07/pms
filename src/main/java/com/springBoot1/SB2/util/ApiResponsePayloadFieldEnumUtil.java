package com.springBoot1.SB2.util;

public enum ApiResponsePayloadFieldEnumUtil {
    SUCCESS("success"),

    STATUS("status"),
    PAYLOAD("payload"),
    MESSAGE("message"),
    DATABASE_CONSTRAINTS_ERROR("database_constraints"),
    ERROR("error");
    private final String field;

    ApiResponsePayloadFieldEnumUtil(String field) {
        this.field = field;
    }

    public String field() {
        return field;
    }
}
