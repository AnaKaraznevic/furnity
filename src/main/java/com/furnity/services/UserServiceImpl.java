package com.furnity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furnity.entities.User;
import com.furnity.repositories.UserRepositories;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepositories userrepositories;

	@Override
	public User findByEmailPass(User user) {
		// TODO Auto-generated method stub
		System.out.println(user.getEmail());
		System.out.println(user.getPass());
		User user1=userrepositories.findByEmailAndPass(user.getEmail(),user.getPass());
		return user1;
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return userrepositories.save(user);
	}
}
