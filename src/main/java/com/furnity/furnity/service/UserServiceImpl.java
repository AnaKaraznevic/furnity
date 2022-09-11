package com.furnity.furnity.service;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.furnity.furnity.model.Role;
import com.furnity.furnity.model.User;
import com.furnity.furnity.repository.RoleRepository;
import com.furnity.furnity.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
    private RoleRepository roleRepository;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
       // user.setRoles(new HashSet<>(roleRepository.findAll()));
		Optional<Role> roles=roleRepository.findById(Long.valueOf(1));
		HashSet<Role> userRole=new HashSet<>();
		if (roles.isPresent()) {
			userRole.add(roles.get());
		}
		user.setRoles(userRole);
		
		return userRepository.save(user);
		
	}
}
