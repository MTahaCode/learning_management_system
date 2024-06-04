package com.backend.lms_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.lms_backend.dao.enrollmentDao;
import com.backend.lms_backend.entity.enrollment;
import com.backend.lms_backend.entity.student;

@Service
public class enrollmentService {
	
	@Autowired
	private enrollmentDao enrollmentDAO;
	
	//get all
	public List<enrollment> findAll() {
		return enrollmentDAO.findAll();
	}
	
	//add enrollment
	public enrollment save(enrollment stu) {
		enrollmentDAO.save(stu);
		return stu;
	}
	
	//find one enrollment
	public enrollment findOne(int id) {
		return enrollmentDAO.findById(id).get();
	}
	
	public void deleteById(int id) {
		enrollment stu = enrollmentDAO.findById(id).get();
		enrollmentDAO.delete(stu);
	}
	
	public enrollment updateenrollment(enrollment updatedStu) {
		enrollmentDAO.save(updatedStu);
		return updatedStu;
	}
	
	public List<enrollment> getEnrollmentsByStudent( student studentEntity ) {
		return enrollmentDAO.findByStudentEntity(studentEntity);
	}
	
}
