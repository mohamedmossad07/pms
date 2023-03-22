package com.springBoot1.SB2.dto.notification;

import com.springBoot1.SB2.dto.base.IdAndCreatedAtAuditingDTO;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShowAllNotificationsDTO extends IdAndCreatedAtAuditingDTO {
    private String message;
    private boolean read;
}
