package com.springBoot1.SB2.service;

import com.springBoot1.SB2.config.Authority.Authority;
import com.springBoot1.SB2.dto.address.CreateAddressDTO;
import com.springBoot1.SB2.dto.address.ShowAddressDTO;
import com.springBoot1.SB2.dto.address.ShowAllAddressDTO;
import com.springBoot1.SB2.dto.address.UpdateAddressDTO;
import com.springBoot1.SB2.dto.category.*;
import com.springBoot1.SB2.entity.Address;
import com.springBoot1.SB2.entity.Category;
import com.springBoot1.SB2.exception.api.ApiException;
import com.springBoot1.SB2.exception.api.UnAuthorizedAccessException;
import com.springBoot1.SB2.mapper.base.BaseMapper;
import com.springBoot1.SB2.service.base.BaseTrashableCRUDServiceImpl;
import com.springBoot1.SB2.util.ArrayUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends BaseTrashableCRUDServiceImpl<Category, Long, CreateCategoryDTO, ShowAllCategoryDTO, ShowCategoryDTO, UpdateCategoryDTO> {
    @Autowired
    private MessageSource messageSource;
    @PreAuthorize("hasAuthority('" + Authority.CATEGORYCREATE + "')")
    @Override
    public void create(CreateCategoryDTO createCategoryDTO) throws ApiException, UnAuthorizedAccessException {
        super.create(createCategoryDTO);
    }

    @PreAuthorize("hasAuthority('" + Authority.CATEGORYUPDATE + "')")
    @Override
    public void update(Long id, UpdateCategoryDTO updateCategoryDTO) throws ApiException, UnAuthorizedAccessException {
         super.update(id,updateCategoryDTO);
    }

    @PreAuthorize("hasAuthority('" + Authority.CATEGORYSHOW + "')")
    @Override
    public ShowCategoryDTO show(Long id) throws  UnAuthorizedAccessException, ApiException {
        return super.show(id);
    }

    @PreAuthorize("hasAuthority('" + Authority.CATEGORYTRASH + "')")
    @Override
    public void trash(Long id) throws  ApiException {
        Category category = baseRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        category.setTrashed(true);
        baseRepository.save(category);
    }

    @PreAuthorize("hasAuthority('" + Authority.CATEGORYRESTORE + "')")
    @Override
    public void restore(Long id) throws  ApiException {
        Category category = baseRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        category.setTrashed(false);
        baseRepository.save(category);
    }

    @PreAuthorize("hasAuthority('" + Authority.CATEGORYDELETE + "')")
    @Override
    public void delete(Long aLong) throws  UnAuthorizedAccessException, ApiException {
        super.delete(aLong);
    }

    @PreAuthorize("hasAuthority('" + Authority.CATEGORYSHOWALL + "')")
    @Override
    public List<ShowAllCategoryDTO> findAll(int page, int size, String sort) {
        return super.findAll(page, size,sort);
    }
}
