package com.backend.lms_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class submittedassignment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Double marksAttained;
	@OneToOne
	private enrollment enrolled;
	public submittedassignment(int id, Double marksAttained, enrollment enrolled) {
		super();
		this.id = id;
		this.marksAttained = marksAttained;
		this.enrolled = enrolled;
	}
	public submittedassignment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getMarksAttained() {
		return marksAttained;
	}
	public void setMarksAttained(Double marksAttained) {
		this.marksAttained = marksAttained;
	}
	public enrollment getEnrolled() {
		return enrolled;
	}
	public void setEnrolled(enrollment enrolled) {
		this.enrolled = enrolled;
	}

}
