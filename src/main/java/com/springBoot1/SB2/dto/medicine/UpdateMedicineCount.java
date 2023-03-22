package com.springBoot1.SB2.dto.medicine;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateMedicineCount {
    @NotNull(message = "{medicines.medicine.NotNull}")
    private Long medicine;
    @NotNull(message = "{medicines.pharmacy.NotNull}")
    private Long pharmacy;
    @NotNull(message = "{medicines.count.NotNull}")
    private int count;
}
