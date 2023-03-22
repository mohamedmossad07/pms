package com.springBoot1.SB2.dto.base;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseTrashableAuditingDTO extends BaseIdAndAuditingDTO<Long> {
    private boolean trashed;
}
