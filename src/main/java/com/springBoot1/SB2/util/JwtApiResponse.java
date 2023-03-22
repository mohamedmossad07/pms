package com.springBoot1.SB2.util;

import lombok.Getter;

@Getter
public class JwtApiResponse {
    private final String tokenType = "Bearer ";
    private final String token;

    public JwtApiResponse(String token) {
        this.token = token;
    }

}
