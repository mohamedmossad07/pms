package com.springBoot1.SB2.repository;

import com.springBoot1.SB2.entity.PharmacyMedicine;
import com.springBoot1.SB2.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface PharmacyMedicineRepository extends BaseRepository<PharmacyMedicine, Long> {
    Optional<PharmacyMedicine> findByPharmacyIdAndMedicineId(Long p, Long m);
    List<PharmacyMedicine> findByPharmacyId(Long p);
}
