package com.springBoot1.SB2.dto.supplier;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@ToString
public class CreateSupplierDTO {
    @NotBlank(message = "{suppliers.name.NotBlank}")
    @Size(min = 3, message = "{suppliers.name.Size}")
    private String name;
    @Email(message = "{users.email.enterValidEmail}")
    @NotBlank(message = "{suppliers.email.NotBlank}")
    private String email;
    @NotNull(message = "{suppliers.address.NotNull}")
    private Long address;
    private Set<String> phones;
}
