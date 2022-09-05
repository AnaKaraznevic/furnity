package com.furnity.furnity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.furnity.furnity.model.User;

@Repository
public interface UserRepositories extends CrudRepository<User, Integer> {

}
