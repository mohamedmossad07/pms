package com.springBoot1.SB2.mapper.transaction;

import com.springBoot1.SB2.dto.category.ShowIdAndNameCategoryDTO;
import com.springBoot1.SB2.dto.medicine.ShowIdMedicineDTO;
import com.springBoot1.SB2.dto.pharmacy.ShowIdPharmacyDTO;
import com.springBoot1.SB2.dto.transaction.CreateTransactionDTO;
import com.springBoot1.SB2.dto.transaction.ShowAllTransactionDTO;
import com.springBoot1.SB2.dto.transaction.ShowTransactionDTO;
import com.springBoot1.SB2.dto.transaction.ShowTransactionalMedicineDTO;
import com.springBoot1.SB2.dto.transaction.UpdateTransactionDTO;
import com.springBoot1.SB2.entity.Category;
import com.springBoot1.SB2.entity.Medicine;
import com.springBoot1.SB2.entity.MedicineTransaction;
import com.springBoot1.SB2.entity.Pharmacy;
import com.springBoot1.SB2.entity.Transaction;
import com.springBoot1.SB2.exception.api.ApiException;
import com.springBoot1.SB2.mapper.base.IdsMapper;
import com.springBoot1.SB2.mapper.customer.CustomerMapper;
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
public class TransactionMapperImpl implements TransactionMapper {

    @Autowired
    private IdsMapper idsMapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<ShowAllTransactionDTO> mapToShowAll(List<Transaction> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ShowAllTransactionDTO> list = new ArrayList<ShowAllTransactionDTO>( entities.size() );
        for ( Transaction transaction : entities ) {
            list.add( transactionToShowAllTransactionDTO( transaction ) );
        }

        return list;
    }

    @Override
    public Transaction unMapCreated(CreateTransactionDTO createDTO) {
        if ( createDTO == null ) {
            return null;
        }

        Transaction.TransactionBuilder transaction = Transaction.builder();

        try {
            transaction.customer( idsMapper.mapLongIdToCustomer( createDTO.getCustomer() ) );
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }
        try {
            transaction.pharmacy( idsMapper.mapLongIdToPharmacy( createDTO.getPharmacy() ) );
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }

        return transaction.build();
    }

    @Override
    public Transaction unMapUpdated(Transaction entity, UpdateTransactionDTO updateDTO) {
        if ( updateDTO == null ) {
            return entity;
        }

        try {
            if ( updateDTO.getCustomer() != null ) {
                entity.setCustomer( idsMapper.mapLongIdToCustomer( updateDTO.getCustomer() ) );
            }
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }
        try {
            if ( updateDTO.getPharmacy() != null ) {
                entity.setPharmacy( idsMapper.mapLongIdToPharmacy( updateDTO.getPharmacy() ) );
            }
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }

        return entity;
    }

    @Override
    public ShowTransactionDTO mapToShow(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        ShowTransactionDTO showTransactionDTO = new ShowTransactionDTO();

        showTransactionDTO.setMedicines( medicineTransactionSetToShowTransactionalMedicineDTOSet( transaction.getMedicineTransactions() ) );
        showTransactionDTO.setCreatedBy( transaction.getCreatedBy() );
        showTransactionDTO.setUpdatedBy( transaction.getUpdatedBy() );
        showTransactionDTO.setCreatedAt( transaction.getCreatedAt() );
        showTransactionDTO.setUpdatedAt( transaction.getUpdatedAt() );
        showTransactionDTO.setId( transaction.getId() );
        showTransactionDTO.setTrashed( transaction.isTrashed() );
        showTransactionDTO.setCustomer( customerMapper.mapCustomerToShowIdAndCustomerDTO( transaction.getCustomer() ) );
        showTransactionDTO.setPrice( transaction.getPrice() );
        showTransactionDTO.setPharmacy( pharmacyToShowIdPharmacyDTO( transaction.getPharmacy() ) );
        showTransactionDTO.setInvoice( transaction.getInvoice() );

        return showTransactionDTO;
    }

    protected ShowAllTransactionDTO transactionToShowAllTransactionDTO(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        ShowAllTransactionDTO showAllTransactionDTO = new ShowAllTransactionDTO();

        showAllTransactionDTO.setCreatedAt( transaction.getCreatedAt() );
        showAllTransactionDTO.setId( transaction.getId() );
        showAllTransactionDTO.setCustomer( customerMapper.mapCustomerToShowIdAndCustomerDTO( transaction.getCustomer() ) );
        showAllTransactionDTO.setPrice( transaction.getPrice() );

        return showAllTransactionDTO;
    }

    protected ShowIdAndNameCategoryDTO categoryToShowIdAndNameCategoryDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        ShowIdAndNameCategoryDTO showIdAndNameCategoryDTO = new ShowIdAndNameCategoryDTO();

        showIdAndNameCategoryDTO.setId( category.getId() );
        showIdAndNameCategoryDTO.setName( category.getName() );

        return showIdAndNameCategoryDTO;
    }

    protected ShowIdMedicineDTO medicineToShowIdMedicineDTO(Medicine medicine) {
        if ( medicine == null ) {
            return null;
        }

        ShowIdMedicineDTO showIdMedicineDTO = new ShowIdMedicineDTO();

        showIdMedicineDTO.setId( medicine.getId() );
        showIdMedicineDTO.setScientificName( medicine.getScientificName() );
        showIdMedicineDTO.setMarketName( medicine.getMarketName() );
        showIdMedicineDTO.setCategory( categoryToShowIdAndNameCategoryDTO( medicine.getCategory() ) );
        showIdMedicineDTO.setPrice( medicine.getPrice() );

        return showIdMedicineDTO;
    }

    protected ShowTransactionalMedicineDTO medicineTransactionToShowTransactionalMedicineDTO(MedicineTransaction medicineTransaction) {
        if ( medicineTransaction == null ) {
            return null;
        }

        ShowTransactionalMedicineDTO showTransactionalMedicineDTO = new ShowTransactionalMedicineDTO();

        showTransactionalMedicineDTO.setMedicine( medicineToShowIdMedicineDTO( medicineTransaction.getMedicine() ) );
        showTransactionalMedicineDTO.setCount( medicineTransaction.getCount() );

        return showTransactionalMedicineDTO;
    }

    protected Set<ShowTransactionalMedicineDTO> medicineTransactionSetToShowTransactionalMedicineDTOSet(Set<MedicineTransaction> set) {
        if ( set == null ) {
            return null;
        }

        Set<ShowTransactionalMedicineDTO> set1 = new LinkedHashSet<ShowTransactionalMedicineDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MedicineTransaction medicineTransaction : set ) {
            set1.add( medicineTransactionToShowTransactionalMedicineDTO( medicineTransaction ) );
        }

        return set1;
    }

    protected ShowIdPharmacyDTO pharmacyToShowIdPharmacyDTO(Pharmacy pharmacy) {
        if ( pharmacy == null ) {
            return null;
        }

        ShowIdPharmacyDTO showIdPharmacyDTO = new ShowIdPharmacyDTO();

        showIdPharmacyDTO.setId( pharmacy.getId() );
        showIdPharmacyDTO.setName( pharmacy.getName() );

        return showIdPharmacyDTO;
    }
}
