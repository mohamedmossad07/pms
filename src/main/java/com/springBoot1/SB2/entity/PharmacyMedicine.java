package com.springBoot1.SB2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.springBoot1.SB2.entity.base.BaseIdAndAuditing;
import com.springBoot1.SB2.entity.base.DateAndUserAuditing;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Component
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pharmacy_medicines")
public class PharmacyMedicine extends BaseIdAndAuditing<Long> {
    @ManyToOne
    @JoinColumn(name = "pharmacy_id",nullable = false)
    @JsonBackReference
    private Pharmacy pharmacy;
    @ManyToOne
    @JoinColumn(name = "medicine_id",nullable = false)
    @JsonBackReference
    private Medicine medicine;
    @Column(columnDefinition = "INT(10) DEFAULT 0")
    private int count;
    @Column(nullable = false)
    private LocalDate expiration;
    @Column(columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean emailed;
}
