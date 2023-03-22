package com.springBoot1.SB2.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MedicineExpirationEvent {
    Long medicineId;
    Long pharmacyId;
}
