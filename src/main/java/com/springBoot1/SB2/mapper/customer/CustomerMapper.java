package com.springBoot1.SB2.mapper.customer;

import com.springBoot1.SB2.dto.customer.*;
import com.springBoot1.SB2.entity.Customer;
import com.springBoot1.SB2.mapper.address.AddressMapper;
import com.springBoot1.SB2.mapper.base.BaseMapper;
import com.springBoot1.SB2.mapper.base.IdsMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(uses = {AddressMapper.class, IdsMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface CustomerMapper extends BaseMapper<Customer, ShowCustomerDTO, ShowAllCustomerDTO, CreateCustomerDTO, UpdateCustomerDTO> {
    @Mapping(target = "name",expression = "java(customer.getFname()+\" \"+customer.getLname())")
    ShowAllCustomerDTO map2SingleShowAll(Customer customer);
    default String mapCustomerToString(Customer customer){
        if (customer==null)return null;
        return customer.getFname()+" "+customer.getLname();
    }
    default ShowIdAndCustomerDTO mapCustomerToShowIdAndCustomerDTO(Customer customer){
        if (customer==null)return null;
        return new ShowIdAndCustomerDTO(customer.getId(),mapCustomerToString(customer));
    }
}
