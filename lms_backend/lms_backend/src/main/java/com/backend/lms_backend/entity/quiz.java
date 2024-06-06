package com.backend.lms_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	private String description;
	
	private String question1;
	private String question2;
	private String question3;
	private String question4;
	
	
	private Double weightage;
	
	@ManyToOne
	private teacherAssignment teacher_course;
	
	public quiz() {
		super();
		// TODO Auto-generated constructor stub
	}

	public quiz(int id, String title, String description, String question1, String question2, String question3,
			String question4, Double weightage, teacherAssignment teacher_course) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.question1 = question1;
		this.question2 = question2;
		this.question3 = question3;
		this.question4 = question4;
		this.weightage = weightage;
		this.teacher_course = teacher_course;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion1() {
		return question1;
	}

	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	public String getQuestion2() {
		return question2;
	}

	public void setQuestion2(String question2) {
		this.question2 = question2;
	}

	public String getQuestion3() {
		return question3;
	}

	public void setQuestion3(String question3) {
		this.question3 = question3;
	}

	public String getQuestion4() {
		return question4;
	}

	public void setQuestion4(String question4) {
		this.question4 = question4;
	}

	public Double getWeightage() {
		return weightage;
	}

	public void setWeightage(Double weightage) {
		this.weightage = weightage;
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

	
	
	
}
