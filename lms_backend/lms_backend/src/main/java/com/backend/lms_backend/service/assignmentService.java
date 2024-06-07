package com.backend.lms_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.lms_backend.dao.assignmentDao;
import com.backend.lms_backend.entity.assignment;
import com.backend.lms_backend.entity.teacher;
import com.backend.lms_backend.entity.teacherAssignment;


@Service
public class assignmentService {
	@Autowired
	private assignmentDao assignmentDAO;
	//get all
		public List<assignment> findAll() {
			return assignmentDAO.findAll();
		}
		
		//add assignment
		public assignment save(assignment assign) {
		
			assignmentDAO.save(assign);
			return assign;
		}
		
		//find one assignment
		public assignment findOne(int id) {
			return assignmentDAO.findById(id).get();
		}
		
		//delete one assignment
		public void deleteById(int id) {
			assignment qu = assignmentDAO.findById(id).get();
			assignmentDAO.delete(qu);
		}
		
		//update a assignment
		public assignment updateassignment(assignment assign) {
			assignmentDAO.save(assign);
			return assign;
		}
		public List<assignment> getTeacherAssignmentsByTeacher( teacherAssignment teacherAssignmentEntity ) {
			return assignmentDAO.findByTeacherAssignmentEntity(teacherAssignmentEntity);
		}
}
