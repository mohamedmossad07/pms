package com.springBoot1.SB2.entity;

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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "suppliers")
public class Supplier extends BaseTrashableAndAuditing<Long> {
    @Column(nullable = false, length = 50, unique = true)
    private String name;
    @Column(nullable = false, length = 50, unique = true)
    private String email;
    @ManyToOne
    @JoinColumn(nullable = false, name = "address")
    @JsonManagedReference
    private Address address;
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Medicine> medicines;
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    @JsonManagedReference
    @ToString.Exclude
    private Set<SupplierPhone> phones;
}
