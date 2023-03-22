package com.springBoot1.SB2.mapper.role;

import com.springBoot1.SB2.dto.role.AddRoleDTO;
import com.springBoot1.SB2.dto.role.ShowAllRoleDTO;
import com.springBoot1.SB2.dto.role.ShowRoleDTO;
import com.springBoot1.SB2.dto.role.UpdateRoleDTO;
import com.springBoot1.SB2.dto.user.ShowIdNameUserDTO;
import com.springBoot1.SB2.entity.Role;
import com.springBoot1.SB2.entity.User;
import com.springBoot1.SB2.mapper.pharmacy.PharmacyMapper;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-23T01:37:42+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Ubuntu)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Autowired
    private PharmacyMapper pharmacyMapper;

    @Override
    public ShowRoleDTO mapToShow(Role entity) {
        if ( entity == null ) {
            return null;
        }

        ShowRoleDTO showRoleDTO = new ShowRoleDTO();

        showRoleDTO.setCreatedBy( entity.getCreatedBy() );
        showRoleDTO.setUpdatedBy( entity.getUpdatedBy() );
        showRoleDTO.setCreatedAt( entity.getCreatedAt() );
        showRoleDTO.setUpdatedAt( entity.getUpdatedAt() );
        showRoleDTO.setId( entity.getId() );
        showRoleDTO.setTrashed( entity.isTrashed() );
        showRoleDTO.setName( entity.getName() );
        showRoleDTO.setUsers( userSetToShowIdNameUserDTOSet( entity.getUsers() ) );
        showRoleDTO.setPriority( entity.getPriority() );

        return showRoleDTO;
    }

    @Override
    public List<ShowAllRoleDTO> mapToShowAll(List<Role> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ShowAllRoleDTO> list = new ArrayList<ShowAllRoleDTO>( entities.size() );
        for ( Role role : entities ) {
            list.add( roleToShowAllRoleDTO( role ) );
        }

        return list;
    }

    @Override
    public Role unMapCreated(AddRoleDTO createDTO) {
        if ( createDTO == null ) {
            return null;
        }

        Role.RoleBuilder role = Role.builder();

        role.name( createDTO.getName() );
        role.priority( createDTO.getPriority() );

        return role.build();
    }

    @Override
    public Role unMapUpdated(Role entity, UpdateRoleDTO updateDTO) {
        if ( updateDTO == null ) {
            return entity;
        }

        if ( updateDTO.getName() != null ) {
            entity.setName( updateDTO.getName() );
        }
        if ( updateDTO.getPriority() != null ) {
            entity.setPriority( updateDTO.getPriority() );
        }

        return entity;
    }

    protected Set<ShowIdNameUserDTO> userSetToShowIdNameUserDTOSet(Set<User> set) {
        if ( set == null ) {
            return null;
        }

        Set<ShowIdNameUserDTO> set1 = new LinkedHashSet<ShowIdNameUserDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( User user : set ) {
            set1.add( pharmacyMapper.mapUserToShowIdNameUserDTO( user ) );
        }

        return set1;
    }

    protected ShowAllRoleDTO roleToShowAllRoleDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        ShowAllRoleDTO showAllRoleDTO = new ShowAllRoleDTO();

        showAllRoleDTO.setCreatedAt( role.getCreatedAt() );
        showAllRoleDTO.setId( role.getId() );
        showAllRoleDTO.setName( role.getName() );
        showAllRoleDTO.setPriority( role.getPriority() );

        return showAllRoleDTO;
    }
}
