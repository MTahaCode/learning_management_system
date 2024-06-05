package com.backend.lms_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class teacher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy = "teacherEntity", 
			cascade = CascadeType.ALL, 
			orphanRemoval = true)
	@JsonIgnore
	private List<teacherAssignment> teacherAssignments = new ArrayList<>();
	
	private String name;
	private String email;
	private String password;
	
	public teacher() {
		super();
	}

	public teacher(int id, List<teacherAssignment> teacherAssignments, String name, String email, String password) {
		super();
		this.id = id;
		this.teacherAssignments = teacherAssignments;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<teacherAssignment> getTeacherAssignments() {
		return teacherAssignments;
	}

	public void setTeacherAssignments(List<teacherAssignment> teacherAssignments) {
		this.teacherAssignments = teacherAssignments;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
