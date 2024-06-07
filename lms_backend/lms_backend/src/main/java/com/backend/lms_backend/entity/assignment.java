package com.backend.lms_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class assignment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	private String description;
	
	private Double totalMarks;
	private Double weightage;
	@ManyToOne
	@JoinColumn(name= "teacher_assignment_id")
	private teacherAssignment teacherAssignmentEntity;

	public Double getWeightage() {
		return weightage;
	}

	public void setWeightage(Double weightage) {
		this.weightage = weightage;
	}

	

	public assignment(int id, String title, String description, Double totalMarks, Double weightage,
			teacherAssignment teacherAssignmentEntity) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.totalMarks = totalMarks;
		this.weightage = weightage;
		this.teacherAssignmentEntity = teacherAssignmentEntity;
	}

	public assignment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public teacherAssignment getTeacherAssignmentEntity() {
		return teacherAssignmentEntity;
	}
	public void setTeacherAssignmentEntity(teacherAssignment teacherAssignmentEntity) {
		this.teacherAssignmentEntity = teacherAssignmentEntity;
	}

	public Double getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(Double totalMarks) {
		this.totalMarks = totalMarks;
	}
	
}
