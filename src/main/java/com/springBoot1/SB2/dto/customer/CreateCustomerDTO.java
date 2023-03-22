package com.springBoot1.SB2.dto.customer;

import com.springBoot1.SB2.validation.customer.CustomerFieldNotTaken;
import com.springBoot1.SB2.validation.user.UserFieldNotTaken;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;
import static com.springBoot1.SB2.validation.FieldNamesValidationEnum.*;

@Setter
@Getter
public class CreateCustomerDTO {
    @NotBlank(message = "{users.fname.NotBlank}")
    @Size(min = 3, max = 30, message = "{users.fname.Size}")
    private String fname;//first name
    @NotBlank(message = "{users.lname.NotBlank}")
    @Size(min = 3, max = 30, message = "{users.lname.Size}")
    private String lname;//last name
    @Nullable
    @Email(message = "{users.email.enterValidEmail}")
    @CustomerFieldNotTaken(field = VALIDATE_FIELD_EMAIL,message = "{email.taken}")
    private String email;
    @Pattern(regexp = "[+]?[0-9]{3,29}", message = "{users.phone.enterValidPhone}")
    @Nullable
    @CustomerFieldNotTaken(field = VALIDATE_FIELD_PHONE,message = "{phone.taken}")
    private String phone;
    @NotNull(message = "{customers.address.NotNull}")
    private Long address;
}
