package com.furnity.furnity.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.furnity.furnity.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
//public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String username);


}
