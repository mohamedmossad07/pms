package com.springBoot1.SB2.mapper.base;

import com.springBoot1.SB2.entity.*;
import com.springBoot1.SB2.exception.api.ApiException;
import com.springBoot1.SB2.repository.*;
import com.springBoot1.SB2.service.UploadService;
import com.springBoot1.SB2.util.ArrayUtil;
import org.mapstruct.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class IdsMapper {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PharmacyRepository pharmacyRepository;
    @Autowired
    private RoleRepository roleRepository;

    public Address mapLongIdToAddress(Long id) throws ApiException {
        if (id==null)return null;
       return addressRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
    }
    public Category mapLongIdToCategory(Long id) throws ApiException {
        if (id==null)return null;
       return categoryRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
    }
    public Supplier mapLongIdToSupplier(Long id) throws ApiException {
        if (id==null)return null;
       return supplierRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
    }
    public User mapLongIdToUser(Long id) throws ApiException {
        if (id==null)return null;
       return userRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
    }
    public Customer mapLongIdToCustomer(Long id) throws ApiException {
        if (id==null)return null;
       return customerRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
    }
    public Pharmacy mapLongIdToPharmacy(Long id) throws ApiException {
        if (id==null)return null;
       return pharmacyRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
    }
    public Role mapLongIdToRole(Long id) throws ApiException {
        if (id==null)return null;
       return roleRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
    }
    public static Set<PharmacyUser> mapIdsToPharmacyUsers(Set<Long> ids,Pharmacy pharmacy){
        if(ids==null||ids.isEmpty())return null;
        Set<PharmacyUser> users=new HashSet<>();
        ids.forEach(id -> {
            User user=new User();
            user.setId(id);
            users.add(new PharmacyUser(user,pharmacy));
        });
        return users;
    }
    public static PharmacyUser mapPharmacyIdToPharmacyUser(Long id,User user){
        if(id==null)return null;
        Pharmacy pharmacy=new Pharmacy();
        pharmacy.setId(id);
        return new PharmacyUser(user,pharmacy);
    }
    public String mapMultiPartToString(MultipartFile file,@Context String path) throws ApiException {
        if(file==null||file.isEmpty())return null;
        try {
            return uploadService.upload(path, file);
        } catch (IOException e) {
            throw new ApiException(e.getMessage());
        }
    }
}
