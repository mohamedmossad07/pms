package com.springBoot1.SB2.dto.user;

import com.springBoot1.SB2.validation.FieldNamesValidationEnum;
import com.springBoot1.SB2.validation.user.UserFieldNotTaken;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import static com.springBoot1.SB2.validation.FieldNamesValidationEnum.*;

@Setter
@Getter
@ToString
public class UpdateUserDTO {
    private String fname;//first name
    private String lname;//last name
    @UserFieldNotTaken(field = VALIDATE_FIELD_USERNAME,message = "{users.username.taken}")
    private String username;//username identifiers used in login
    @Nullable
    @Email(message = "{users.email.enterValidEmail}")
    @UserFieldNotTaken(field = VALIDATE_FIELD_EMAIL,message = "{users.email.taken}")
    private String email;
    private String password;
    @UserFieldNotTaken(field = VALIDATE_FIELD_PHONE,message = "{users.phone.taken}")
    private String phone;
    private Long role;
    private Long pharmacy;
    private MultipartFile img;
}
