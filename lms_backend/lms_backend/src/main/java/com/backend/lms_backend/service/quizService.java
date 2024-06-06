package com.backend.lms_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.backend.lms_backend.dao.quizDao;
import com.backend.lms_backend.entity.quiz;
import com.backend.lms_backend.entity.student;

@Service
public class quizService {
	@Autowired
	private quizDao quizDAO;
	//get all
		public List<quiz> findAll() {
			return quizDAO.findAll();
		}
		
		//add quiz
		public quiz save(quiz qui) {
		
			quizDAO.save(qui);
			return qui;
		}
		
		//find one quiz
		public quiz findOne(int id) {
			return quizDAO.findById(id).get();
		}
		
		//delete one quiz
		public void deleteById(int id) {
			quiz qu = quizDAO.findById(id).get();
			quizDAO.delete(qu);
		}
		
		//update a quiz
		public quiz updatequiz(quiz qui) {
			quizDAO.save(qui);
			return qui;
		}
}	
