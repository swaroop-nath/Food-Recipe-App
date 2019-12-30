package com.cg.bench.food_recipe.recipe_service.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.cg.bench.food_recipe.entity.Dish;
import com.cg.bench.food_recipe.recipe_service.exception.NoRecipesFoundException;
import com.cg.bench.food_recipe.recipe_service.exception.RecipeNotModifiedException;

@Repository
public class RecipeRepositoryImpl implements RecipeRepository {

	@Autowired private EntityManager entityManager;
	
	@Override
	public List<Dish> fetchAllRecipes() throws NoRecipesFoundException {
		List<Dish> recipes = entityManager.createNamedQuery("fetch_all_recipes").getResultList();
		
		if (recipes.size() == 0)
			throw new NoRecipesFoundException("No Recipes Found in the database", HttpStatus.NOT_FOUND);
		return recipes;
	}

	// The following algorithm is working!!!
	@Override
	public Dish createNewRecipe(Dish newDish) {
		Dish mergedDish = entityManager.merge(newDish);
		entityManager.flush();
		return mergedDish;
	}

	@Override
	public boolean removeRecipe(Dish toBeRemovedDish) throws RecipeNotModifiedException {
		try {
			Dish mergedDish = entityManager.merge(toBeRemovedDish);
			entityManager.remove(mergedDish);
		} catch (Exception e) {
			throw new RecipeNotModifiedException("Recipe couldn't be deleted. Reason-\n" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return true;
	}

	// This works
	@Override
	public boolean addReviewOnDish(Dish reviewedDish) throws RecipeNotModifiedException {
		try {
			entityManager.merge(reviewedDish);
			entityManager.flush();
		} catch (Exception e) {
			throw new RecipeNotModifiedException("Recipe couldn't be deleted. Reason-\n" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return true;
	}

}
