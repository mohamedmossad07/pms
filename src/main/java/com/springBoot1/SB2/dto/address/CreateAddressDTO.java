package com.springBoot1.SB2.dto.address;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressDTO {
    @NotBlank(message = "{addresses.governorate.NotBlank}")
    private String governorate;
    @NotBlank(message = "{addresses.city.NotBlank}")
    private String city;
    @NotBlank(message = "{addresses.town.NotBlank}")
    private String town;
    private String street;

}
