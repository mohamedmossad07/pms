package com.springBoot1.SB2.service;

import com.springBoot1.SB2.config.Authority.Authority;
import com.springBoot1.SB2.dto.category.CreateCategoryDTO;
import com.springBoot1.SB2.dto.category.ShowAllCategoryDTO;
import com.springBoot1.SB2.dto.category.ShowCategoryDTO;
import com.springBoot1.SB2.dto.category.UpdateCategoryDTO;
import com.springBoot1.SB2.dto.customer.CreateCustomerDTO;
import com.springBoot1.SB2.dto.customer.ShowAllCustomerDTO;
import com.springBoot1.SB2.dto.customer.ShowCustomerDTO;
import com.springBoot1.SB2.dto.customer.UpdateCustomerDTO;
import com.springBoot1.SB2.dto.transaction.ShowIdPriceTransactionDTO;
import com.springBoot1.SB2.entity.Address;
import com.springBoot1.SB2.entity.Category;
import com.springBoot1.SB2.entity.Customer;
import com.springBoot1.SB2.exception.api.ApiException;
import com.springBoot1.SB2.exception.api.UnAuthorizedAccessException;
import com.springBoot1.SB2.mapper.base.BaseMapper;
import com.springBoot1.SB2.repository.AddressRepository;
import com.springBoot1.SB2.repository.CustomerRepository;
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
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl extends BaseTrashableCRUDServiceImpl<Customer, Long, CreateCustomerDTO, ShowAllCustomerDTO, ShowCustomerDTO, UpdateCustomerDTO> {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MessageSource messageSource;
    @PreAuthorize("hasAuthority('" + Authority.CUSTOMERCREATE + "')")
    @Override
    public void create(CreateCustomerDTO createCustomerDTO) throws ApiException, UnAuthorizedAccessException {
        super.create(createCustomerDTO);
     }

    @PreAuthorize("hasAuthority('" + Authority.CUSTOMERUPDATE + "')")
    @Override
    public void update(Long id, UpdateCustomerDTO updateCustomerDTO) throws ApiException, UnAuthorizedAccessException {
        super.update(id,updateCustomerDTO);
    }

    @PreAuthorize("hasAuthority('" + Authority.CUSTOMERSHOW + "')")
    @Override
    public ShowCustomerDTO show(Long id) throws  UnAuthorizedAccessException, ApiException {
        return super.show(id);
    }

    @PreAuthorize("hasAuthority('" + Authority.CUSTOMERDELETE + "')")
    @Override
    public void delete(Long aLong) throws  UnAuthorizedAccessException, ApiException {
        super.delete(aLong);
    }

    @PreAuthorize("hasAuthority('" + Authority.CUSTOMERSHOWALL + "')")
    @Override
    public List<ShowAllCustomerDTO> findAll(int page, int size, String sort) {
    return super.findAll(page, size, sort);
    }

    @PreAuthorize("hasAuthority('" + Authority.CUSTOMERTRASH + "')")
    @Override
    public void trash(Long id) throws  ApiException {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        customer.setTrashed(true);
        customerRepository.save(customer);
    }

    @PreAuthorize("hasAuthority('" + Authority.CUSTOMERRESTORE + "')")
    @Override
    public void restore(Long id) throws  ApiException {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        customer.setTrashed(false);
        customerRepository.save(customer);
    }
}
