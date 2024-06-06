package com.backend.lms_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.lms_backend.dao.announcementDao;
import com.backend.lms_backend.entity.announcement;
import com.backend.lms_backend.entity.teacherAssignment;

@Service
public class announcementService {
	
	@Autowired
	private announcementDao announcementDAO;
	
	//get all
	public List<announcement> findAll() {
		return announcementDAO.findAll();
	}
	
	public announcement save(announcement an) {
		announcementDAO.save(an);
		return an;
	}
	
	public announcement findOne(int id) {
		return announcementDAO.findById(id).get();
	}
	
	public void deleteById(int id) {
		announcement an = announcementDAO.findById(id).get();
		announcementDAO.delete(an);
	}
	
	public announcement updateannouncement(announcement updatedAn) {
		announcementDAO.save(updatedAn);
		return updatedAn;
	}
	
	public List<announcement> getAnnouncementsByTeacherAssignment(teacherAssignment teacherAssignmentEntity) {
	    return announcementDAO.findByTeacherAssignmentEntity(teacherAssignmentEntity);
	}
}
