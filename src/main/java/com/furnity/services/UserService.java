package com.furnity.services;

import org.springframework.web.multipart.MultipartFile;

import com.furnity.entities.User;


public interface UserService {
	public User findByEmailPass(User user);
//	public User saveUser(User user); 
	public User saveUser(User user,MultipartFile multipartFile);
}
