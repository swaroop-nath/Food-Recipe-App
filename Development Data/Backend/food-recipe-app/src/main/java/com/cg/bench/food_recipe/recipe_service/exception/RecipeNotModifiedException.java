package com.cg.bench.food_recipe.recipe_service.exception;

import org.springframework.http.HttpStatus;

public class RecipeNotModifiedException extends Exception {
	
	private HttpStatus status;

	public RecipeNotModifiedException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return this.status;
	}
}
