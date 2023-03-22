package com.springBoot1.SB2.dto.auth;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private String username;
    private String email;
    private Set<String> authorities;
    private Object jwt;
}
