package com.springBoot1.SB2.service;

import com.springBoot1.SB2.config.Authority.Authority;
import com.springBoot1.SB2.config.UserDetailsImpl;
import com.springBoot1.SB2.dto.category.ShowIdAndNameCategoryDTO;
import com.springBoot1.SB2.dto.customer.CreateCustomerDTO;
import com.springBoot1.SB2.dto.customer.ShowAllCustomerDTO;
import com.springBoot1.SB2.dto.customer.ShowCustomerDTO;
import com.springBoot1.SB2.dto.customer.UpdateCustomerDTO;
import com.springBoot1.SB2.dto.medicine.*;
import com.springBoot1.SB2.dto.supplier.ShowIdAndNameSupplierDTO;
import com.springBoot1.SB2.entity.*;
import com.springBoot1.SB2.exception.api.ApiException;
import com.springBoot1.SB2.exception.api.UnAuthorizedAccessException;
import com.springBoot1.SB2.mapper.base.BaseMapper;
import com.springBoot1.SB2.mapper.medicine.MedicineMapper;
import com.springBoot1.SB2.repository.*;
import com.springBoot1.SB2.service.base.BaseTrashableCRUDServiceImpl;
import com.springBoot1.SB2.util.ArrayUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class MedicineServiceImpl extends BaseTrashableCRUDServiceImpl<Medicine, Long, CreateMedicineDTO, ShowAllMedicineDTO, ShowMedicineDTO, UpdateMedicineDTO> {
    @Autowired
    private PharmacyRepository pharmacyRepository;
    @Autowired
    private PharmacyMedicineRepository pharmacyMedicineRepository;
    @Autowired
    private MessageSource messageSource;
    @Value("${uploadPaths.medicinePath}")
    private String medicinePath;
    @Autowired
    private MedicineMapper medicineMapper;
    @PreAuthorize("hasAuthority('" + Authority.MEDICINECREATE + "')")
    @Override
    public void create(CreateMedicineDTO createMedicineDTO) throws ApiException {
        Medicine medicine =medicineMapper.unMapCreated(createMedicineDTO,medicinePath);
        medicine.setPharmacies(pharmacyRepository.findAll().stream().map(pharmacy -> {
            LocalDate exp = LocalDate.now();
            try {
                if(createMedicineDTO.getExpiration()!=null){
                    exp=LocalDate.parse(createMedicineDTO.getExpiration());
                }
            } catch (DateTimeParseException e) {
                throw new RuntimeException(new ApiException(e.getMessage()));
            }
            return new PharmacyMedicine( pharmacy, medicine, 0, exp,false);
        }).collect(Collectors.toSet()));
        baseRepository.save(medicine);
    }

    @PreAuthorize("hasAuthority('" + Authority.MEDICINEUPDATE + "')")
    @Override
    public void update(Long id, UpdateMedicineDTO updateMedicineDTO) throws ApiException {
        Medicine medicine = baseRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        medicine=medicineMapper.unMapUpdated(medicine,updateMedicineDTO,medicinePath);
        if (updateMedicineDTO.getImg()!=null&&!updateMedicineDTO.getImg().isEmpty()) {
            try {
                if (medicine.getImg() != null) {
                    Path path = Paths.get(medicine.getImg());
                    Files.deleteIfExists(path);
                }
            } catch (IOException e) {
                throw new ApiException(e.getMessage());
            }
        }
        if (updateMedicineDTO.getExpiration() != null) {
            if(updateMedicineDTO.getPharmacy()==null){
                throw new ApiException(messageSource.getMessage("exceptions.pharmacies.Notnull", ArrayUtil.of(), LocaleContextHolder.getLocale()));
            }
            Medicine finalMedicine = medicine;
            medicine.getPharmacies().forEach(pharmacyMedicine -> {
                if(Objects.equals(pharmacyMedicine.getPharmacy().getId(), updateMedicineDTO.getPharmacy()) && Objects.equals(pharmacyMedicine.getMedicine().getId(), finalMedicine.getId()))
                    pharmacyMedicine.setExpiration(LocalDate.parse(updateMedicineDTO.getExpiration()));
            });
        }
        baseRepository.save(medicine);
    }
    @PreAuthorize("hasAuthority('" + Authority.MEDICINESHOW + "')")
    public ShowMedicineDTO show(Long id,Long pharmacy) throws UnAuthorizedAccessException, ApiException {
        Medicine e = baseRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        return medicineMapper.mapToShow(e,pharmacy);
    }
    @PreAuthorize("hasAuthority('" + Authority.MEDICINESHOWALL + "')")
    public List<ShowAllMedicineDTO> findAll(int page, int size, String sort,Long pharmacy) throws ApiException {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Medicine> data = baseRepository.findAll(pageable);
        return medicineMapper.mapToShowAll(data.getContent(),pharmacy);
    }

    @PreAuthorize("hasAuthority('" + Authority.MEDICINEDELETE + "')")
    @Override
    public void delete(Long aLong) throws UnAuthorizedAccessException, ApiException {
        super.delete(aLong);
    }

    @PreAuthorize("hasAuthority('" + Authority.MEDICINETRASH + "')")
    @Override
    public void trash(Long id) throws ApiException {
        Medicine medicine = baseRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        medicine.setTrashed(true);
        baseRepository.save(medicine);
    }
    @PreAuthorize("hasAuthority('" + Authority.MEDICINERESTORE + "')")
    @Override
    public void restore(Long id) throws ApiException {
        Medicine medicine = baseRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        medicine.setTrashed(false);
        baseRepository.save(medicine);
    }
    @PreAuthorize("hasAuthority('" + Authority.MEDICINEUPDATECOUNT + "')")
    public void updateCount(UpdateMedicineCount updateMedicineCount) throws  ApiException {
        PharmacyMedicine pharmacyMedicine = pharmacyMedicineRepository.findByPharmacyIdAndMedicineId(updateMedicineCount.getPharmacy(), updateMedicineCount.getMedicine()).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of("Pharmacy or medicine "), LocaleContextHolder.getLocale())));
        pharmacyMedicine.setCount(updateMedicineCount.getCount());
        pharmacyMedicine.setEmailed(false);
        pharmacyMedicineRepository.save(pharmacyMedicine);
    }
}
