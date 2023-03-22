package com.springBoot1.SB2.service;

import com.springBoot1.SB2.dto.address.CreateAddressDTO;
import com.springBoot1.SB2.dto.address.ShowAddressDTO;
import com.springBoot1.SB2.dto.address.ShowAllAddressDTO;
import com.springBoot1.SB2.dto.address.UpdateAddressDTO;
import com.springBoot1.SB2.dto.authority.AddOrUpdateAuthorityDTO;
import com.springBoot1.SB2.dto.authority.ShowAllAuthorityDTO;
import com.springBoot1.SB2.dto.authority.ShowAuthorityDTO;
import com.springBoot1.SB2.dto.role.ShowRoleIdAndNameDTO;
import com.springBoot1.SB2.entity.Address;
import com.springBoot1.SB2.entity.Authority;
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
public class AuthorityServiceImpl extends BaseTrashableCRUDServiceImpl<Authority, Long, AddOrUpdateAuthorityDTO, ShowAllAuthorityDTO, ShowAuthorityDTO, AddOrUpdateAuthorityDTO> {
    @Autowired
    private MessageSource messageSource;
    @PreAuthorize("hasAuthority('" + com.springBoot1.SB2.config.Authority.Authority.AUTHORITYSHOW + "')")
    @Override
    public ShowAuthorityDTO show(Long id) throws UnAuthorizedAccessException, ApiException {
        return super.show(id);
    }

    @PreAuthorize("hasAuthority('" + com.springBoot1.SB2.config.Authority.Authority.AUTHORITYSHOWALL + "')")
    @Override
    public List<ShowAllAuthorityDTO> findAll(int page, int size, String sort) {
        return super.findAll(page, size, sort);
    }

    public void trash(Long id) throws  ApiException {
        Authority authority = baseRepository.findById(id).orElseThrow(() ->new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        authority.setTrashed(true);
        baseRepository.save(authority);
    }

    @Override
    public void restore(Long id) throws  ApiException {
        Authority authority = baseRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        authority.setTrashed(false);
        baseRepository.save(authority);
    }
}
