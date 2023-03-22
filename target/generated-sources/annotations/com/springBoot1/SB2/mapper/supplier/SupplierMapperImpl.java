package com.springBoot1.SB2.mapper.supplier;

import com.springBoot1.SB2.dto.address.ShowIdAndAddressDTO;
import com.springBoot1.SB2.dto.supplier.CreateSupplierDTO;
import com.springBoot1.SB2.dto.supplier.ShowAllSupplierDTO;
import com.springBoot1.SB2.dto.supplier.ShowSupplierDTO;
import com.springBoot1.SB2.dto.supplier.UpdateSupplierDTO;
import com.springBoot1.SB2.entity.Address;
import com.springBoot1.SB2.entity.Supplier;
import com.springBoot1.SB2.entity.SupplierPhone;
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
public class SupplierMapperImpl implements SupplierMapper {

    @Autowired
    private IdsMapper idsMapper;
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public ShowSupplierDTO mapToShow(Supplier entity) {
        if ( entity == null ) {
            return null;
        }

        ShowSupplierDTO showSupplierDTO = new ShowSupplierDTO();

        showSupplierDTO.setCreatedBy( entity.getCreatedBy() );
        showSupplierDTO.setUpdatedBy( entity.getUpdatedBy() );
        showSupplierDTO.setCreatedAt( entity.getCreatedAt() );
        showSupplierDTO.setUpdatedAt( entity.getUpdatedAt() );
        showSupplierDTO.setId( entity.getId() );
        showSupplierDTO.setTrashed( entity.isTrashed() );
        showSupplierDTO.setName( entity.getName() );
        showSupplierDTO.setEmail( entity.getEmail() );
        showSupplierDTO.setAddress( addressToShowIdAndAddressDTO( entity.getAddress() ) );
        showSupplierDTO.setPhones( supplierPhoneSetToStringSet( entity.getPhones() ) );

        return showSupplierDTO;
    }

    @Override
    public List<ShowAllSupplierDTO> mapToShowAll(List<Supplier> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ShowAllSupplierDTO> list = new ArrayList<ShowAllSupplierDTO>( entities.size() );
        for ( Supplier supplier : entities ) {
            list.add( supplierToShowAllSupplierDTO( supplier ) );
        }

        return list;
    }

    @Override
    public Supplier unMapCreated(CreateSupplierDTO createSupplierDTO) {
        if ( createSupplierDTO == null ) {
            return null;
        }

        Supplier supplier = new Supplier();

        supplier.setName( createSupplierDTO.getName() );
        supplier.setEmail( createSupplierDTO.getEmail() );
        try {
            supplier.setAddress( idsMapper.mapLongIdToAddress( createSupplierDTO.getAddress() ) );
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }

        supplier.setPhones( mapStringToSupplierPhone(createSupplierDTO.getPhones(),supplier) );

        return supplier;
    }

    @Override
    public Supplier unMapUpdated(Supplier supplier, UpdateSupplierDTO updateSupplierDTO) {
        if ( updateSupplierDTO == null ) {
            return supplier;
        }

        if ( updateSupplierDTO.getName() != null ) {
            supplier.setName( updateSupplierDTO.getName() );
        }
        if ( updateSupplierDTO.getEmail() != null ) {
            supplier.setEmail( updateSupplierDTO.getEmail() );
        }
        try {
            if ( updateSupplierDTO.getAddress() != null ) {
                supplier.setAddress( idsMapper.mapLongIdToAddress( updateSupplierDTO.getAddress() ) );
            }
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }

        supplier.setPhones( mapStringToSupplierPhone(updateSupplierDTO.getPhones(),supplier) );

        return supplier;
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

    protected Set<String> supplierPhoneSetToStringSet(Set<SupplierPhone> set) {
        if ( set == null ) {
            return null;
        }

        Set<String> set1 = new LinkedHashSet<String>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( SupplierPhone supplierPhone : set ) {
            set1.add( mapSupplierPhoneToString( supplierPhone ) );
        }

        return set1;
    }

    protected ShowAllSupplierDTO supplierToShowAllSupplierDTO(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }

        ShowAllSupplierDTO showAllSupplierDTO = new ShowAllSupplierDTO();

        showAllSupplierDTO.setCreatedAt( supplier.getCreatedAt() );
        showAllSupplierDTO.setId( supplier.getId() );
        showAllSupplierDTO.setName( supplier.getName() );
        showAllSupplierDTO.setEmail( supplier.getEmail() );
        showAllSupplierDTO.setAddress( addressMapper.mapAddressToString( supplier.getAddress() ) );

        return showAllSupplierDTO;
    }
}
