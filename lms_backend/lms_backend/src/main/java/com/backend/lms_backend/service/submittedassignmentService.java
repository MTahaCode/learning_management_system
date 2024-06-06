package com.backend.lms_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.lms_backend.dao.submittedassignmentDao;
import com.backend.lms_backend.entity.submittedassignment;

@Service
public class submittedassignmentService {
	@Autowired
	private submittedassignmentDao submittedassignmentDAO;
	//get all
		public List<submittedassignment> findAll() {
			return submittedassignmentDAO.findAll();
		}
		
		//add submittedassignment
		public submittedassignment save(submittedassignment assign) {
		
			submittedassignmentDAO.save(assign);
			return assign;
		}
		
		//find one submittedassignment
		public submittedassignment findOne(int id) {
			return submittedassignmentDAO.findById(id).get();
		}
		
		//delete one submittedassignment
		public void deleteById(int id) {
			submittedassignment qu = submittedassignmentDAO.findById(id).get();
			submittedassignmentDAO.delete(qu);
		}
		
		//update a submittedassignment
		public submittedassignment updatesubmittedassignment(submittedassignment assign) {
			submittedassignmentDAO.save(assign);
			return assign;
		}
}
