package com.springBoot1.SB2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.springBoot1.SB2.entity.base.BaseTrashableAndAuditing;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@Component
@Builder
@Table(name = "addresses", uniqueConstraints = {@UniqueConstraint(columnNames = {"governorate", "city", "town", "street"})})
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseTrashableAndAuditing<Long> {
    @Column(nullable = false, length = 20)
    private String governorate;
    @Column(nullable = false, length = 20)
    private String city;
    @Column(nullable = false, length = 20)
    private String town;
    @Column(length = 40)
    private String street;
    @OneToMany(mappedBy = "address")
    @ToString.Exclude
    @JsonBackReference
    private Set<Supplier> suppliers;
    @OneToMany(mappedBy = "address")
    @ToString.Exclude
    @JsonBackReference
    private Set<Customer> customers;
    @OneToMany(mappedBy = "address")
    @JsonBackReference
    @ToString.Exclude
    private Set<Pharmacy> pharmacies;
}
