package com.springBoot1.SB2.dto.customer;

import com.springBoot1.SB2.dto.base.IdAndCreatedAtAuditingDTO;
import lombok.*;

@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ShowAllCustomerDTO extends IdAndCreatedAtAuditingDTO {
    private String name;
    private String address;
}
