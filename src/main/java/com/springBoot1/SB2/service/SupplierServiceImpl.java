package com.springBoot1.SB2.service;

import com.springBoot1.SB2.config.Authority.Authority;
import com.springBoot1.SB2.dto.role.AddRoleDTO;
import com.springBoot1.SB2.dto.role.ShowAllRoleDTO;
import com.springBoot1.SB2.dto.role.ShowRoleDTO;
import com.springBoot1.SB2.dto.role.UpdateRoleDTO;
import com.springBoot1.SB2.dto.supplier.CreateSupplierDTO;
import com.springBoot1.SB2.dto.supplier.ShowAllSupplierDTO;
import com.springBoot1.SB2.dto.supplier.ShowSupplierDTO;
import com.springBoot1.SB2.dto.supplier.UpdateSupplierDTO;
import com.springBoot1.SB2.entity.Address;
import com.springBoot1.SB2.entity.Role;
import com.springBoot1.SB2.entity.Supplier;
import com.springBoot1.SB2.entity.SupplierPhone;
import com.springBoot1.SB2.exception.api.ApiException;
import com.springBoot1.SB2.exception.api.UnAuthorizedAccessException;
import com.springBoot1.SB2.mapper.base.BaseMapper;
import com.springBoot1.SB2.repository.AddressRepository;
import com.springBoot1.SB2.repository.SupplierPhoneRepository;
import com.springBoot1.SB2.service.base.BaseTrashableCRUDServiceImpl;
import com.springBoot1.SB2.util.ArrayUtil;
import com.springBoot1.SB2.util.ObjectUtil;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl extends BaseTrashableCRUDServiceImpl<Supplier, Long, CreateSupplierDTO, ShowAllSupplierDTO, ShowSupplierDTO, UpdateSupplierDTO> {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private SupplierPhoneRepository supplierPhoneRepository;
    @PreAuthorize("hasAuthority('" + Authority.SUPPLIERCREATE + "')")
    @Override
    public void create(CreateSupplierDTO createSupplierDTO) throws ApiException, UnAuthorizedAccessException {
        super.create(createSupplierDTO);
    }

    @PreAuthorize("hasAuthority('" + Authority.SUPPLIERUPDATE + "')")
    @Override
    public void update(Long id, UpdateSupplierDTO updateSupplierDTO) throws ApiException, UnAuthorizedAccessException {
        if (updateSupplierDTO.getPhones() != null&&!updateSupplierDTO.getPhones().isEmpty()) {
            supplierPhoneRepository.deleteBySupplierId(id);}
        super.update(id,updateSupplierDTO);
    }
    @PreAuthorize("hasAuthority('" + Authority.SUPPLIERSHOW + "')")
    @Override
    public ShowSupplierDTO show(Long id) throws  UnAuthorizedAccessException, ApiException {
        return super.show(id);
    }

    @PreAuthorize("hasAuthority('" + Authority.SUPPLIERSHOWALL + "')")
    @Override
    public List<ShowAllSupplierDTO> findAll(int page, int size, String sort) {
        return super.findAll(page, size, sort);
     }

    @PreAuthorize("hasAuthority('" + Authority.SUPPLIERDELETE + "')")
    @Override
    public void delete(Long aLong) throws  UnAuthorizedAccessException, ApiException {
        super.delete(aLong);
    }

    @PreAuthorize("hasAuthority('" + Authority.SUPPLIERTRASH + "')")
    @Override
    public void trash(Long id) throws  ApiException {
        Supplier supplier = baseRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        supplier.setTrashed(true);
        baseRepository.save(supplier);
    }
    @PreAuthorize("hasAuthority('" + Authority.SUPPLIERRESTORE + "')")
    @Override
    public void restore(Long id) throws  ApiException {
        Supplier supplier = baseRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        supplier.setTrashed(false);
        baseRepository.save(supplier);
    }
}