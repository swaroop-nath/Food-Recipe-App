package com.cg.bench.food_recipe.recipe_service.service;

import java.util.List;

import com.cg.bench.food_recipe.entity.Dish;
import com.cg.bench.food_recipe.recipe_service.exception.NoRecipesFoundException;
import com.cg.bench.food_recipe.recipe_service.exception.RecipeNotModifiedException;

public interface RecipeService {

	List<Dish> fetchAllRecipes() throws NoRecipesFoundException;

	Dish createNewRecipe(Dish newDish);

	boolean removeRecipe(Dish toBeRemovedDish) throws RecipeNotModifiedException;

	boolean addReviewOnDish(Dish reviewedDish) throws RecipeNotModifiedException;

}
