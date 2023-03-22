package com.springBoot1.SB2.dto.address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAddressDTO {
    private String governorate;
    private String city;
    private String town;
    private String street;
}
