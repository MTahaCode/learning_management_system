package com.backend.lms_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.lms_backend.dao.teacherAssignmentDao;
import com.backend.lms_backend.entity.teacherAssignment;
import com.backend.lms_backend.entity.teacher;

@Service
public class teacherAssignmentService {

	@Autowired
	private teacherAssignmentDao teacherAssignmentDAO;
	
	//get all
	public List<teacherAssignment> findAll() {
		return teacherAssignmentDAO.findAll();
	}
	
	//add teacherAssignment
	public teacherAssignment save(teacherAssignment ta) {
		teacherAssignmentDAO.save(ta);
		return ta;
	}
	
	//find one teacherAssignment
	public teacherAssignment findOne(int id) {
		return teacherAssignmentDAO.findById(id).get();
	}
	
	public void deleteById(int id) {
		teacherAssignment ta = teacherAssignmentDAO.findById(id).get();
		teacherAssignmentDAO.delete(ta);
	}
	
	public teacherAssignment updateteacherAssignment(teacherAssignment updatedStu) {
		teacherAssignmentDAO.save(updatedStu);
		return updatedStu;
	}
	
	public List<teacherAssignment> getTeacherAssignmentsByTeacher( teacher teacherEntity ) {
		return teacherAssignmentDAO.findByTeacherEntity(teacherEntity);
	}
	
	public List<String> getSectionsOfCourse(int course_id) {
		return teacherAssignmentDAO.findDistinctSectionsByCourseId(course_id);
	}
}
