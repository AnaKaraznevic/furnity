package com.furnity.furnity.service;

import org.springframework.stereotype.Service;

import com.furnity.furnity.model.User;


public interface UserService {
	User save(User user);

    User findByEmail(String email);
}
