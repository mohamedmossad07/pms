package com.springBoot1.SB2.mapper.authority;

import com.springBoot1.SB2.dto.authority.AddOrUpdateAuthorityDTO;
import com.springBoot1.SB2.dto.authority.ShowAllAuthorityDTO;
import com.springBoot1.SB2.dto.authority.ShowAuthorityDTO;
import com.springBoot1.SB2.dto.role.ShowRoleIdAndNameDTO;
import com.springBoot1.SB2.entity.Authority;
import com.springBoot1.SB2.entity.Role;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-23T01:37:42+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Ubuntu)"
)
@Component
public class AuthorityMapperImpl implements AuthorityMapper {

    @Override
    public ShowAuthorityDTO mapToShow(Authority entity) {
        if ( entity == null ) {
            return null;
        }

        ShowAuthorityDTO showAuthorityDTO = new ShowAuthorityDTO();

        showAuthorityDTO.setCreatedBy( entity.getCreatedBy() );
        showAuthorityDTO.setUpdatedBy( entity.getUpdatedBy() );
        showAuthorityDTO.setCreatedAt( entity.getCreatedAt() );
        showAuthorityDTO.setUpdatedAt( entity.getUpdatedAt() );
        showAuthorityDTO.setId( entity.getId() );
        showAuthorityDTO.setTrashed( entity.isTrashed() );
        showAuthorityDTO.setName( entity.getName() );
        showAuthorityDTO.setRoles( roleSetToShowRoleIdAndNameDTOSet( entity.getRoles() ) );

        return showAuthorityDTO;
    }

    @Override
    public List<ShowAllAuthorityDTO> mapToShowAll(List<Authority> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ShowAllAuthorityDTO> list = new ArrayList<ShowAllAuthorityDTO>( entities.size() );
        for ( Authority authority : entities ) {
            list.add( authorityToShowAllAuthorityDTO( authority ) );
        }

        return list;
    }

    @Override
    public Authority unMapCreated(AddOrUpdateAuthorityDTO createDTO) {
        if ( createDTO == null ) {
            return null;
        }

        Authority.AuthorityBuilder authority = Authority.builder();

        authority.name( createDTO.getName() );

        return authority.build();
    }

    @Override
    public Authority unMapUpdated(Authority entity, AddOrUpdateAuthorityDTO updateDTO) {
        if ( updateDTO == null ) {
            return entity;
        }

        if ( updateDTO.getName() != null ) {
            entity.setName( updateDTO.getName() );
        }

        return entity;
    }

    protected ShowRoleIdAndNameDTO roleToShowRoleIdAndNameDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        ShowRoleIdAndNameDTO showRoleIdAndNameDTO = new ShowRoleIdAndNameDTO();

        showRoleIdAndNameDTO.setId( role.getId() );
        showRoleIdAndNameDTO.setName( role.getName() );

        return showRoleIdAndNameDTO;
    }

    protected Set<ShowRoleIdAndNameDTO> roleSetToShowRoleIdAndNameDTOSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<ShowRoleIdAndNameDTO> set1 = new LinkedHashSet<ShowRoleIdAndNameDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleToShowRoleIdAndNameDTO( role ) );
        }

        return set1;
    }

    protected ShowAllAuthorityDTO authorityToShowAllAuthorityDTO(Authority authority) {
        if ( authority == null ) {
            return null;
        }

        ShowAllAuthorityDTO showAllAuthorityDTO = new ShowAllAuthorityDTO();

        showAllAuthorityDTO.setCreatedAt( authority.getCreatedAt() );
        showAllAuthorityDTO.setId( authority.getId() );
        showAllAuthorityDTO.setName( authority.getName() );

        return showAllAuthorityDTO;
    }
}
