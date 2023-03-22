package com.springBoot1.SB2.dto.user;

import com.springBoot1.SB2.dto.base.IdAndCreatedAtAuditingDTO;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShowAllUserDTO extends IdAndCreatedAtAuditingDTO {
    private String username;
    private String name;
    private String email;
    private String phone;
    private String role;
}
