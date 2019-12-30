package com.cg.bench.food_recipe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "image_link_master")
@SequenceGenerator(name = "img_seq", sequenceName = "image_link_sequence", allocationSize = 1)
public class Image {

	@Id
	@GeneratedValue(generator = "img_seq")
	@Column(name = "image_id")
	private int imageId;
	
	@Column(length = 256)
	private String imageURL;

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
}
