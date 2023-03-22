package com.springBoot1.SB2.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdatePasswordDTO {
    @NotBlank(message = "{users.password.NotBlank}")
    @Pattern(regexp = "\\w+", message = "{users.password.Pattern}")
    @Size(min = 4, message = "{users.password.Size}")
    private String password;
    @NotBlank(message = "{resetPassword.token.NotBlank}")
    private String token;
}
