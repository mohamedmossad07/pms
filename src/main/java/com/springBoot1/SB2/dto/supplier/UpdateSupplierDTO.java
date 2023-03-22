package com.springBoot1.SB2.dto.supplier;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import java.util.Set;

@Getter
@Setter
@ToString
public class UpdateSupplierDTO {
    private String name;
    @Email(message = "{CreateUserDTO.email.email }")
    @Nullable
    private String email;
    private Long address;
    private Set<String> phones;
}
