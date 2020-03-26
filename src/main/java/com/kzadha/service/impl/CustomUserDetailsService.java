package com.kzadha.service.impl;


import com.kzadha.dao.UserRepository;
import com.kzadha.exception.UnauthorizedException;
import com.kzadha.model.User;
import com.kzadha.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username or email : " + email);
        }

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userDao.findById(id);
        if (user == null) {
            throw new UnauthorizedException(String.format("User with id = %s not found", id));
        }
        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUser(User user) {
        return UserPrincipal.create(user);
    }
}