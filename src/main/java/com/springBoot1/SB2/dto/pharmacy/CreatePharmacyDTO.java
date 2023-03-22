package com.springBoot1.SB2.dto.pharmacy;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class CreatePharmacyDTO {
    @NotBlank(message = "{pharmacies.name.NotBlank}")
    private String name;
    @NotNull(message = "{pharmacies.address.NotNull}")
    private Long address;
    private Long manager;
    private Set<Long> users;
}
