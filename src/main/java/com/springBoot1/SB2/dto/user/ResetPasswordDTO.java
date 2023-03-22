package com.springBoot1.SB2.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@ToString
public class ResetPasswordDTO {

    @NotBlank(message = "{resetPassword.email.NotBlank}")
    @Email(message = "{resetPassword.email.email}")
    private String email;
    @NotBlank(message = "{resetPassword.link.NotBlank}")
    private String link;
}
