package com.backend.lms_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.lms_backend.entity.feedback;
import com.backend.lms_backend.service.feedbackService;

@RestController
public class feedbackController {

	@Autowired
	private feedbackService FeedbackService;
	
	@GetMapping("/feedback")
	public List<feedback> retrieverAllfeedback() {
		return FeedbackService.findAll();
	}
	
	@GetMapping("/feedback/{id}")
	public feedback retrievefeedback(@PathVariable int id) {
		return FeedbackService.findOne(id);
	}
	
	@PostMapping("/feedback")
	public feedback createfeedback(@RequestBody feedback fb) {
		feedback savedfb = FeedbackService.save(fb);
		return savedfb;
	}
	
	@DeleteMapping("/feedback/{id}")
	public void deletefeedback(@PathVariable int id) throws Exception {
		try {
			FeedbackService.deleteById(id);
		}
		catch (Exception e) {
			throw new Exception("Not found id: " + id);
		}
	}
	
	@PutMapping("/feedback/{id}")
	public feedback updatefeedback(@RequestBody feedback fb, @PathVariable int id) {
		feedback existingfb = FeedbackService.findOne(id);
		if (existingfb == null) {
			throw new RuntimeException("feedback not found with id: " + id);
		}
		fb.setId(id);
		return FeedbackService.updatefeedback(fb);
		
	}
}
