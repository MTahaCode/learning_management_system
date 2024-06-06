package com.backend.lms_backend.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class announcement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name= "teacher_assignment_id")
	private teacherAssignment teacherAssignmentEntity;
	
	private String announcementData;
	private Date announcementDate;
	
	
	public announcement(int id, teacherAssignment teacherAssignmentEntity, String announcementData,
			Date announcementDate) {
		super();
		this.id = id;
		this.teacherAssignmentEntity = teacherAssignmentEntity;
		this.announcementData = announcementData;
		this.announcementDate = announcementDate;
	}
	
	public announcement() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public teacherAssignment getTeacherAssignmentEntity() {
		return teacherAssignmentEntity;
	}
	public void setTeacherAssignmentEntity(teacherAssignment teacherAssignmentEntity) {
		this.teacherAssignmentEntity = teacherAssignmentEntity;
	}
	public String getAnnouncementData() {
		return announcementData;
	}
	public void setAnnouncementData(String announcementData) {
		this.announcementData = announcementData;
	}
	public Date getAnnouncementDate() {
		return announcementDate;
	}
	public void setAnnouncementDate(Date announcementDate) {
		this.announcementDate = announcementDate;
	}
	
	
	
}
