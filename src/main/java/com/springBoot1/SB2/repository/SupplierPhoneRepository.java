package com.springBoot1.SB2.repository;

import com.springBoot1.SB2.entity.SupplierPhone;
import com.springBoot1.SB2.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface SupplierPhoneRepository extends BaseRepository<SupplierPhone, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "delete from supplier_phones where supplier_id = ?1")
    void deleteBySupplierId(Long id);
}
