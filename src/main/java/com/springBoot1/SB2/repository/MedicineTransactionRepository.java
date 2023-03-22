package com.springBoot1.SB2.repository;

import com.springBoot1.SB2.entity.MedicineTransaction;
import com.springBoot1.SB2.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface MedicineTransactionRepository extends BaseRepository<MedicineTransaction, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "delete from medicine_transactions where transaction_id = ?1")
    void deleteByTransactionId(Long id);
}
