package com.cg.bench.food_recipe.auth_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bench.food_recipe.entity.Login;
import com.cg.bench.food_recipe.entity.User;
import com.cg.bench.food_recipe.auth_service.exception.InvalidCredentialsException;
import com.cg.bench.food_recipe.auth_service.exception.SessionTimedOutException;
import com.cg.bench.food_recipe.auth_service.exception.UserCollisionException;
import com.cg.bench.food_recipe.auth_service.service.AuthService;
import com.cg.bench.food_recipe.auth_service.service.CustomUserDetailsService;

@RestController
@RequestMapping(value = "auth-api")
@CrossOrigin("http://localhost:4200")
public class AuthController {
	
	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private AuthService service;
	
	/**
	 * The following end-point is used to authenticate the login credentials 
	 * of the user.
	 * @param credentials The login credentials of the user attempting to sign in.
	 * @return The assigned token to the logged in user, on successful
	 * login.
	 * @throws InvalidCredentialsException
	 */
	@PostMapping(value = "/authenticate", consumes = "application/json", produces = "application/json")
	public String authenticateUser(@RequestBody Login credentials) throws InvalidCredentialsException {
		return service.authenticateUser(credentials);
	}
	
	/**
	 * The following end-point is used to create an account
	 * for a new user.
	 * @param newUser The details of the new account.
	 * @return The assigned token to the signed up user, on successful
	 * sign up.
	 * @throws UserCollisionException 
	 */
	@PostMapping(value = "/sign-up", consumes = "application/json")
	public String createNewAccount(@RequestBody User newUser) throws UserCollisionException {
		return service.createNewAccount(newUser);
	}
	
	/**
	 * The following end-point is used to update profile of the user.
	 * @param modifiedUser The updated details of the user.
	 * @return The modified User details.
	 * @throws SessionTimedOutException 
	 */
	@PostMapping(value = "/update-profile", consumes = "application/json", produces = "application/json")
	public User updateProfile(@RequestBody User modifiedUser) {
		return service.updateProfile(modifiedUser);
	}
	
	/**
	 * The following end-point is used to fetch the user details of the user
	 * represented by the JWT. It is useful in fetching user details after a user
	 * has logged in.
	 * @param authorizationToken The token generated for the user after
	 * successful sign-in.
	 * @return The user details for the logged in user.
	 * @throws SessionTimedOutException
	 */
	@GetMapping(value = "/get-user", produces = "application/json")
	public User getUserForToken(@RequestHeader("Authorization") String authorizationToken) throws SessionTimedOutException {
		return service.loadUserFromToken(authorizationToken);
	}

}
