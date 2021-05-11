package com.chinjja.taco.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chinjja.taco.User;
import com.chinjja.taco.data.UserRepository;

@Service
public class UserRepositoryUserDetailService implements UserDetailsService {
	private final UserRepository userRepo;
	
	@Autowired
	public UserRepositoryUserDetailService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if(user == null)
			throw new UsernameNotFoundException("User '" + username + "' not found");
		return user;
	}

}
