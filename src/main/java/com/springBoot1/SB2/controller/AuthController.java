package com.springBoot1.SB2.controller;

import com.springBoot1.SB2.config.JwtTokenProvider;
import com.springBoot1.SB2.config.UserDetailsImpl;
import com.springBoot1.SB2.dto.auth.LoginResponseDTO;
import com.springBoot1.SB2.dto.auth.UserLoginDTO;
import com.springBoot1.SB2.service.AuthService;
import com.springBoot1.SB2.util.ApiResponseUtil;
import com.springBoot1.SB2.util.JwtApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${API.URL_PREFIX}auth/")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtTokenProvider provider;
    @Autowired
    private MessageSource messageSource;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginDTO loginDTO) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authService.login(loginDTO);
        Map<String, Object> map = new HashMap<>();
        map.put("success", Boolean.TRUE);
        map.put("status", HttpStatus.OK);
        String token = provider.generateToken(userDetails);
        Set<String> authorities;
        authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(userDetails.getUsername(), userDetails.getUser().getEmail(), authorities, new JwtApiResponse(token));
        map.put("payload", loginResponseDTO);
        System.out.println(token);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + token).body(map);
    }

    @PostMapping("logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String token = provider.getJwtFromRequest(request);
        authService.inValidateToken(token);
        String[] m = {};
        return ApiResponseUtil.successPayload(messageSource.getMessage("auth.logout", m, LocaleContextHolder.getLocale()), HttpStatus.OK);
    }
}
