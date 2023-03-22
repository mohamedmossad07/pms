package com.springBoot1.SB2.dto.address;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShowIdAndAddressDTO {
    private Long id;
    private String governorate;
    private String city;
    private String town;
    private String street;
}
