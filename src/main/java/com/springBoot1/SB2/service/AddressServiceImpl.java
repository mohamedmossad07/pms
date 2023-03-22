package com.springBoot1.SB2.service;

import com.springBoot1.SB2.config.Authority.Authority;
import com.springBoot1.SB2.dto.address.CreateAddressDTO;
import com.springBoot1.SB2.dto.address.ShowAddressDTO;
import com.springBoot1.SB2.dto.address.ShowAllAddressDTO;
import com.springBoot1.SB2.dto.address.UpdateAddressDTO;
import com.springBoot1.SB2.entity.Address;
import com.springBoot1.SB2.exception.api.ApiException;
import com.springBoot1.SB2.exception.api.UnAuthorizedAccessException;
import com.springBoot1.SB2.mapper.address.AddressMapper;
import com.springBoot1.SB2.mapper.base.BaseMapper;
import com.springBoot1.SB2.service.base.BaseTrashableCRUDServiceImpl;
import com.springBoot1.SB2.util.ArrayUtil;
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
public class AddressServiceImpl extends BaseTrashableCRUDServiceImpl<Address, Long, CreateAddressDTO, ShowAllAddressDTO, ShowAddressDTO, UpdateAddressDTO> {
    @Autowired
    private MessageSource messageSource;
    @PreAuthorize("hasAuthority('" + Authority.ADDRESSCREATE + "')")
    @Override
    public void create(CreateAddressDTO createAddressDTO) throws UnAuthorizedAccessException, ApiException {
        super.create(createAddressDTO);
    }

    @PreAuthorize("hasAuthority('" + Authority.ADDRESSUPDATE + "')")
    @Override
    public void update(Long aLong, UpdateAddressDTO updateAddressDTO) throws UnAuthorizedAccessException, ApiException {
        super.update(aLong, updateAddressDTO);
    }

    @PreAuthorize("hasAuthority('" + Authority.ADDRESSSHOW + "')")
    @Override
    public ShowAddressDTO show(Long id) throws UnAuthorizedAccessException, ApiException {
         return super.show(id);
    }

    @PreAuthorize("hasAuthority('" + Authority.ADDRESSSHOWALL + "')")
    @Override
    public List<ShowAllAddressDTO> findAll(int page, int size, String sort) {
        return super.findAll(page, size, sort);
    }

    @PreAuthorize("hasAuthority('" + Authority.ADDRESSDELETE + "')")
    @Override
    public void delete(Long aLong) throws UnAuthorizedAccessException, ApiException {
        super.delete(aLong);
    }

    @PreAuthorize("hasAuthority('" + Authority.ADDRESSTRASH + "')")
    @Override
    public void trash(Long id) throws ApiException {
        Address address = baseRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        address.setTrashed(true);
        baseRepository.save(address);
    }

    @PreAuthorize("hasAuthority('" + Authority.ADDRESSRESTORE + "')")
    @Override
    public void restore(Long id) throws ApiException {
        Address address = baseRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        address.setTrashed(false);
        baseRepository.save(address);
    }
}
