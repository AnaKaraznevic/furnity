package com.furnity.furnity.service;

import com.furnity.furnity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl( UserRepository userRepository ) {
        super();
        this.userRepository = userRepository;
    }

    /*
    @Override
    public User saveNewUser() {

        return userRepository.save(newUser);
    }
     */
}
