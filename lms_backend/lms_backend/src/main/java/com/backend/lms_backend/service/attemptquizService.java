package com.backend.lms_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.lms_backend.dao.attemptquizDao;
import com.backend.lms_backend.entity.attemptquiz;

@Service
public class attemptquizService {
	@Autowired
	private attemptquizDao attemptquizDAO;
	//get all
		public List<attemptquiz> findAll() {
			return attemptquizDAO.findAll();
		}
		
		//add attemptquiz
		public attemptquiz save(attemptquiz attemptqui) {
		
			attemptquizDAO.save(attemptqui);
			return attemptqui;
		}
		
		//find one attemptquiz
		public attemptquiz findOne(int id) {
			return attemptquizDAO.findById(id).get();
		}
		
		//delete one attemptquiz
		public void deleteById(int id) {
			attemptquiz qu = attemptquizDAO.findById(id).get();
			attemptquizDAO.delete(qu);
		}
		
		//update a attemptquiz
		public attemptquiz updateattemptquiz(attemptquiz attemptqui) {
			attemptquizDAO.save(attemptqui);
			return attemptqui;
		}
}
