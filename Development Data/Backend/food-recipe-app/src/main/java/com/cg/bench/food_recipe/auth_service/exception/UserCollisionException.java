package com.cg.bench.food_recipe.auth_service.exception;

import org.springframework.http.HttpStatus;

public class UserCollisionException extends Exception {

	private HttpStatus status;

	public UserCollisionException() {
		super();
	}

	public UserCollisionException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
	
	public HttpStatus getStatus() {
		return this.status;
	}
	
}
