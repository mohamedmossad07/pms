package com.springBoot1.SB2.dto.user;

import com.springBoot1.SB2.dto.base.BaseTrashableAuditingDTO;
import com.springBoot1.SB2.dto.pharmacy.ShowIdPharmacyDTO;
import com.springBoot1.SB2.dto.role.ShowRoleIdAndNameDTO;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShowUserDTO extends BaseTrashableAuditingDTO {
    private String fname;//first name
    private String lname;//last name
    private String username;//username identifiers used in login
    private String email;
    private String phone;
    private String img;
    private ShowRoleIdAndNameDTO role ;
    private ShowIdPharmacyDTO pharmacy;
    private Set<ShowIdPharmacyDTO> managing;
}
