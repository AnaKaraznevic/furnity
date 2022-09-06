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

    public List<User> findUserByFullNameAndAddressTown (String fullName, String town) {
        return userRepository.findByFullNameAndAddressTown(fullName,town);
    }

    public void deleteUserById(Long id){
        userRepository.deleteUserById(id);
    }

    public User updateUser(User userInfo){
        User user = userRepository.findUserById(userInfo.getId())
                .orElseThrow(() -> new UserNotFoundException("User by id " + userInfo.getId() +
                        " was not found in DataBase" ));

        user.setFullName(userInfo.getFullName());
        user.setPhoneNumber(userInfo.getPhoneNumber());
        user.setEmail(userInfo.getEmail());
        user.setAddress(userInfo.getAddress());
        user.setPassword(userInfo.getPassword());
        //user.setCreatedAt(userInfo.getCreatedAt());
        //user.setUpdatedAt(userInfo.getUpdatedAt());

        User updatedUser = userRepository.save(user);

        return userRepository.save(updatedUser);
    }
}
