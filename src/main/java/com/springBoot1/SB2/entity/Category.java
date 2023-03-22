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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "categories", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Category extends BaseTrashableAndAuditing<Long> {
    @Column(nullable = false, length = 30)
    private String name;
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    private Category parent;
    @JsonBackReference
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Set<Category> subCategories;
    @JsonBackReference
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Medicine> medicines;
}
