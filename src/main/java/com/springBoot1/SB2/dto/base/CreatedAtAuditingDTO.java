package com.springBoot1.SB2.dto.base;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreatedAtAuditingDTO {
    protected LocalDateTime createdAt;
}
