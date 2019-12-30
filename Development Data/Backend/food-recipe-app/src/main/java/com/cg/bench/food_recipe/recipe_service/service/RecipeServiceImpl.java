package com.cg.bench.food_recipe.recipe_service.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bench.food_recipe.entity.Dish;
import com.cg.bench.food_recipe.recipe_service.exception.NoRecipesFoundException;
import com.cg.bench.food_recipe.recipe_service.exception.RecipeNotModifiedException;
import com.cg.bench.food_recipe.recipe_service.repository.RecipeRepository;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {
	
	@Autowired private RecipeRepository repository;

	@Override
	public List<Dish> fetchAllRecipes() throws NoRecipesFoundException {
		return repository.fetchAllRecipes();
	}

	@Override
	public Dish createNewRecipe(Dish newDish) {
		return repository.createNewRecipe(newDish);
	}

	@Override
	public boolean removeRecipe(Dish toBeRemovedDish) throws RecipeNotModifiedException {
		return repository.removeRecipe(toBeRemovedDish);
	}

	@Override
	public boolean addReviewOnDish(Dish reviewedDish) throws RecipeNotModifiedException {
		return repository.addReviewOnDish(reviewedDish);
	}

}
