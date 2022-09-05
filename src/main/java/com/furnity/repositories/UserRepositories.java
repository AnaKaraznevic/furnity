package com.furnity.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.furnity.entities.User;

@Repository
public interface UserRepositories extends CrudRepository<User, Integer> {

}
