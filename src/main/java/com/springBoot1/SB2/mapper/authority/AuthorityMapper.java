package com.springBoot1.SB2.mapper.authority;

import com.springBoot1.SB2.dto.address.CreateAddressDTO;
import com.springBoot1.SB2.dto.address.ShowAddressDTO;
import com.springBoot1.SB2.dto.address.ShowAllAddressDTO;
import com.springBoot1.SB2.dto.address.UpdateAddressDTO;
import com.springBoot1.SB2.dto.authority.AddOrUpdateAuthorityDTO;
import com.springBoot1.SB2.dto.authority.ShowAllAuthorityDTO;
import com.springBoot1.SB2.dto.authority.ShowAuthorityDTO;
import com.springBoot1.SB2.entity.Address;
import com.springBoot1.SB2.entity.Authority;
import com.springBoot1.SB2.mapper.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthorityMapper extends BaseMapper<Authority, ShowAuthorityDTO, ShowAllAuthorityDTO, AddOrUpdateAuthorityDTO, AddOrUpdateAuthorityDTO> {}
