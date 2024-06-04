package com.backend.lms_backend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

@Entity
public class course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy = "courseEntity",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	@JsonIgnore
	private List<enrollment> enrollments = new ArrayList<>();
	
	private String name;
	private Integer credits;
	
	public course() {
		super();
	}

	public course(int id, List<enrollment> enrollments, String name, Integer credits) {
		super();
		this.id = id;
		this.enrollments = enrollments;
		this.name = name;
		this.credits = credits;
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

	public Integer getCredits() {
		return credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}
}
