package com.backend.lms_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.lms_backend.dao.feedbackDao;
import com.backend.lms_backend.entity.feedback;

@Service
public class feedbackService {
	
	@Autowired
	private feedbackDao feedbackDAO;
	
	//get all
	public List<feedback> findAll() {
		return feedbackDAO.findAll();
	}
	
	public feedback save(feedback fb) {
		feedbackDAO.save(fb);
		return fb;
	}
	
	public feedback findOne(int id) {
		return feedbackDAO.findById(id).get();
	}
	
	public void deleteById(int id) {
		feedback stu = feedbackDAO.findById(id).get();
		feedbackDAO.delete(stu);
	}
	
	public feedback updatefeedback(feedback updatedFb) {
		feedbackDAO.save(updatedFb);
		return updatedFb;
	}
}
