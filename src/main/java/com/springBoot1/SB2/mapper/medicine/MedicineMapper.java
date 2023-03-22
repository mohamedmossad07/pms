package com.springBoot1.SB2.mapper.medicine;

import com.springBoot1.SB2.dto.customer.CreateCustomerDTO;
import com.springBoot1.SB2.dto.customer.ShowAllCustomerDTO;
import com.springBoot1.SB2.dto.customer.ShowCustomerDTO;
import com.springBoot1.SB2.dto.customer.UpdateCustomerDTO;
import com.springBoot1.SB2.dto.medicine.CreateMedicineDTO;
import com.springBoot1.SB2.dto.medicine.ShowAllMedicineDTO;
import com.springBoot1.SB2.dto.medicine.ShowMedicineDTO;
import com.springBoot1.SB2.dto.medicine.UpdateMedicineDTO;
import com.springBoot1.SB2.entity.Customer;
import com.springBoot1.SB2.entity.Medicine;
import com.springBoot1.SB2.entity.PharmacyMedicine;
import com.springBoot1.SB2.mapper.address.AddressMapper;
import com.springBoot1.SB2.mapper.base.BaseMapper;
import com.springBoot1.SB2.mapper.base.IdsMapper;
import org.mapstruct.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Mapper(uses = {IdsMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MedicineMapper extends BaseMapper<Medicine, ShowMedicineDTO, ShowAllMedicineDTO, CreateMedicineDTO, UpdateMedicineDTO> {
    List<ShowAllMedicineDTO> mapToShowAll(List<Medicine> medicines,@Context Long pharmacy);
    Medicine unMapCreated(CreateMedicineDTO createMedicineDTO,@Context String path);
    @Mapping(target = "img",ignore = true)
    @Override
    Medicine unMapCreated(CreateMedicineDTO createMedicineDTO);
    Medicine unMapUpdated(@MappingTarget Medicine medicine,UpdateMedicineDTO updateMedicineDTO,@Context  String path);
    @Mapping(target = "img",ignore = true)
    @Override
    Medicine unMapUpdated(@MappingTarget Medicine medicine,UpdateMedicineDTO updateMedicineDTO);
    @Mapping(target = "expiration",expression = "java(getExp(medicine.getPharmacies(),medicine,pharmacy))")
    @Mapping(target = "count",expression = "java(getCount(medicine.getPharmacies(),medicine,pharmacy))")
    ShowAllMedicineDTO map2SingleShowAll(Medicine medicine,@Context  Long pharmacy);
    @Mapping(target = "expiration",expression = "java(getExp(medicine.getPharmacies(),medicine,pharmacy))")
    @Mapping(target = "count",expression = "java(getCount(medicine.getPharmacies(),medicine,pharmacy))")
    ShowMedicineDTO mapToShow(Medicine medicine,@Context Long pharmacy);
    default LocalDate getExp(Set<PharmacyMedicine> pharmacies, Medicine medicine, Long pharmacy){
        if (pharmacies==null||pharmacies.isEmpty())return null;
        return pharmacies.stream().filter(pharmacyMedicine -> Objects.equals(pharmacyMedicine.getMedicine().getId(), medicine.getId()) && Objects.equals(pharmacyMedicine.getPharmacy().getId(), pharmacy)).findFirst().map(PharmacyMedicine::getExpiration).orElse(null);
    }
    default Integer getCount(Set<PharmacyMedicine> pharmacies, Medicine medicine, Long pharmacy){
        if (pharmacies==null||pharmacies.isEmpty())return null;
        return pharmacies.stream().filter(pharmacyMedicine -> Objects.equals(pharmacyMedicine.getMedicine().getId(), medicine.getId()) && Objects.equals(pharmacyMedicine.getPharmacy().getId(), pharmacy)).findFirst().map(PharmacyMedicine::getCount).orElse(null);
    }
}