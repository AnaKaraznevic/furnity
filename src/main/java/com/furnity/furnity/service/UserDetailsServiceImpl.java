package com.furnity.furnity.service;

import com.furnity.furnity.model.User;
import com.furnity.furnity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername( String email ) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        return user;
    }
}
