package com.backend.lms_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private teacherAssignment teacher_course;

	public Double getWeightage() {
		return weightage;
	}

	public void setWeightage(Double weightage) {
		this.weightage = weightage;
	}

	

	public assignment(int id, String title, String description, Double totalMarks, Double weightage,
			teacherAssignment teacher_course) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.totalMarks = totalMarks;
		this.weightage = weightage;
		this.teacher_course = teacher_course;
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

	public teacherAssignment getTeacher_course() {
		return teacher_course;
	}

	public void setTeacher_course(teacherAssignment teacher_course) {
		this.teacher_course = teacher_course;
	}

	public Double getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(Double totalMarks) {
		this.totalMarks = totalMarks;
	}
	
}
