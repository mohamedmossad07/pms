package com.springBoot1.SB2.service;

import com.springBoot1.SB2.config.Authority.Authority;
import com.springBoot1.SB2.dto.pharmacy.CreatePharmacyDTO;
import com.springBoot1.SB2.dto.pharmacy.ShowAllPharmacyDTO;
import com.springBoot1.SB2.dto.pharmacy.ShowPharmacyDTO;
import com.springBoot1.SB2.dto.pharmacy.UpdatePharmacyDTO;
import com.springBoot1.SB2.dto.role.AddRoleDTO;
import com.springBoot1.SB2.dto.role.ShowAllRoleDTO;
import com.springBoot1.SB2.dto.role.ShowRoleDTO;
import com.springBoot1.SB2.dto.role.UpdateRoleDTO;
import com.springBoot1.SB2.dto.user.ShowIdNameUserDTO;
import com.springBoot1.SB2.entity.Pharmacy;
import com.springBoot1.SB2.entity.Role;
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

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends BaseTrashableCRUDServiceImpl<Role, Long, AddRoleDTO, ShowAllRoleDTO, ShowRoleDTO, UpdateRoleDTO> {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private BaseMapper<Role, ShowRoleDTO, ShowAllRoleDTO, AddRoleDTO, UpdateRoleDTO> baseMapper;
    @PreAuthorize("hasAuthority('" + Authority.ROLECREATE + "')")
    @Override
    public void create(AddRoleDTO roleDTO) throws UnAuthorizedAccessException, ApiException {
        checkPriority(roleDTO.getPriority());
        super.create(roleDTO);
    }

    @PreAuthorize("hasAuthority('" + Authority.ROLEUPDATE + "')")
    @Override
    public void update(Long id, UpdateRoleDTO roleDTO) throws UnAuthorizedAccessException, ApiException {
        checkPriority(roleDTO.getPriority());
         super.update(id,roleDTO);
    }

    @PreAuthorize("hasAuthority('" + Authority.ROLESHOW + "')")
    @Override
    public ShowRoleDTO show(Long id) throws UnAuthorizedAccessException, ApiException {
        Role role = baseRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        checkPriority(role.getPriority());
        return baseMapper.mapToShow(role);
    }

    @PreAuthorize("hasAuthority('" + Authority.ROLESHOWALL + "')")
    @Override
    public List<ShowAllRoleDTO> findAll(int page, int size, String sort) {
        return super.findAll(page, size, sort);
    }

    @PreAuthorize("hasAuthority('" + Authority.ROLETRASH + "')")
    @Override
    public void trash(@NotNull Long id) throws UnAuthorizedAccessException, ApiException {
        Role role = baseRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        checkPriority(role.getPriority());
        role.setTrashed(true);
        baseRepository.save(role);
    }

    @PreAuthorize("hasAuthority('" + Authority.ROLERESTORE + "')")
    @Override
    public void restore(@NotNull Long id) throws UnAuthorizedAccessException, ApiException {
        Role role = baseRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        checkPriority(role.getPriority());
        role.setTrashed(false);
        baseRepository.save(role);
    }

    @PreAuthorize("hasAuthority('" + Authority.ROLEDELETE + "')")
    @Override
    public void delete(Long aLong) throws UnAuthorizedAccessException, ApiException {
        super.delete(aLong);
    }
}
