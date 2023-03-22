package com.springBoot1.SB2.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class ResetPasswordTokenDTO {
    @NotBlank(message = "{resetPassword.token.NotBlank}")
    private String token;
}
