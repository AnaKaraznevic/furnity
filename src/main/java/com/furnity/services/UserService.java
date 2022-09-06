package com.furnity.services;

import com.furnity.entities.User;


public interface UserService {
	public User findByEmailPass(User user);
	public User saveUser(User user);
}
