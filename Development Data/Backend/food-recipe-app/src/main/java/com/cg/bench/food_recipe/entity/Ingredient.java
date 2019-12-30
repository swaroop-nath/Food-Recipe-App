package com.cg.bench.food_recipe.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ingredient_master")
@SequenceGenerator(name = "ing_seq", sequenceName = "ingredient_master_sequence", allocationSize = 1)
public class Ingredient {
	
	@Id
	@GeneratedValue(generator = "ing_seq")
	@Column(name = "ingredient_id")
	private int ingredientId;
	
	@ManyToMany(mappedBy = "ingredients")
	@JsonIgnore
	@JsonIgnoreProperties("hibernateLazyInitializer")
	private List<Dish> dishes;

	@Column(length = 15)
	private String ingredientName;

	public int getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}
	
}
