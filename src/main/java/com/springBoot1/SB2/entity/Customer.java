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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "customers", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"fname", "lname"})})
public class Customer extends BaseTrashableAndAuditing<Long> {
    @Column(nullable = false, length = 30)
    private String fname;//first name
    @Column(nullable = false, length = 30)
    private String lname;//last name
    @Column(length = 160)
    private String email;
    @Column(length = 30)
    private String phone;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonBackReference
    @ToString.Exclude
    private Set<Transaction> transactions;
    @ManyToOne
    @JoinColumn(name = "address")
    @JsonManagedReference
    private Address address;
}
