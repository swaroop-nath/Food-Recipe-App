package com.cg.bench.food_recipe.auth_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.bench.food_recipe.entity.User;
import com.cg.bench.food_recipe.auth_service.repository.AuthRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired private AuthRepository repository;

	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		User user = repository.findUserByEmailID(emailId);
		
		if (user == null)
			throw new UsernameNotFoundException("User for given email: " + emailId + " not found.");
		
		return org.springframework.security.core.userdetails.User
				.withUsername(emailId)
				.password(user.getPassword())
				.authorities(user.getRoles())
				.accountExpired(false)
		        .accountLocked(false)
		        .credentialsExpired(false)
		        .disabled(false)
		        .build();
	}

}
