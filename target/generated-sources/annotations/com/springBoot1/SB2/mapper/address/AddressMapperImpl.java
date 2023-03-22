package com.springBoot1.SB2.mapper.address;

import com.springBoot1.SB2.dto.address.CreateAddressDTO;
import com.springBoot1.SB2.dto.address.ShowAddressDTO;
import com.springBoot1.SB2.dto.address.ShowAllAddressDTO;
import com.springBoot1.SB2.dto.address.UpdateAddressDTO;
import com.springBoot1.SB2.entity.Address;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-23T01:37:42+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Ubuntu)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public ShowAddressDTO mapToShow(Address entity) {
        if ( entity == null ) {
            return null;
        }

        ShowAddressDTO showAddressDTO = new ShowAddressDTO();

        showAddressDTO.setCreatedBy( entity.getCreatedBy() );
        showAddressDTO.setUpdatedBy( entity.getUpdatedBy() );
        showAddressDTO.setCreatedAt( entity.getCreatedAt() );
        showAddressDTO.setUpdatedAt( entity.getUpdatedAt() );
        showAddressDTO.setId( entity.getId() );
        showAddressDTO.setTrashed( entity.isTrashed() );
        showAddressDTO.setGovernorate( entity.getGovernorate() );
        showAddressDTO.setCity( entity.getCity() );
        showAddressDTO.setTown( entity.getTown() );
        showAddressDTO.setStreet( entity.getStreet() );

        return showAddressDTO;
    }

    @Override
    public List<ShowAllAddressDTO> mapToShowAll(List<Address> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ShowAllAddressDTO> list = new ArrayList<ShowAllAddressDTO>( entities.size() );
        for ( Address address : entities ) {
            list.add( addressToShowAllAddressDTO( address ) );
        }

        return list;
    }

    @Override
    public Address unMapCreated(CreateAddressDTO createDTO) {
        if ( createDTO == null ) {
            return null;
        }

        Address.AddressBuilder address = Address.builder();

        address.governorate( createDTO.getGovernorate() );
        address.city( createDTO.getCity() );
        address.town( createDTO.getTown() );
        address.street( createDTO.getStreet() );

        return address.build();
    }

    @Override
    public Address unMapUpdated(Address entity, UpdateAddressDTO updateDTO) {
        if ( updateDTO == null ) {
            return entity;
        }

        if ( updateDTO.getGovernorate() != null ) {
            entity.setGovernorate( updateDTO.getGovernorate() );
        }
        if ( updateDTO.getCity() != null ) {
            entity.setCity( updateDTO.getCity() );
        }
        if ( updateDTO.getTown() != null ) {
            entity.setTown( updateDTO.getTown() );
        }
        if ( updateDTO.getStreet() != null ) {
            entity.setStreet( updateDTO.getStreet() );
        }

        return entity;
    }

    protected ShowAllAddressDTO addressToShowAllAddressDTO(Address address) {
        if ( address == null ) {
            return null;
        }

        ShowAllAddressDTO showAllAddressDTO = new ShowAllAddressDTO();

        showAllAddressDTO.setCreatedAt( address.getCreatedAt() );
        showAllAddressDTO.setId( address.getId() );
        showAllAddressDTO.setGovernorate( address.getGovernorate() );
        showAllAddressDTO.setCity( address.getCity() );
        showAllAddressDTO.setTown( address.getTown() );
        showAllAddressDTO.setStreet( address.getStreet() );

        return showAllAddressDTO;
    }
}
