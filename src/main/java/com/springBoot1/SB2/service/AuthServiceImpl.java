package com.springBoot1.SB2.service;

import com.springBoot1.SB2.config.JwtTokenProvider;
import com.springBoot1.SB2.config.UserDetailsImpl;
import com.springBoot1.SB2.dto.auth.UserLoginDTO;
import com.springBoot1.SB2.entity.JwtToken;
import com.springBoot1.SB2.repository.JwtTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider provider;
    @Autowired
    private JwtTokenRepository tokenRepository;

    public UserDetails login(UserLoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        Authentication authentication =
                authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return (UserDetailsImpl) authentication.getPrincipal();
    }

    @Override
    public void inValidateToken(String token) {
        JwtToken jwtToken = JwtToken.builder().token(token).exp(provider.getExpTime(token)).build();
        tokenRepository.save(jwtToken);
    }
}
