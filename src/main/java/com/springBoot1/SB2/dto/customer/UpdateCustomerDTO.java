package com.springBoot1.SB2.dto.customer;

import com.springBoot1.SB2.validation.customer.CustomerFieldNotTaken;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import static com.springBoot1.SB2.validation.FieldNamesValidationEnum.*;

@Setter
@Getter
@ToString
public class UpdateCustomerDTO {
    private String fname;//first name
    private String lname;//last name
    @Email(message = "{CreateUserDTO.email.email}")
    @Nullable
    @CustomerFieldNotTaken(field = VALIDATE_FIELD_EMAIL,message = "{email.taken}")
    private String email;
    @Pattern(regexp = "[+]?[0-9]{3,29}", message = "{CreateUserDTO.phone.phone}")
    @Nullable
    @CustomerFieldNotTaken(field = VALIDATE_FIELD_PHONE,message = "{phone.taken}")
    private String phone;
    private Long address;//need
}
