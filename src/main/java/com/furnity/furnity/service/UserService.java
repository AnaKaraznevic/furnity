package com.furnity.furnity.service;

import com.furnity.furnity.exception.UserNotFoundException;
import com.furnity.furnity.model.User;
import com.furnity.furnity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    // Constructor
    @Autowired
    public UserService( UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    public User addNewUser( User newUser ) {
        return userRepository.save(newUser);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById( Long id ) {
        userRepository.deleteUserById(id);
    }

    public User findUsersById(Long id){
        return userRepository.findUsersById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found in Data Base" ));
    }
}
