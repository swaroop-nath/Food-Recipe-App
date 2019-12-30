package com.cg.bench.food_recipe.recipe_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bench.food_recipe.entity.Dish;
import com.cg.bench.food_recipe.recipe_service.exception.NoRecipesFoundException;
import com.cg.bench.food_recipe.recipe_service.exception.RecipeNotModifiedException;
import com.cg.bench.food_recipe.recipe_service.service.RecipeService;

@RestController
@RequestMapping(value = "recipe-api")
@CrossOrigin("http://localhost:4200")
public class RecipeController {
	
	@Autowired private RecipeService service;

	/**
	 * The following end-point is used to fetch all the recipes that have been added to 
	 * the database by various users. 
	 * @return List of recipes found in the database
	 * @throws NoRecipesFoundException 
	 */
	@GetMapping(value = "/fetch-all", produces = "application/json")
	public List<Dish> fetchAllRecipes() throws NoRecipesFoundException {
		return service.fetchAllRecipes();
	}
	
	/**
	 * The following end-point is used to add a new recipe to the database.
	 * @param newDish The newly created recipe by the user.
	 * @return The recipe added to the database.
	 */
	@PostMapping(value = "/add-recipe", produces = "application/json", consumes = "application/json")
	public Dish createNewRecipe(@RequestBody Dish newDish) {
		return service.createNewRecipe(newDish);
	}
	
	/**
	 * The following end-point is used to remove a dish from the database.
	 * @param toBeRemovedDish The dish which the admin wishes to remove.
	 * @return A flag indicating whether the recipe has been successfully
	 * removed, true for successful removal, false otherwise.
	 * @throws RecipeNotModifiedException 
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(value = "/remove", consumes = "application/json")
	public boolean removeRecipe(@RequestBody Dish toBeRemovedDish) throws RecipeNotModifiedException {
		return service.removeRecipe(toBeRemovedDish);
	}
	
	/**
	 * The following end-point is used to add a review to the recipe.
	 * The review can be either in the form of like/dislike or comment or both
	 * @param reviewedDish The dish on which the review has been added, the 
	 * entity contains the added review in its association.
	 * @return A flag indicating whether the review has been successfully
	 * add for the recipe, true for successful addition, false otherwise.
	 * @throws RecipeNotModifiedException 
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping(value = "/review-dish", consumes = "application/json")
	public boolean addReviewOnDish(@RequestBody Dish reviewedDish) throws RecipeNotModifiedException {
		System.out.println(reviewedDish.getReviews().size());
		return service.addReviewOnDish(reviewedDish);
	}
}
