package com.chinjja.taco.data;

import org.springframework.data.repository.CrudRepository;

import com.chinjja.taco.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
