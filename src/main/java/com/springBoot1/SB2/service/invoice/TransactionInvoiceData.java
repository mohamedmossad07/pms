package com.springBoot1.SB2.service.invoice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString
public class TransactionInvoiceData {
    private Double totPrice;
    private String customerName;
    private String pharmacyName;
    private String pharmacyAddress;
    private String pharmacistName;
    private Long transactionId;
    private Set<MedicineCountAndPriceTransactionInvoiceData> medicines = new HashSet<>();
}
