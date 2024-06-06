package com.backend.lms_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name= "enrollment_id")
	private enrollment enrollmentEntity;
	
	private String feedbackData;

	public feedback(int id, enrollment enrollmentEntity, String feedbackData) {
		super();
		this.id = id;
		this.enrollmentEntity = enrollmentEntity;
		this.feedbackData = feedbackData;
	}

	public feedback() {
		super();
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public enrollment getEnrollmentEntity() {
		return enrollmentEntity;
	}

	public void setEnrollmentEntity(enrollment enrollmentEntity) {
		this.enrollmentEntity = enrollmentEntity;
	}

	public String getFeedbackData() {
		return feedbackData;
	}

	public void setFeedbackData(String feedbackData) {
		this.feedbackData = feedbackData;
	}

	
}
