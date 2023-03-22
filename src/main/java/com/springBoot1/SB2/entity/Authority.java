package com.springBoot1.SB2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.springBoot1.SB2.entity.base.BaseTrashableAndAuditing;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authorities", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Builder
public class Authority extends BaseTrashableAndAuditing<Long> {
    @Column(nullable = false, length = 200)
    private String name;//authority name
    @ManyToMany(mappedBy = "authorities", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Role> roles;
}
