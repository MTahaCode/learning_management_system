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
public class enrollment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name= "student_id")
	private student studentEntity;
	
	@ManyToOne
	@JoinColumn(name= "course_id")
	private course courseEntity;
	
	@OneToMany(mappedBy="enrollmentEntity", cascade= CascadeType.ALL)
	@JsonManagedReference
	private List<attendance> attendanceList;
	
	private String section;
	private String semester;
	private String grade;
	
	public enrollment() {
		super();
	}

	public enrollment(int id, student studentEntity, course courseEntity, List<attendance> attendanceList,
			String section, String semester, String grade) {
		super();
		this.id = id;
		this.studentEntity = studentEntity;
		this.courseEntity = courseEntity;
		this.attendanceList = attendanceList;
		this.section = section;
		this.semester = semester;
		this.grade = grade;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public student getStudentEntity() {
		return studentEntity;
	}

	public void setStudentEntity(student studentEntity) {
		this.studentEntity = studentEntity;
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public List<attendance> getAttendanceList() {
		return attendanceList;
	}

	public void setAttendanceList(List<attendance> attendanceList) {
		this.attendanceList = attendanceList;
	}
	
}
