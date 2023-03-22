package com.springBoot1.SB2.service;

import com.springBoot1.SB2.config.Authority.Authority;
import com.springBoot1.SB2.dto.customer.CreateCustomerDTO;
import com.springBoot1.SB2.dto.customer.ShowAllCustomerDTO;
import com.springBoot1.SB2.dto.customer.ShowCustomerDTO;
import com.springBoot1.SB2.dto.customer.UpdateCustomerDTO;
import com.springBoot1.SB2.dto.pharmacy.CreatePharmacyDTO;
import com.springBoot1.SB2.dto.pharmacy.ShowAllPharmacyDTO;
import com.springBoot1.SB2.dto.pharmacy.ShowPharmacyDTO;
import com.springBoot1.SB2.dto.pharmacy.UpdatePharmacyDTO;
import com.springBoot1.SB2.dto.user.ShowIdNameUserDTO;
import com.springBoot1.SB2.entity.*;
import com.springBoot1.SB2.exception.api.ApiException;
import com.springBoot1.SB2.exception.api.UnAuthorizedAccessException;
import com.springBoot1.SB2.mapper.base.BaseMapper;
import com.springBoot1.SB2.repository.AddressRepository;
import com.springBoot1.SB2.repository.MedicineRepository;
import com.springBoot1.SB2.repository.PharmacyUserRepository;
import com.springBoot1.SB2.repository.UserRepository;
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

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PharmacyServiceImpl extends BaseTrashableCRUDServiceImpl<Pharmacy, Long, CreatePharmacyDTO, ShowAllPharmacyDTO, ShowPharmacyDTO, UpdatePharmacyDTO> {
    @Autowired
    private PharmacyUserRepository pharmacyUserRepository;
    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private BaseMapper<Pharmacy, ShowPharmacyDTO, ShowAllPharmacyDTO, CreatePharmacyDTO, UpdatePharmacyDTO> baseMapper;
    @PreAuthorize("hasAuthority('" + Authority.PHARMACYCREATE + "')")
    @Override
    public void create(CreatePharmacyDTO createPharmacyDTO) throws ApiException {
        Pharmacy pharmacy=baseMapper.unMapCreated(createPharmacyDTO);
        pharmacy.setMedicines(medicineRepository.findAll().stream().map(medicine -> new PharmacyMedicine( pharmacy, medicine, 0, LocalDate.now(),false)).collect(Collectors.toSet()));
//        Pharmacy savedPharmacy = baseRepository.save();
        baseRepository.save(pharmacy);
    }

    @PreAuthorize("hasAuthority('" + Authority.PHARMACYUPDATE + "')")
    @Override
    public void update(Long id, UpdatePharmacyDTO updatePharmacyDTO) throws ApiException, UnAuthorizedAccessException {
        if (updatePharmacyDTO.getUsers() != null && !updatePharmacyDTO.getUsers().isEmpty()) {
            pharmacyUserRepository.deleteByPharmacyId(id);
        }
        super.update(id,updatePharmacyDTO);
    }

    @PreAuthorize("hasAuthority('" + Authority.PHARMACYSHOW + "')")
    @Override
    public ShowPharmacyDTO show(Long id) throws ApiException, UnAuthorizedAccessException {
        return super.show(id);
    }

    @PreAuthorize("hasAuthority('" + Authority.PHARMACYTRASH + "')")
    @Override
    public void trash(Long id) throws ApiException {
        Pharmacy pharmacy = baseRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        pharmacy.setTrashed(true);
        baseRepository.save(pharmacy);
    }

    @PreAuthorize("hasAuthority('" + Authority.PHARMACYRESTORE + "')")
    @Override
    public void restore(Long id) throws ApiException {
        Pharmacy pharmacy = baseRepository.findById(id).orElseThrow(() ->new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        pharmacy.setTrashed(false);
        baseRepository.save(pharmacy);
    }

    @PreAuthorize("hasAuthority('" + Authority.PHARMACYSHOWALL + "')")
    @Override
    public List<ShowAllPharmacyDTO> findAll(int page, int size, String sort) {
        return super.findAll(page, size,sort);
    }
}
