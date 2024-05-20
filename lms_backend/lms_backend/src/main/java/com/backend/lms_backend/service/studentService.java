package com.backend.lms_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.lms_backend.dao.studentDao;
import com.backend.lms_backend.entity.student;

@Service
public class studentService {
	
	@Autowired
	private studentDao studentDAO;
	
	//get all
	public List<student> findAll() {
		return studentDAO.findAll();
	}
	
	//add student
	public student save(student stu) {
		studentDAO.save(stu);
		return stu;
	}
	
	//find one student
	public student findOne(int id) {
		return studentDAO.findById(id).get();
	}
	
	public void deleteById(int id) {
		student stu = studentDAO.findById(id).get();
		studentDAO.delete(stu);
	}
	
	public student updateStudent(student updatedStu) {
		studentDAO.save(updatedStu);
		return updatedStu;
	}
}





