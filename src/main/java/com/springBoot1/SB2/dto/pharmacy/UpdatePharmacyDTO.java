package com.springBoot1.SB2.dto.pharmacy;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UpdatePharmacyDTO {
    private String name;
    private Long address;
    private Long manager;
    private Set<Long> users;

}
