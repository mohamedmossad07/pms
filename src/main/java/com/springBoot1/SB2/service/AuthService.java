package com.springBoot1.SB2.service;

import com.springBoot1.SB2.dto.auth.UserLoginDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    UserDetails login(UserLoginDTO loginDTO);

    void inValidateToken(String token);
}
