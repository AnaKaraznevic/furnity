package com.furnity.furnity.controller;

import com.furnity.furnity.model.User;
import com.furnity.furnity.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")  // ???
@RequestMapping("/api/v1/")
public class UserController {

    private final UserService userService;

    // Constructor
    public UserController( UserService userService ) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = userService.findAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        User userToFindById = userService.findUserById(id);
        return new ResponseEntity<>(userToFindById, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<User> addNewUser(@RequestBody User user){
        User newUser = userService.saveNewUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserById( @PathVariable("id") Long id){
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/user/")
    public ResponseEntity<User> updateUser(@RequestBody User userInfo){
        User updateUser = userService.updateUser(userInfo);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }
}
