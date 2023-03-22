package com.springBoot1.SB2.dto.transaction;

import com.springBoot1.SB2.dto.medicine.ShowIdMedicineDTO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShowTransactionalMedicineDTO {
    private ShowIdMedicineDTO medicine;
    private Integer count;
}
