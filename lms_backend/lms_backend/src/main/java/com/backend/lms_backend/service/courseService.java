package com.backend.lms_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.lms_backend.dao.courseDao;
import com.backend.lms_backend.entity.course;
import com.backend.lms_backend.entity.course;

@Service
public class courseService {
	
	@Autowired
	private courseDao courseDAO;
	
	//get all
	public List<course> findAll() {
		return courseDAO.findAll();
	}
	
	//add course
	public course save(course stu) {
		courseDAO.save(stu);
		return stu;
	}
	
	//find one course
	public course findOne(int id) {
		return courseDAO.findById(id).get();
	}
	
	public void deleteById(int id) {
		course stu = courseDAO.findById(id).get();
		courseDAO.delete(stu);
	}
	
	public course updatecourse(course updatedStu) {
		courseDAO.save(updatedStu);
		return updatedStu;
	}
}





