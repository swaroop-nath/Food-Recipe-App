package com.cg.bench.food_recipe.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "dish_master")
@SequenceGenerator(name = "dish_seq", sequenceName = "dish_master_sequence", allocationSize = 1)
@NamedQuery(name = "fetch_all_recipes", query = "from Dish")
public class Dish {

	@Id
	@GeneratedValue(generator = "dish_seq")
	@Column(name = "dish_id")
	private int dishId;
	
	@Column(length = 100)
	private String dishName;
	
	@Column(length = 1024)
	private String description;
	
	@Column(length = 4096)
	private String cookingProcedure;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
	private Category category;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "dish_ingredient_master",
			joinColumns = @JoinColumn(referencedColumnName = "dish_id"),
			inverseJoinColumns = @JoinColumn(referencedColumnName = "ingredient_id"))
	private List<Ingredient> ingredients;
	
	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	private List<Image> images;
	
	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	private List<Review> reviews;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "author_id", referencedColumnName = "user_id")
	private User author;

	public int getDishId() {
		return dishId;
	}

	public void setDishId(int dishId) {
		this.dishId = dishId;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public String getCookingProcedure() {
		return cookingProcedure;
	}

	public void setCookingProcedure(String cookingProcedure) {
		this.cookingProcedure = cookingProcedure;
	}

	@JsonIgnoreProperties("hibernateLazyInitializer")
	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
	
}
