package com.furnity.furnity.service;

import com.furnity.furnity.model.Role;
import com.furnity.furnity.model.User;
import com.furnity.furnity.repository.UserRepository;
import com.furnity.furnity.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl( UserRepository userRepository ) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User saveNewUser( UserRegistrationDto userRegistrationDto ) {
        User newUser = new User(userRegistrationDto.getFirstName(), userRegistrationDto.getFirstName(),
                userRegistrationDto.getEmail(), userRegistrationDto.getPassword(),
                Arrays.asList(new Role("USER_ROLE")));

        return userRepository.save(newUser);
    }
}
