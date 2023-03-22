package com.springBoot1.SB2.dto.transaction;

import com.springBoot1.SB2.dto.base.IdAndCreatedAtAuditingDTO;
import com.springBoot1.SB2.dto.customer.ShowIdAndCustomerDTO;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShowAllTransactionDTO extends IdAndCreatedAtAuditingDTO {
    private ShowIdAndCustomerDTO customer;
    private Double price;
}
