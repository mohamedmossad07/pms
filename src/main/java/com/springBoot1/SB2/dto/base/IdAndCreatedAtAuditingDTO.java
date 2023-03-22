package com.springBoot1.SB2.dto.base;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class IdAndCreatedAtAuditingDTO extends CreatedAtAuditingDTO {
    protected Long id;
}
