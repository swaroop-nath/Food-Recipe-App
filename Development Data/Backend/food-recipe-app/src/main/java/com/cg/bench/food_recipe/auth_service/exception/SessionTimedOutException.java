package com.cg.bench.food_recipe.auth_service.exception;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;

public class SessionTimedOutException extends ServletException {

	private HttpStatus status;
	
	public SessionTimedOutException() {
		super();
	}
	
	public SessionTimedOutException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
	
	public HttpStatus getStatus() {
		return this.status;
	}

}
