package com.springBoot1.SB2.dto.transaction;

import com.springBoot1.SB2.dto.base.BaseIdAndAuditingDTO;
import com.springBoot1.SB2.dto.base.BaseTrashableAuditingDTO;
import lombok.*;

@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ShowIdPriceTransactionDTO extends BaseIdAndAuditingDTO<Long> {
    private double price;
}
