package com.springBoot1.SB2.mapper.pharmacy;

import com.springBoot1.SB2.dto.address.ShowIdAndAddressDTO;
import com.springBoot1.SB2.dto.pharmacy.CreatePharmacyDTO;
import com.springBoot1.SB2.dto.pharmacy.ShowAllPharmacyDTO;
import com.springBoot1.SB2.dto.pharmacy.ShowPharmacyDTO;
import com.springBoot1.SB2.dto.pharmacy.UpdatePharmacyDTO;
import com.springBoot1.SB2.dto.user.ShowIdNameUserDTO;
import com.springBoot1.SB2.entity.Address;
import com.springBoot1.SB2.entity.Pharmacy;
import com.springBoot1.SB2.entity.PharmacyUser;
import com.springBoot1.SB2.exception.api.ApiException;
import com.springBoot1.SB2.mapper.address.AddressMapper;
import com.springBoot1.SB2.mapper.base.IdsMapper;
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
public class PharmacyMapperImpl implements PharmacyMapper {

    @Autowired
    private IdsMapper idsMapper;
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public ShowPharmacyDTO mapToShow(Pharmacy entity) {
        if ( entity == null ) {
            return null;
        }

        ShowPharmacyDTO showPharmacyDTO = new ShowPharmacyDTO();

        showPharmacyDTO.setCreatedBy( entity.getCreatedBy() );
        showPharmacyDTO.setUpdatedBy( entity.getUpdatedBy() );
        showPharmacyDTO.setCreatedAt( entity.getCreatedAt() );
        showPharmacyDTO.setUpdatedAt( entity.getUpdatedAt() );
        showPharmacyDTO.setId( entity.getId() );
        showPharmacyDTO.setTrashed( entity.isTrashed() );
        showPharmacyDTO.setName( entity.getName() );
        showPharmacyDTO.setAddress( addressToShowIdAndAddressDTO( entity.getAddress() ) );
        showPharmacyDTO.setManager( mapUserToShowIdNameUserDTO( entity.getManager() ) );
        showPharmacyDTO.setUsers( pharmacyUserSetToShowIdNameUserDTOSet( entity.getUsers() ) );

        return showPharmacyDTO;
    }

    @Override
    public List<ShowAllPharmacyDTO> mapToShowAll(List<Pharmacy> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ShowAllPharmacyDTO> list = new ArrayList<ShowAllPharmacyDTO>( entities.size() );
        for ( Pharmacy pharmacy : entities ) {
            list.add( pharmacyToShowAllPharmacyDTO( pharmacy ) );
        }

        return list;
    }

    @Override
    public Pharmacy unMapCreated(CreatePharmacyDTO createPharmacyDTO) {
        if ( createPharmacyDTO == null ) {
            return null;
        }

        Pharmacy pharmacy = new Pharmacy();

        pharmacy.setName( createPharmacyDTO.getName() );
        try {
            pharmacy.setAddress( idsMapper.mapLongIdToAddress( createPharmacyDTO.getAddress() ) );
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }
        try {
            pharmacy.setManager( idsMapper.mapLongIdToUser( createPharmacyDTO.getManager() ) );
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }

        pharmacy.setUsers( IdsMapper.mapIdsToPharmacyUsers(createPharmacyDTO.getUsers(),pharmacy) );

        return pharmacy;
    }

    @Override
    public Pharmacy unMapUpdated(Pharmacy pharmacy, UpdatePharmacyDTO updatePharmacyDTO) {
        if ( updatePharmacyDTO == null ) {
            return pharmacy;
        }

        if ( updatePharmacyDTO.getName() != null ) {
            pharmacy.setName( updatePharmacyDTO.getName() );
        }
        try {
            if ( updatePharmacyDTO.getAddress() != null ) {
                pharmacy.setAddress( idsMapper.mapLongIdToAddress( updatePharmacyDTO.getAddress() ) );
            }
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }
        try {
            if ( updatePharmacyDTO.getManager() != null ) {
                pharmacy.setManager( idsMapper.mapLongIdToUser( updatePharmacyDTO.getManager() ) );
            }
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }

        pharmacy.setUsers( IdsMapper.mapIdsToPharmacyUsers(updatePharmacyDTO.getUsers(),pharmacy) );

        return pharmacy;
    }

    protected ShowIdAndAddressDTO addressToShowIdAndAddressDTO(Address address) {
        if ( address == null ) {
            return null;
        }

        ShowIdAndAddressDTO showIdAndAddressDTO = new ShowIdAndAddressDTO();

        showIdAndAddressDTO.setId( address.getId() );
        showIdAndAddressDTO.setGovernorate( address.getGovernorate() );
        showIdAndAddressDTO.setCity( address.getCity() );
        showIdAndAddressDTO.setTown( address.getTown() );
        showIdAndAddressDTO.setStreet( address.getStreet() );

        return showIdAndAddressDTO;
    }

    protected Set<ShowIdNameUserDTO> pharmacyUserSetToShowIdNameUserDTOSet(Set<PharmacyUser> set) {
        if ( set == null ) {
            return null;
        }

        Set<ShowIdNameUserDTO> set1 = new LinkedHashSet<ShowIdNameUserDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PharmacyUser pharmacyUser : set ) {
            set1.add( mapPharmacyUserToShowIdNameUserDTO( pharmacyUser ) );
        }

        return set1;
    }

    protected ShowAllPharmacyDTO pharmacyToShowAllPharmacyDTO(Pharmacy pharmacy) {
        if ( pharmacy == null ) {
            return null;
        }

        ShowAllPharmacyDTO showAllPharmacyDTO = new ShowAllPharmacyDTO();

        showAllPharmacyDTO.setCreatedAt( pharmacy.getCreatedAt() );
        showAllPharmacyDTO.setId( pharmacy.getId() );
        showAllPharmacyDTO.setName( pharmacy.getName() );
        showAllPharmacyDTO.setAddress( addressMapper.mapAddressToString( pharmacy.getAddress() ) );
        showAllPharmacyDTO.setManager( mapUserToShowIdNameUserDTO( pharmacy.getManager() ) );

        return showAllPharmacyDTO;
    }
}
