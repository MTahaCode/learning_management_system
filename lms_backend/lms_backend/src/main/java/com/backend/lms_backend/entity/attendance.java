package com.backend.lms_backend.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class attendance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "enrollment_id")
	@JsonBackReference
	private enrollment enrollmentEntity;
	
	private LocalDateTime dateTime;
	private String type;
	
	public attendance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public attendance(Integer id, enrollment enrollmentEntity, LocalDateTime dateTime, String type) {
		super();
		this.id = id;
		this.enrollmentEntity = enrollmentEntity;
		this.dateTime = dateTime;
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public enrollment getEnrollmentEntity() {
		return enrollmentEntity;
	}
	public void setEnrollmentEntity(enrollment enrollmentEntity) {
		this.enrollmentEntity = enrollmentEntity;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
