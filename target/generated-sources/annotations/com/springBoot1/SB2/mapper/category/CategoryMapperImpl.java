package com.springBoot1.SB2.mapper.category;

import com.springBoot1.SB2.dto.category.CreateCategoryDTO;
import com.springBoot1.SB2.dto.category.ShowAllCategoryDTO;
import com.springBoot1.SB2.dto.category.ShowCategoryDTO;
import com.springBoot1.SB2.dto.category.ShowIdAndNameCategoryDTO;
import com.springBoot1.SB2.dto.category.UpdateCategoryDTO;
import com.springBoot1.SB2.entity.Category;
import com.springBoot1.SB2.exception.api.ApiException;
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
public class CategoryMapperImpl implements CategoryMapper {

    @Autowired
    private IdsMapper idsMapper;

    @Override
    public ShowCategoryDTO mapToShow(Category entity) {
        if ( entity == null ) {
            return null;
        }

        ShowCategoryDTO showCategoryDTO = new ShowCategoryDTO();

        showCategoryDTO.setCreatedBy( entity.getCreatedBy() );
        showCategoryDTO.setUpdatedBy( entity.getUpdatedBy() );
        showCategoryDTO.setCreatedAt( entity.getCreatedAt() );
        showCategoryDTO.setUpdatedAt( entity.getUpdatedAt() );
        showCategoryDTO.setId( entity.getId() );
        showCategoryDTO.setTrashed( entity.isTrashed() );
        showCategoryDTO.setName( entity.getName() );
        showCategoryDTO.setParent( categoryToShowIdAndNameCategoryDTO( entity.getParent() ) );
        showCategoryDTO.setSubCategories( categorySetToShowIdAndNameCategoryDTOSet( entity.getSubCategories() ) );

        return showCategoryDTO;
    }

    @Override
    public List<ShowAllCategoryDTO> mapToShowAll(List<Category> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ShowAllCategoryDTO> list = new ArrayList<ShowAllCategoryDTO>( entities.size() );
        for ( Category category : entities ) {
            list.add( categoryToShowAllCategoryDTO( category ) );
        }

        return list;
    }

    @Override
    public Category unMapCreated(CreateCategoryDTO createDTO) {
        if ( createDTO == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.name( createDTO.getName() );
        try {
            category.parent( idsMapper.mapLongIdToCategory( createDTO.getParent() ) );
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }

        return category.build();
    }

    @Override
    public Category unMapUpdated(Category entity, UpdateCategoryDTO updateDTO) {
        if ( updateDTO == null ) {
            return entity;
        }

        if ( updateDTO.getName() != null ) {
            entity.setName( updateDTO.getName() );
        }
        try {
            if ( updateDTO.getParent() != null ) {
                entity.setParent( idsMapper.mapLongIdToCategory( updateDTO.getParent() ) );
            }
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }

        return entity;
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

    protected Set<ShowIdAndNameCategoryDTO> categorySetToShowIdAndNameCategoryDTOSet(Set<Category> set) {
        if ( set == null ) {
            return null;
        }

        Set<ShowIdAndNameCategoryDTO> set1 = new LinkedHashSet<ShowIdAndNameCategoryDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Category category : set ) {
            set1.add( categoryToShowIdAndNameCategoryDTO( category ) );
        }

        return set1;
    }

    protected ShowAllCategoryDTO categoryToShowAllCategoryDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        ShowAllCategoryDTO showAllCategoryDTO = new ShowAllCategoryDTO();

        showAllCategoryDTO.setCreatedAt( category.getCreatedAt() );
        showAllCategoryDTO.setId( category.getId() );
        showAllCategoryDTO.setName( category.getName() );

        return showAllCategoryDTO;
    }
}
