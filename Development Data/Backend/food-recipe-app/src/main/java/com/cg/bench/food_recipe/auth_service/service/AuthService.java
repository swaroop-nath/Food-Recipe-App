package com.cg.bench.food_recipe.auth_service.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.cg.bench.food_recipe.entity.Login;
import com.cg.bench.food_recipe.entity.User;
import com.cg.bench.food_recipe.auth_service.exception.InvalidCredentialsException;
import com.cg.bench.food_recipe.auth_service.exception.SessionTimedOutException;
import com.cg.bench.food_recipe.auth_service.exception.UserCollisionException;

public interface AuthService {

	String authenticateUser(Login credentials) throws InvalidCredentialsException;

	String createNewAccount(User newUser) throws UserCollisionException;

	User updateProfile(User modifiedUser);
	
	boolean isValidUser(String bearerToken);

	User loadUserFromToken(String authorizationToken) throws SessionTimedOutException;

}
