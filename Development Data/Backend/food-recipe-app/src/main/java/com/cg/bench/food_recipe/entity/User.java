package com.cg.bench.food_recipe.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user_master")
@SequenceGenerator(name = "user_seq", sequenceName = "user_master_sequence", allocationSize = 1)
public class User {

	@Id
	@GeneratedValue(generator = "user_seq")
	@Column(name = "user_id")
	private int userId;
	
	@Column(length = 40, unique = true, nullable = false)
	private String emailId;
	
	@Column(length = 768)
	private String password;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<Role> roles;
	
	@Column(length = 50)
	private String name;
	
	@Column(name = "dob")
	private LocalDate dateOfBirth;
	
	private int gender;
	
	@Column(length = 25)
	private String city;
	
	@Column(length = 1024)
	private String about;
	
	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "author")
	@JsonIgnore
	@JsonIgnoreProperties("hibernateLazyInitializer")
	private List<Dish> dishes;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}
	
}
