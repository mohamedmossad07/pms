package com.springBoot1.SB2.mapper.pharmacy;

import com.springBoot1.SB2.dto.medicine.CreateMedicineDTO;
import com.springBoot1.SB2.dto.medicine.ShowAllMedicineDTO;
import com.springBoot1.SB2.dto.medicine.ShowMedicineDTO;
import com.springBoot1.SB2.dto.medicine.UpdateMedicineDTO;
import com.springBoot1.SB2.dto.pharmacy.CreatePharmacyDTO;
import com.springBoot1.SB2.dto.pharmacy.ShowAllPharmacyDTO;
import com.springBoot1.SB2.dto.pharmacy.ShowPharmacyDTO;
import com.springBoot1.SB2.dto.pharmacy.UpdatePharmacyDTO;
import com.springBoot1.SB2.dto.user.ShowIdNameUserDTO;
import com.springBoot1.SB2.entity.Medicine;
import com.springBoot1.SB2.entity.Pharmacy;
import com.springBoot1.SB2.entity.PharmacyUser;
import com.springBoot1.SB2.entity.User;
import com.springBoot1.SB2.mapper.address.AddressMapper;
import com.springBoot1.SB2.mapper.base.BaseMapper;
import com.springBoot1.SB2.mapper.base.IdsMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(uses = {IdsMapper.class, AddressMapper.class},
        imports = {IdsMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PharmacyMapper  extends BaseMapper<Pharmacy, ShowPharmacyDTO, ShowAllPharmacyDTO, CreatePharmacyDTO, UpdatePharmacyDTO> {
    @Override
    @Mapping(target = "users",expression = "java(IdsMapper.mapIdsToPharmacyUsers(createPharmacyDTO.getUsers(),pharmacy))")
    Pharmacy unMapCreated(CreatePharmacyDTO createPharmacyDTO);
    @Override
    @Mapping(target = "users",expression = "java(IdsMapper.mapIdsToPharmacyUsers(updatePharmacyDTO.getUsers(),pharmacy))")
    Pharmacy unMapUpdated(@MappingTarget Pharmacy pharmacy, UpdatePharmacyDTO updatePharmacyDTO);
    default ShowIdNameUserDTO mapUserToShowIdNameUserDTO(User user){
        return new ShowIdNameUserDTO(user.getId(),user.getFname()+" "+user.getLname());
    }
    default ShowIdNameUserDTO mapPharmacyUserToShowIdNameUserDTO(PharmacyUser pharmacyUser){
        if (pharmacyUser.getUser()==null)return  null;
        return new ShowIdNameUserDTO(pharmacyUser.getUser().getId(),pharmacyUser.getUser().getFname()+" "+pharmacyUser.getUser().getLname());
    }
}
