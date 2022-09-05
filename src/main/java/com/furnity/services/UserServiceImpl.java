package com.furnity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furnity.repositories.UserRepositories;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepositories userrepositories;
}
