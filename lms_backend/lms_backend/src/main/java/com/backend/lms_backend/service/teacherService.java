package com.backend.lms_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.lms_backend.dao.teacherDao;
import com.backend.lms_backend.entity.teacher;

@Service
public class teacherService {
	
	@Autowired
	private teacherDao teacherDAO;
	
	//get all
	public List<teacher> findAll() {
		return teacherDAO.findAll();
	}
	
	//get by email and password
	public teacher findTeacherByEmailAndPassword(String email, String password) {
		return teacherDAO.findByEmailAndPassword(email, password).orElse(null);
	}
	
	//add teacher
	public teacher save(teacher stu) {
		teacherDAO.save(stu);
		return stu;
	}
	
	//find one teacher
	public teacher findOne(int id) {
		return teacherDAO.findById(id).get();
	}
	
	//delete one teacher
	public void deleteById(int id) {
		teacher stu = teacherDAO.findById(id).get();
		teacherDAO.delete(stu);
	}
	
	//update a teacher
	public teacher updateTeacher(teacher updatedStu) {
		teacherDAO.save(updatedStu);
		return updatedStu;
	}
}
