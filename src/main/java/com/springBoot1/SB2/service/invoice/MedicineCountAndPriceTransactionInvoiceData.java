package com.springBoot1.SB2.service.invoice;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MedicineCountAndPriceTransactionInvoiceData {
    private String medicineMarketName;
    private Integer count;
    private LocalDate expiration;
    private Double unitPrice;
    private Double amountPrice;
}
