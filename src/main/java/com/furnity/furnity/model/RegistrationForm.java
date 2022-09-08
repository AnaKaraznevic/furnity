package com.furnity.furnity.model;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;


@Data
public class RegistrationForm {

    private String firstName;
    private String secondName;
    private String email;
    private String password;

    public User toUser( PasswordEncoder passwordEncoder ){
        User user = new User();
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setRoles(Collections.singleton(Role.USER));
        return user;
    }

}
