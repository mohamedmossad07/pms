package com.springBoot1.SB2.mapper.role;

import com.springBoot1.SB2.dto.pharmacy.CreatePharmacyDTO;
import com.springBoot1.SB2.dto.pharmacy.ShowAllPharmacyDTO;
import com.springBoot1.SB2.dto.pharmacy.ShowPharmacyDTO;
import com.springBoot1.SB2.dto.pharmacy.UpdatePharmacyDTO;
import com.springBoot1.SB2.dto.role.AddRoleDTO;
import com.springBoot1.SB2.dto.role.ShowAllRoleDTO;
import com.springBoot1.SB2.dto.role.ShowRoleDTO;
import com.springBoot1.SB2.dto.role.UpdateRoleDTO;
import com.springBoot1.SB2.entity.Pharmacy;
import com.springBoot1.SB2.entity.Role;
import com.springBoot1.SB2.mapper.address.AddressMapper;
import com.springBoot1.SB2.mapper.base.BaseMapper;
import com.springBoot1.SB2.mapper.base.IdsMapper;
import com.springBoot1.SB2.mapper.pharmacy.PharmacyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(uses = {IdsMapper.class, PharmacyMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleMapper extends BaseMapper<Role, ShowRoleDTO, ShowAllRoleDTO, AddRoleDTO, UpdateRoleDTO> {
}
