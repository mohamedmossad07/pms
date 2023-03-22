package com.springBoot1.SB2.dto.user;

import com.springBoot1.SB2.validation.FieldNamesValidationEnum;
import com.springBoot1.SB2.validation.user.UserFieldNotTaken;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

import static com.springBoot1.SB2.validation.FieldNamesValidationEnum.*;

@Getter
@Setter
@ToString
public class CreateUserDTO {
    @NotBlank(message = "{users.fname.NotBlank}")
    @Size(min = 3, max = 20, message = "{users.fname.Size}")
    private String fname;//first name
    @NotBlank(message = "{users.lname.NotBlank}")
    @Size(min = 3, max = 20, message = "{users.lname.Size}")
    private String lname;//last name
    @NotBlank(message = "{users.username.NotBlank}")
    @Pattern(regexp = "\\w+", message = "{users.username.Pattern}")
    @Size(min = 3, max = 20, message = "{users.username.Size}")
    @UserFieldNotTaken(field = VALIDATE_FIELD_USERNAME,message = "{username.taken}")
    private String username;
    @NotBlank(message = "{users.password.NotBlank}")
    @Pattern(regexp = "\\w+", message = "{users.password.Pattern}")
    @Size(min = 4, message = "{users.password.Size}")
    private String password;
    @Nullable
    @Email(message = "{users.email.enterValidEmail}")
    @UserFieldNotTaken(field = VALIDATE_FIELD_EMAIL,message = "{email.taken}")
    private String email;
    @Pattern(regexp = "[+]?[0-9]{3,29}", message = "{users.phone.enterValidPhone}")
    @Nullable
    @UserFieldNotTaken(field = VALIDATE_FIELD_PHONE,message = "{phone.taken}")
    private String phone;
    @NotNull(message = "{users.role.role}")
    private Long role;
    private Long pharmacy;
    private MultipartFile img;
}
