package com.furnity.furnity.service;

import com.furnity.furnity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furnity.furnity.repository.UserRepositories;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepositories userrepositories;
}
