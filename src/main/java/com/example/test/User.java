package com.example.test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Size(min=3, max=25)
	private String firstName;
	
	@NotBlank
	@Size(min=3, max=25)
	private String lastName;
	private String dateNaisance;
	
	
	
	
	public User() {
		
	}
	
	public User(Long id, String firstName, String lastName, String dateNaisance) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateNaisance = dateNaisance;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getDateNaisance() {
		return dateNaisance;
	}
	public void setDateNaisance(String dateNaisance) {
		this.dateNaisance = dateNaisance;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateNaisance="
				+ dateNaisance + "]";
	}
	
	
	
}