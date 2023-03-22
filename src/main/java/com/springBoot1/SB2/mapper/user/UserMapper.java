package com.springBoot1.SB2.mapper.user;

import com.springBoot1.SB2.dto.transaction.CreateTransactionDTO;
import com.springBoot1.SB2.dto.transaction.ShowAllTransactionDTO;
import com.springBoot1.SB2.dto.transaction.ShowTransactionDTO;
import com.springBoot1.SB2.dto.transaction.UpdateTransactionDTO;
import com.springBoot1.SB2.dto.user.CreateUserDTO;
import com.springBoot1.SB2.dto.user.ShowAllUserDTO;
import com.springBoot1.SB2.dto.user.ShowUserDTO;
import com.springBoot1.SB2.dto.user.UpdateUserDTO;
import com.springBoot1.SB2.entity.Transaction;
import com.springBoot1.SB2.entity.User;
import com.springBoot1.SB2.mapper.base.BaseMapper;
import com.springBoot1.SB2.mapper.base.IdsMapper;
import com.springBoot1.SB2.mapper.customer.CustomerMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(uses = {IdsMapper.class},
        imports = {IdsMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper extends BaseMapper<User, ShowUserDTO, ShowAllUserDTO, CreateUserDTO, UpdateUserDTO> {
    @Mapping(target = "pharmacy",expression = "java(IdsMapper.mapPharmacyIdToPharmacyUser(createUserDTO.getPharmacy(),user))")
    User unMapCreated(CreateUserDTO createUserDTO,@Context String path);
    @Mapping(target = "pharmacy",expression = "java(IdsMapper.mapPharmacyIdToPharmacyUser(updateUserDTO.getPharmacy(),user))")
    User unMapUpdated(@MappingTarget User user, UpdateUserDTO updateUserDTO, @Context String path);
    @Override
    @Mapping(target = "img",ignore = true)
    @Mapping(target = "pharmacy",ignore = true)
    User unMapCreated(CreateUserDTO createUserDTO);
    @Override
    @Mapping(target = "img",ignore = true)
    @Mapping(target = "pharmacy",ignore = true)
    User unMapUpdated(@MappingTarget User user, UpdateUserDTO updateUserDTO);
    @Mapping(target = "role",source = "role.name")
    @Mapping(target = "name",expression = "java(user.getFname()+\" \"+user.getLname())")
    ShowAllUserDTO map2SingleShowAll(User user);
    @Override
    @Mapping(target = "pharmacy",source = "pharmacy.pharmacy")
    ShowUserDTO mapToShow(User user);
}
