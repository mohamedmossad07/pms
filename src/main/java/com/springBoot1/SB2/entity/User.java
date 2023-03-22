package com.springBoot1.SB2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.springBoot1.SB2.entity.base.BaseTrashableAndAuditing;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@ToString
@Component
@Table(name = "users")
public class User extends BaseTrashableAndAuditing<Long> {
    @Column(nullable = false, length = 30)
    private String fname;//first name
    @Column(nullable = false, length = 30)
    private String lname;//last name
    @Column(nullable = false, length = 60, unique = true)
    private String username;
    @Column(length = 160)
    private String email;
    @Column(nullable = false, length = 60)
    private String password;
    @Column(length = 30)
    private String phone;
    private String img;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private ResetPasswordToken resetPasswordToken;
    @OneToMany(mappedBy = "manager", fetch = FetchType.EAGER)
    @JsonBackReference
    @ToString.Exclude
    private Set<Pharmacy> managing;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    @ToString.Exclude
    private PharmacyUser pharmacy;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Set<UserNotification> notifications;
}


