package com.springBoot1.SB2.dto.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BaseIdAndAuditingDTO<ID> extends BaseAuditingDTO {
    private ID id;
}
