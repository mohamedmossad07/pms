package com.springBoot1.SB2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "medicine_transactions")
public class MedicineTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "transaction_id",nullable = false)
    @JsonBackReference
    private Transaction transaction;
    @ManyToOne
    @JoinColumn(name = "medicine_id",nullable = false)
    @JsonBackReference
    private Medicine medicine;
    private Integer count;
}