package com.springBoot1.SB2.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.springBoot1.SB2.entity.base.BaseTrashableAndAuditing;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@Component
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pharmacies", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "address"})})
public class Pharmacy extends BaseTrashableAndAuditing<Long> {
    @Column(nullable = false, length = 30)
    private String name;
    @ManyToOne
    @JoinColumn(nullable = false, name = "address")
    @JsonManagedReference
    private Address address;
    @ManyToOne
    @JoinColumn(name = "manager")
    @JsonManagedReference
    private User manager;
    @OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL)
    @JsonManagedReference
    @ToString.Exclude
    private Set<PharmacyUser> users;
    @OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL)
    @JsonManagedReference
    @ToString.Exclude
    private Set<PharmacyMedicine> medicines;
    @OneToMany(mappedBy = "pharmacy")
    @JsonManagedReference
    private Set<Transaction> transactions;
}
