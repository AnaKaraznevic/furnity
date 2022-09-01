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

    public void deleteUserById(Long id){
        userRepository.deleteUserById(id);
    }

    public User updateUser(User userInfo){
        User user = userRepository.findUserById(userInfo.getId())
                .orElseThrow(() -> new UserNotFoundException("User by id " + userInfo.getId() +
                        " was not found in DataBase" ));

        user.setFirstName(userInfo.getFirstName());
        user.setLastName(userInfo.getLastName());
        user.setMiddleName(userInfo.getMiddleName());
        user.setEmail(userInfo.getEmail());
        user.setPassword(userInfo.getPassword());
        user.setPhoneNumber(userInfo.getPhoneNumber());
        user.setAddressId(userInfo.getAddressId());
        user.setCreatedAt(userInfo.getCreatedAt());
        user.setUpdatedAt(userInfo.getUpdatedAt());

        User updatedUser = userRepository.save(user);

        return userRepository.save(updatedUser);
    }
}
