package com.springBoot1.SB2.mapper.supplier;

import com.springBoot1.SB2.dto.pharmacy.CreatePharmacyDTO;
import com.springBoot1.SB2.dto.pharmacy.ShowAllPharmacyDTO;
import com.springBoot1.SB2.dto.pharmacy.ShowPharmacyDTO;
import com.springBoot1.SB2.dto.pharmacy.UpdatePharmacyDTO;
import com.springBoot1.SB2.dto.supplier.CreateSupplierDTO;
import com.springBoot1.SB2.dto.supplier.ShowAllSupplierDTO;
import com.springBoot1.SB2.dto.supplier.ShowSupplierDTO;
import com.springBoot1.SB2.dto.supplier.UpdateSupplierDTO;
import com.springBoot1.SB2.entity.Pharmacy;
import com.springBoot1.SB2.entity.Supplier;
import com.springBoot1.SB2.entity.SupplierPhone;
import com.springBoot1.SB2.mapper.address.AddressMapper;
import com.springBoot1.SB2.mapper.base.BaseMapper;
import com.springBoot1.SB2.mapper.base.IdsMapper;
import com.springBoot1.SB2.mapper.pharmacy.PharmacyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(uses = {IdsMapper.class, AddressMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SupplierMapper extends BaseMapper<Supplier, ShowSupplierDTO, ShowAllSupplierDTO, CreateSupplierDTO, UpdateSupplierDTO> {
    @Override
    @Mapping(target = "phones",expression = "java(mapStringToSupplierPhone(createSupplierDTO.getPhones(),supplier))")
    Supplier unMapCreated(CreateSupplierDTO createSupplierDTO);
    default Set<SupplierPhone> mapStringToSupplierPhone(Set<String> phones, Supplier supplier){
       if(phones!=null&& !phones.isEmpty()){
            return  phones.stream().map(s -> new SupplierPhone(s,supplier)).collect(Collectors.toSet());
       }
       return null;
    }
    @Override
    @Mapping(target = "phones",expression = "java(mapStringToSupplierPhone(updateSupplierDTO.getPhones(),supplier))")
    Supplier unMapUpdated(@MappingTarget Supplier supplier, UpdateSupplierDTO updateSupplierDTO);
    default String mapSupplierPhoneToString(SupplierPhone phone){
        if (phone==null)return null;
        return phone.getPhone();
    }
}
