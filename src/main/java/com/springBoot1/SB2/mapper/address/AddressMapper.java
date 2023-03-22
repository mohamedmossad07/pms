package com.springBoot1.SB2.mapper.address;

import com.springBoot1.SB2.dto.address.CreateAddressDTO;
import com.springBoot1.SB2.dto.address.ShowAddressDTO;
import com.springBoot1.SB2.dto.address.ShowAllAddressDTO;
import com.springBoot1.SB2.dto.address.UpdateAddressDTO;
import com.springBoot1.SB2.entity.Address;
import com.springBoot1.SB2.mapper.base.BaseMapper;
import com.springBoot1.SB2.repository.CustomerRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressMapper extends BaseMapper<Address,ShowAddressDTO,ShowAllAddressDTO,CreateAddressDTO,UpdateAddressDTO> {
    default String mapAddressToString(Address address) {
        return address.getGovernorate() + "-" + address.getCity() + "-" + address.getTown();
    }
}