package com.cg.bench.food_recipe.auth_service.service;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.bench.food_recipe.entity.Login;
import com.cg.bench.food_recipe.entity.User;
import com.cg.bench.food_recipe.auth_service.exception.InvalidCredentialsException;
import com.cg.bench.food_recipe.auth_service.exception.SessionTimedOutException;
import com.cg.bench.food_recipe.auth_service.exception.UserCollisionException;
import com.cg.bench.food_recipe.auth_service.repository.AuthRepository;
import com.cg.bench.food_recipe.auth_service.security.JWTokenProvider;

@Service
@Transactional(rollbackFor = {DataIntegrityViolationException.class, SQLIntegrityConstraintViolationException.class, UserCollisionException.class})
public class AuthServiceImpl implements AuthService {
	
	@Autowired private AuthRepository repository;
	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private JWTokenProvider jwTokenProvider;
	@Autowired private PasswordEncoder passwordEncoder;

	@Override
	public String authenticateUser(Login credentials) throws InvalidCredentialsException {
		try {
			// Takes care of encoding the password
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmailId(), credentials.getPassword()));
			return jwTokenProvider.createToken(credentials.getEmailId(), repository.findUserByEmailID(credentials.getEmailId()).getRoles());
		} catch (AuthenticationException e) {
			throw new InvalidCredentialsException("Invalid Login Credentials.", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public String createNewAccount(User newUser) throws UserCollisionException {
		User existentUser = repository.findUserByEmailID(newUser.getEmailId());
		
		if (existentUser != null)
			throw new UserCollisionException("User with the Email-ID: " + newUser.getEmailId() + " already exists.", HttpStatus.INTERNAL_SERVER_ERROR);
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		repository.save(newUser);
		
		return jwTokenProvider.createToken(newUser.getEmailId(), repository.findUserByEmailID(newUser.getEmailId()).getRoles());
	}

	@Override
	public User updateProfile(User modifiedUser) {
		User mergedUser = AuthRepository.ENTITY_MANAGER.merge(modifiedUser);
		AuthRepository.ENTITY_MANAGER.flush();
		return mergedUser;
	}

	@Override
	public boolean isValidUser(String authenticationToken) {
		if (authenticationToken == null) {
			return false;  // Invalid token
		}
		
		return jwTokenProvider.getAuthentication(authenticationToken) != null;
	}

	@Override
	public User loadUserFromToken(String authorizationToken) throws SessionTimedOutException {
		if (!isValidUser(authorizationToken))
			throw new SessionTimedOutException("Session Timed Out. Please Login Again", HttpStatus.BAD_REQUEST);
		String userEmailId = jwTokenProvider.getUsername(authorizationToken);
		User loggedInUser = repository.findUserByEmailID(userEmailId);
		loggedInUser.setPassword("CLASSIFIED");
		return loggedInUser;
	}

}
