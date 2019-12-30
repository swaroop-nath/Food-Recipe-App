package com.cg.bench.food_recipe.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "review_master")
@SequenceGenerator(name = "review_seq", sequenceName = "review_master_sequence", allocationSize = 1)
public class Review {

	@Id
	@GeneratedValue(generator = "review_seq")
	@Column(name = "review_id")
	private int reviewId;
	
	// Flag represents like or dislike or no sentiment.
	// 1 - Like, 0 - Dislike, 2 - Nothing
	private int flag;
	
	@Column(length = 1024)
	private String comment;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "reviewer_id", referencedColumnName = "user_id")
	private User reviewer;

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getReviewer() {
		return reviewer;
	}

	public void setReviewer(User reviewer) {
		this.reviewer = reviewer;
	}
	
}
