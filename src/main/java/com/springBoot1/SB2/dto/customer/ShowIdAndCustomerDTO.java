package com.springBoot1.SB2.dto.customer;

import lombok.*;

@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ShowIdAndCustomerDTO {
    private Long id;
    private String name;
}
