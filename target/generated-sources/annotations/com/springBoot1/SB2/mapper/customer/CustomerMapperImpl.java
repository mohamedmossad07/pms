package com.springBoot1.SB2.mapper.customer;

import com.springBoot1.SB2.dto.address.ShowIdAndAddressDTO;
import com.springBoot1.SB2.dto.customer.CreateCustomerDTO;
import com.springBoot1.SB2.dto.customer.ShowAllCustomerDTO;
import com.springBoot1.SB2.dto.customer.ShowCustomerDTO;
import com.springBoot1.SB2.dto.customer.UpdateCustomerDTO;
import com.springBoot1.SB2.dto.transaction.ShowIdPriceTransactionDTO;
import com.springBoot1.SB2.entity.Address;
import com.springBoot1.SB2.entity.Customer;
import com.springBoot1.SB2.entity.Transaction;
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
public class CustomerMapperImpl implements CustomerMapper {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private IdsMapper idsMapper;

    @Override
    public ShowCustomerDTO mapToShow(Customer entity) {
        if ( entity == null ) {
            return null;
        }

        ShowCustomerDTO showCustomerDTO = new ShowCustomerDTO();

        showCustomerDTO.setCreatedBy( entity.getCreatedBy() );
        showCustomerDTO.setUpdatedBy( entity.getUpdatedBy() );
        showCustomerDTO.setCreatedAt( entity.getCreatedAt() );
        showCustomerDTO.setUpdatedAt( entity.getUpdatedAt() );
        showCustomerDTO.setId( entity.getId() );
        showCustomerDTO.setTrashed( entity.isTrashed() );
        showCustomerDTO.setFname( entity.getFname() );
        showCustomerDTO.setLname( entity.getLname() );
        showCustomerDTO.setEmail( entity.getEmail() );
        showCustomerDTO.setPhone( entity.getPhone() );
        showCustomerDTO.setAddress( addressToShowIdAndAddressDTO( entity.getAddress() ) );
        showCustomerDTO.setTransactions( transactionSetToShowIdPriceTransactionDTOSet( entity.getTransactions() ) );

        return showCustomerDTO;
    }

    @Override
    public List<ShowAllCustomerDTO> mapToShowAll(List<Customer> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ShowAllCustomerDTO> list = new ArrayList<ShowAllCustomerDTO>( entities.size() );
        for ( Customer customer : entities ) {
            list.add( map2SingleShowAll( customer ) );
        }

        return list;
    }

    @Override
    public Customer unMapCreated(CreateCustomerDTO createDTO) {
        if ( createDTO == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.fname( createDTO.getFname() );
        customer.lname( createDTO.getLname() );
        customer.email( createDTO.getEmail() );
        customer.phone( createDTO.getPhone() );
        try {
            customer.address( idsMapper.mapLongIdToAddress( createDTO.getAddress() ) );
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }

        return customer.build();
    }

    @Override
    public Customer unMapUpdated(Customer entity, UpdateCustomerDTO updateDTO) {
        if ( updateDTO == null ) {
            return entity;
        }

        if ( updateDTO.getFname() != null ) {
            entity.setFname( updateDTO.getFname() );
        }
        if ( updateDTO.getLname() != null ) {
            entity.setLname( updateDTO.getLname() );
        }
        if ( updateDTO.getEmail() != null ) {
            entity.setEmail( updateDTO.getEmail() );
        }
        if ( updateDTO.getPhone() != null ) {
            entity.setPhone( updateDTO.getPhone() );
        }
        try {
            if ( updateDTO.getAddress() != null ) {
                entity.setAddress( idsMapper.mapLongIdToAddress( updateDTO.getAddress() ) );
            }
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }

        return entity;
    }

    @Override
    public ShowAllCustomerDTO map2SingleShowAll(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        ShowAllCustomerDTO showAllCustomerDTO = new ShowAllCustomerDTO();

        showAllCustomerDTO.setCreatedAt( customer.getCreatedAt() );
        showAllCustomerDTO.setId( customer.getId() );
        showAllCustomerDTO.setAddress( addressMapper.mapAddressToString( customer.getAddress() ) );

        showAllCustomerDTO.setName( customer.getFname()+" "+customer.getLname() );

        return showAllCustomerDTO;
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

    protected ShowIdPriceTransactionDTO transactionToShowIdPriceTransactionDTO(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        ShowIdPriceTransactionDTO showIdPriceTransactionDTO = new ShowIdPriceTransactionDTO();

        showIdPriceTransactionDTO.setCreatedBy( transaction.getCreatedBy() );
        showIdPriceTransactionDTO.setUpdatedBy( transaction.getUpdatedBy() );
        showIdPriceTransactionDTO.setCreatedAt( transaction.getCreatedAt() );
        showIdPriceTransactionDTO.setUpdatedAt( transaction.getUpdatedAt() );
        showIdPriceTransactionDTO.setId( transaction.getId() );
        if ( transaction.getPrice() != null ) {
            showIdPriceTransactionDTO.setPrice( transaction.getPrice() );
        }

        return showIdPriceTransactionDTO;
    }

    protected Set<ShowIdPriceTransactionDTO> transactionSetToShowIdPriceTransactionDTOSet(Set<Transaction> set) {
        if ( set == null ) {
            return null;
        }

        Set<ShowIdPriceTransactionDTO> set1 = new LinkedHashSet<ShowIdPriceTransactionDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Transaction transaction : set ) {
            set1.add( transactionToShowIdPriceTransactionDTO( transaction ) );
        }

        return set1;
    }
}
