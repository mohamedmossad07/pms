package com.springBoot1.SB2.config;

import com.springBoot1.SB2.entity.User;
import com.springBoot1.SB2.repository.RoleRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
    private String username;
    private String password;
    private int priority;
    private List<GrantedAuthority> authorities = new ArrayList<>();

    private User user;
    @Autowired
    private RoleRepository roleRepository;

    public UserDetailsImpl(User user) {
        this.user = user;
        username = user.getUsername();
        password = user.getPassword();
        priority = user.getRole().getPriority();
        authorities.add(new SimpleGrantedAuthority("Role_" + user.getRole().getName()));
        user.getRole().getAuthorities().forEach(authority -> {
            authorities.add(new SimpleGrantedAuthority(authority.getName()));
        });
    }

    public UserDetailsImpl(String username, String password, List<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
