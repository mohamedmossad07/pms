package com.springBoot1.SB2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.springBoot1.SB2.entity.base.BaseTrashableAndAuditing;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Component
@ToString
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction extends BaseTrashableAndAuditing<Long> {
    @ManyToOne
    @JoinColumn(name = "customer", nullable = false)
    @JsonManagedReference
    private Customer customer;
    private Double price;
    private String invoice;
    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonManagedReference
    @ToString.Exclude
    private Set<MedicineTransaction> medicineTransactions;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "pharmacy", nullable = false)
    private Pharmacy pharmacy;
}