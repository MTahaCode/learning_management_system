package com.backend.lms_backend.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class teacherAssignment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name= "teacher_id")
	private teacher teacherEntity;
	
	@ManyToOne
	@JoinColumn(name= "course_id")
	private course courseEntity;
	
	@OneToMany(mappedBy="teacher_course", cascade= CascadeType.ALL)
	@JsonManagedReference
	private List<quiz> quizList;
	
	private String section;
	private String semester;
	
	public teacherAssignment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public teacherAssignment(int id, teacher teacherEntity, course courseEntity, String section, String semester) {
		super();
		this.id = id;
		this.teacherEntity = teacherEntity;
		this.courseEntity = courseEntity;
		this.section = section;
		this.semester = semester;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public teacher getTeacherEntity() {
		return teacherEntity;
	}

	public void setTeacherEntity(teacher teacherEntity) {
		this.teacherEntity = teacherEntity;
	}

	public course getCourseEntity() {
		return courseEntity;
	}

	public void setCourseEntity(course courseEntity) {
		this.courseEntity = courseEntity;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public List<quiz> getQuizList() {
		return quizList;
	}

	public void setQuizList(List<quiz> quizList) {
		this.quizList = quizList;
	}
}
