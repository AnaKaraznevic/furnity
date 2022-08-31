package com.furnity.furnity.service;

import com.furnity.furnity.exception.UserNotFoundException;
import com.furnity.furnity.model.User;
import com.furnity.furnity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    public User saveNewUser( User newUser ) {
        return userRepository.save(newUser);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id){
        return userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found in Data Base" ));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserById( @PathVariable("id") Long id){
        userRepository.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
