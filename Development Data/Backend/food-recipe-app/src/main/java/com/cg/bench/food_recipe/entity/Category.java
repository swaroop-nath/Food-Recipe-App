package com.cg.bench.food_recipe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "category_master")
@SequenceGenerator(name = "cat_seq", sequenceName = "category_master_sequence", allocationSize = 1)
public class Category {
	
	@Id
	@GeneratedValue(generator = "cat_seq")
	@Column(name = "category_id")
	private int categoryId;
	
	@Column(length = 15)
	private String categoryName;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
