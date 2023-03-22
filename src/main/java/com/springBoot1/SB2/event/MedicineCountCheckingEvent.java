package com.springBoot1.SB2.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MedicineCountCheckingEvent {
    Long medicineId;
    Long pharmacyId;
}