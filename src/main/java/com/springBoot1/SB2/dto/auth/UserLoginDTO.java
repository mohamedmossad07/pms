package com.springBoot1.SB2.dto.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@ToString
public class UserLoginDTO {
    @NotBlank(message = "{users.username.NotBlank}")
    @Pattern(regexp = "\\w+", message = "{users.username.Pattern}")
    @Size(min = 3, max = 20, message = "{users.username.Size}")
    private String username;
    @NotBlank(message = "{users.password.NotBlank}")
    @Pattern(regexp = "\\w+", message = "{users.password.Pattern}")
    @Size(min = 4, message = "{users.password.Size}")
    private String password;
}
