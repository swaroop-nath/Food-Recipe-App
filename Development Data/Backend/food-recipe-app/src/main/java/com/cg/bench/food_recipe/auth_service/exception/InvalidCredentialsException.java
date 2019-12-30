package com.cg.bench.food_recipe.auth_service.exception;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends Exception {
	
	private HttpStatus status;

	public InvalidCredentialsException() {
		super();
	}
	
	public InvalidCredentialsException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
	
	public HttpStatus getStatus() {
		return this.status;
	}
}
