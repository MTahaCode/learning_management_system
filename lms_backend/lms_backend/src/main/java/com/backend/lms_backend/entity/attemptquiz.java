package com.backend.lms_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class attemptquiz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	
	private Double marksAttained;
	@OneToOne
	private enrollment enrolled;

	

	public attemptquiz(int id, String answer1, String answer2, String answer3, String answer4, Double marksAttained,
			enrollment enrolled) {
		super();
		this.id = id;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.marksAttained = marksAttained;
		this.enrolled = enrolled;
	}

	public attemptquiz() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getAnswer4() {
		return answer4;
	}

	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}

	public enrollment getEnrolled() {
		return enrolled;
	}

	public void setEnrolled(enrollment enrolled) {
		this.enrolled = enrolled;
	}
	
}
