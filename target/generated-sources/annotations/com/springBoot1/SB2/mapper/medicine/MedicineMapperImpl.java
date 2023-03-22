package com.springBoot1.SB2.mapper.medicine;

import com.springBoot1.SB2.dto.category.ShowIdAndNameCategoryDTO;
import com.springBoot1.SB2.dto.medicine.CreateMedicineDTO;
import com.springBoot1.SB2.dto.medicine.ShowAllMedicineDTO;
import com.springBoot1.SB2.dto.medicine.ShowMedicineDTO;
import com.springBoot1.SB2.dto.medicine.UpdateMedicineDTO;
import com.springBoot1.SB2.dto.supplier.ShowIdAndNameSupplierDTO;
import com.springBoot1.SB2.entity.Category;
import com.springBoot1.SB2.entity.Medicine;
import com.springBoot1.SB2.entity.Supplier;
import com.springBoot1.SB2.exception.api.ApiException;
import com.springBoot1.SB2.mapper.base.IdsMapper;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-23T01:37:42+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Ubuntu)"
)
@Component
public class MedicineMapperImpl implements MedicineMapper {

    @Autowired
    private IdsMapper idsMapper;

    @Override
    public ShowMedicineDTO mapToShow(Medicine entity) {
        if ( entity == null ) {
            return null;
        }

        ShowMedicineDTO showMedicineDTO = new ShowMedicineDTO();

        showMedicineDTO.setCreatedBy( entity.getCreatedBy() );
        showMedicineDTO.setUpdatedBy( entity.getUpdatedBy() );
        showMedicineDTO.setCreatedAt( entity.getCreatedAt() );
        showMedicineDTO.setUpdatedAt( entity.getUpdatedAt() );
        showMedicineDTO.setId( entity.getId() );
        showMedicineDTO.setTrashed( entity.isTrashed() );
        showMedicineDTO.setScientificName( entity.getScientificName() );
        showMedicineDTO.setMarketName( entity.getMarketName() );
        showMedicineDTO.setCategory( categoryToShowIdAndNameCategoryDTO( entity.getCategory() ) );
        showMedicineDTO.setDescription( entity.getDescription() );
        showMedicineDTO.setPrice( entity.getPrice() );
        showMedicineDTO.setType( entity.getType() );
        showMedicineDTO.setSupplier( supplierToShowIdAndNameSupplierDTO( entity.getSupplier() ) );
        showMedicineDTO.setImg( entity.getImg() );

        return showMedicineDTO;
    }

    @Override
    public List<ShowAllMedicineDTO> mapToShowAll(List<Medicine> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ShowAllMedicineDTO> list = new ArrayList<ShowAllMedicineDTO>( entities.size() );
        for ( Medicine medicine : entities ) {
            list.add( medicineToShowAllMedicineDTO( medicine ) );
        }

        return list;
    }

    @Override
    public List<ShowAllMedicineDTO> mapToShowAll(List<Medicine> medicines, Long pharmacy) {
        if ( medicines == null ) {
            return null;
        }

        List<ShowAllMedicineDTO> list = new ArrayList<ShowAllMedicineDTO>( medicines.size() );
        for ( Medicine medicine : medicines ) {
            list.add( map2SingleShowAll( medicine, pharmacy ) );
        }

        return list;
    }

    @Override
    public Medicine unMapCreated(CreateMedicineDTO createMedicineDTO, String path) {
        if ( createMedicineDTO == null ) {
            return null;
        }

        Medicine.MedicineBuilder medicine = Medicine.builder();

        medicine.scientificName( createMedicineDTO.getScientificName() );
        medicine.marketName( createMedicineDTO.getMarketName() );
        try {
            medicine.category( idsMapper.mapLongIdToCategory( createMedicineDTO.getCategory() ) );
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }
        medicine.description( createMedicineDTO.getDescription() );
        medicine.price( createMedicineDTO.getPrice() );
        medicine.type( createMedicineDTO.getType() );
        try {
            medicine.supplier( idsMapper.mapLongIdToSupplier( createMedicineDTO.getSupplier() ) );
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }
        try {
            medicine.img( idsMapper.mapMultiPartToString( createMedicineDTO.getImg(), path ) );
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }

        return medicine.build();
    }

    @Override
    public Medicine unMapCreated(CreateMedicineDTO createMedicineDTO) {
        if ( createMedicineDTO == null ) {
            return null;
        }

        Medicine.MedicineBuilder medicine = Medicine.builder();

        medicine.scientificName( createMedicineDTO.getScientificName() );
        medicine.marketName( createMedicineDTO.getMarketName() );
        try {
            medicine.category( idsMapper.mapLongIdToCategory( createMedicineDTO.getCategory() ) );
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }
        medicine.description( createMedicineDTO.getDescription() );
        medicine.price( createMedicineDTO.getPrice() );
        medicine.type( createMedicineDTO.getType() );
        try {
            medicine.supplier( idsMapper.mapLongIdToSupplier( createMedicineDTO.getSupplier() ) );
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }

        return medicine.build();
    }

    @Override
    public Medicine unMapUpdated(Medicine medicine, UpdateMedicineDTO updateMedicineDTO, String path) {
        if ( updateMedicineDTO == null ) {
            return medicine;
        }

        if ( updateMedicineDTO.getScientificName() != null ) {
            medicine.setScientificName( updateMedicineDTO.getScientificName() );
        }
        if ( updateMedicineDTO.getMarketName() != null ) {
            medicine.setMarketName( updateMedicineDTO.getMarketName() );
        }
        try {
            if ( updateMedicineDTO.getCategory() != null ) {
                medicine.setCategory( idsMapper.mapLongIdToCategory( updateMedicineDTO.getCategory() ) );
            }
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }
        if ( updateMedicineDTO.getDescription() != null ) {
            medicine.setDescription( updateMedicineDTO.getDescription() );
        }
        if ( updateMedicineDTO.getPrice() != null ) {
            medicine.setPrice( updateMedicineDTO.getPrice() );
        }
        if ( updateMedicineDTO.getType() != null ) {
            medicine.setType( updateMedicineDTO.getType() );
        }
        try {
            if ( updateMedicineDTO.getSupplier() != null ) {
                medicine.setSupplier( idsMapper.mapLongIdToSupplier( updateMedicineDTO.getSupplier() ) );
            }
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }
        try {
            if ( updateMedicineDTO.getImg() != null ) {
                medicine.setImg( idsMapper.mapMultiPartToString( updateMedicineDTO.getImg(), path ) );
            }
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }

        return medicine;
    }

    @Override
    public Medicine unMapUpdated(Medicine medicine, UpdateMedicineDTO updateMedicineDTO) {
        if ( updateMedicineDTO == null ) {
            return medicine;
        }

        if ( updateMedicineDTO.getScientificName() != null ) {
            medicine.setScientificName( updateMedicineDTO.getScientificName() );
        }
        if ( updateMedicineDTO.getMarketName() != null ) {
            medicine.setMarketName( updateMedicineDTO.getMarketName() );
        }
        try {
            if ( updateMedicineDTO.getCategory() != null ) {
                medicine.setCategory( idsMapper.mapLongIdToCategory( updateMedicineDTO.getCategory() ) );
            }
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }
        if ( updateMedicineDTO.getDescription() != null ) {
            medicine.setDescription( updateMedicineDTO.getDescription() );
        }
        if ( updateMedicineDTO.getPrice() != null ) {
            medicine.setPrice( updateMedicineDTO.getPrice() );
        }
        if ( updateMedicineDTO.getType() != null ) {
            medicine.setType( updateMedicineDTO.getType() );
        }
        try {
            if ( updateMedicineDTO.getSupplier() != null ) {
                medicine.setSupplier( idsMapper.mapLongIdToSupplier( updateMedicineDTO.getSupplier() ) );
            }
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }

        return medicine;
    }

    @Override
    public ShowAllMedicineDTO map2SingleShowAll(Medicine medicine, Long pharmacy) {
        if ( medicine == null ) {
            return null;
        }

        ShowAllMedicineDTO showAllMedicineDTO = new ShowAllMedicineDTO();

        showAllMedicineDTO.setCreatedAt( medicine.getCreatedAt() );
        showAllMedicineDTO.setId( medicine.getId() );
        showAllMedicineDTO.setScientificName( medicine.getScientificName() );
        showAllMedicineDTO.setMarketName( medicine.getMarketName() );
        showAllMedicineDTO.setCategory( categoryToShowIdAndNameCategoryDTO1( medicine.getCategory(), pharmacy ) );
        showAllMedicineDTO.setPrice( medicine.getPrice() );
        showAllMedicineDTO.setType( medicine.getType() );

        showAllMedicineDTO.setExpiration( getExp(medicine.getPharmacies(),medicine,pharmacy) );
        showAllMedicineDTO.setCount( getCount(medicine.getPharmacies(),medicine,pharmacy) );

        return showAllMedicineDTO;
    }

    @Override
    public ShowMedicineDTO mapToShow(Medicine medicine, Long pharmacy) {
        if ( medicine == null ) {
            return null;
        }

        ShowMedicineDTO showMedicineDTO = new ShowMedicineDTO();

        showMedicineDTO.setCreatedBy( medicine.getCreatedBy() );
        showMedicineDTO.setUpdatedBy( medicine.getUpdatedBy() );
        showMedicineDTO.setCreatedAt( medicine.getCreatedAt() );
        showMedicineDTO.setUpdatedAt( medicine.getUpdatedAt() );
        showMedicineDTO.setId( medicine.getId() );
        showMedicineDTO.setTrashed( medicine.isTrashed() );
        showMedicineDTO.setScientificName( medicine.getScientificName() );
        showMedicineDTO.setMarketName( medicine.getMarketName() );
        showMedicineDTO.setCategory( categoryToShowIdAndNameCategoryDTO1( medicine.getCategory(), pharmacy ) );
        showMedicineDTO.setDescription( medicine.getDescription() );
        showMedicineDTO.setPrice( medicine.getPrice() );
        showMedicineDTO.setType( medicine.getType() );
        showMedicineDTO.setSupplier( supplierToShowIdAndNameSupplierDTO1( medicine.getSupplier(), pharmacy ) );
        showMedicineDTO.setImg( medicine.getImg() );

        showMedicineDTO.setExpiration( getExp(medicine.getPharmacies(),medicine,pharmacy) );
        showMedicineDTO.setCount( getCount(medicine.getPharmacies(),medicine,pharmacy) );

        return showMedicineDTO;
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

    protected ShowIdAndNameSupplierDTO supplierToShowIdAndNameSupplierDTO(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }

        ShowIdAndNameSupplierDTO showIdAndNameSupplierDTO = new ShowIdAndNameSupplierDTO();

        showIdAndNameSupplierDTO.setId( supplier.getId() );
        showIdAndNameSupplierDTO.setName( supplier.getName() );

        return showIdAndNameSupplierDTO;
    }

    protected ShowAllMedicineDTO medicineToShowAllMedicineDTO(Medicine medicine) {
        if ( medicine == null ) {
            return null;
        }

        ShowAllMedicineDTO showAllMedicineDTO = new ShowAllMedicineDTO();

        showAllMedicineDTO.setCreatedAt( medicine.getCreatedAt() );
        showAllMedicineDTO.setId( medicine.getId() );
        showAllMedicineDTO.setScientificName( medicine.getScientificName() );
        showAllMedicineDTO.setMarketName( medicine.getMarketName() );
        showAllMedicineDTO.setCategory( categoryToShowIdAndNameCategoryDTO( medicine.getCategory() ) );
        showAllMedicineDTO.setPrice( medicine.getPrice() );
        showAllMedicineDTO.setType( medicine.getType() );

        return showAllMedicineDTO;
    }

    protected ShowIdAndNameCategoryDTO categoryToShowIdAndNameCategoryDTO1(Category category, Long pharmacy) {
        if ( category == null ) {
            return null;
        }

        ShowIdAndNameCategoryDTO showIdAndNameCategoryDTO = new ShowIdAndNameCategoryDTO();

        showIdAndNameCategoryDTO.setId( category.getId() );
        showIdAndNameCategoryDTO.setName( category.getName() );

        return showIdAndNameCategoryDTO;
    }

    protected ShowIdAndNameSupplierDTO supplierToShowIdAndNameSupplierDTO1(Supplier supplier, Long pharmacy) {
        if ( supplier == null ) {
            return null;
        }

        ShowIdAndNameSupplierDTO showIdAndNameSupplierDTO = new ShowIdAndNameSupplierDTO();

        showIdAndNameSupplierDTO.setId( supplier.getId() );
        showIdAndNameSupplierDTO.setName( supplier.getName() );

        return showIdAndNameSupplierDTO;
    }
}
