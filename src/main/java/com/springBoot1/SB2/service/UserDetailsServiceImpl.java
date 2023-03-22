package com.springBoot1.SB2.service;

import com.springBoot1.SB2.config.UserDetailsImpl;
import com.springBoot1.SB2.entity.User;
import com.springBoot1.SB2.repository.UserRepository;
import com.springBoot1.SB2.util.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private MessageSource messageSource;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(messageSource.getMessage("users.username.notfound", ArrayUtil.of(), LocaleContextHolder.getLocale()));
        }
        return new UserDetailsImpl(user);
    }
}
