package com.springBoot1.SB2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.springBoot1.SB2.entity.base.BaseIdAndAuditing;
import com.springBoot1.SB2.entity.base.DateAndUserAuditing;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "supplier_phones")
public class SupplierPhone extends BaseIdAndAuditing<Long> {
    @Column(nullable = false, unique = true)
    private String phone;
    @ManyToOne
    @JoinColumn(name = "supplier_id",nullable = false)
    @JsonBackReference
    private Supplier supplier;
}
