package com.springBoot1.SB2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.springBoot1.SB2.entity.base.BaseTrashableAndAuditing;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
@Component
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "medicines", uniqueConstraints = {@UniqueConstraint(columnNames = "scientificName"),
        @UniqueConstraint(columnNames = "marketName")})
public class Medicine extends BaseTrashableAndAuditing<Long> {
    @Column(nullable = false, length = 50)
    private String scientificName;
    @Column(nullable = false, length = 50)
    private String marketName;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(nullable = false, name = "category_id")
    private Category category;
    @Column(length = 100)
    private String description;
    @Column(nullable = false)
    private Double price;
    @Enumerated(EnumType.STRING)
    private MedicineType type;
    @ManyToOne
    @JoinColumn(name = "supplier", nullable = false)
    @JsonBackReference
    private Supplier supplier;
    private String img;
    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL)
    @JsonManagedReference
    @ToString.Exclude
    private Set<MedicineTransaction> medicineTransactions;
    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL)
    @JsonManagedReference
    @ToString.Exclude
    private Set<PharmacyMedicine> pharmacies;
    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL)
    @JsonManagedReference
    @ToString.Exclude
    private Set<Notification> notifications;

}