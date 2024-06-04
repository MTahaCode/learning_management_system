package com.backend.lms_backend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
public class student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy = "studentEntity", 
			cascade = CascadeType.ALL, 
			orphanRemoval = true)
	@JsonIgnore
	private List<enrollment> enrollments = new ArrayList<>();
	
	private String name;
	private String email;
	private String password;
	
	public student() {
		super();
	}

	public student(int id, List<enrollment> enrollments, String name, String email, String password) {
		super();
		this.id = id;
		this.enrollments = enrollments;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<enrollment> enrollments) {
		this.enrollments = enrollments;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
