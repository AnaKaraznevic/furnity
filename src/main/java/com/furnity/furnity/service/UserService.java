package com.furnity.furnity.service;

import com.furnity.furnity.model.User;
import com.furnity.furnity.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User saveNewUser( UserRegistrationDto registrationDto);
}
