package com.springBoot1.SB2.dto.transaction;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@ToString
public class CreateTransactionDTO {
    @NotNull(message = "{transactions.customer.NotNull}")
    private Long customer;
    @NotEmpty(message = "{transactions.medicines.NotNull}")
    @Valid
    private Set<CreateTransactionalMedicineDTO> medicines;
    @NotNull(message = "{medicines.pharmacy.NotNull}")
    private Long pharmacy;
}
