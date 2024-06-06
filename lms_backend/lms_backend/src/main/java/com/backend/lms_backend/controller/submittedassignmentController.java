package com.backend.lms_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.lms_backend.entity.submittedassignment;
import com.backend.lms_backend.service.submittedassignmentService;

@RestController
public class submittedassignmentController {
private submittedassignmentService submittedassignmentService;
	
	@GetMapping("/submittedassignment")
	public List<submittedassignment> retrieverAllsubmittedassignments() {
		return submittedassignmentService.findAll();
	}
	
	@GetMapping("/submittedassignment/{id}")
	public submittedassignment retrievesubmittedassignment(@PathVariable int id) {
		return submittedassignmentService.findOne(id);
	}
	
	
	@PostMapping("/submittedassignment")
	public submittedassignment createsubmittedassignment(@RequestBody submittedassignment stu) {
		System.out.println("adding submittedassignment");
		submittedassignment savedsubmittedassignment = submittedassignmentService.save(stu);
		return savedsubmittedassignment;
	}
	
	
	
	@DeleteMapping("/submittedassignment/{id}")
	public void deletesubmittedassignment(@PathVariable int id) throws Exception {
		try {
			submittedassignmentService.deleteById(id);
		}
		catch (Exception e) {
			throw new Exception("Not found id: " + id);
		}
	}
	
	@PutMapping("/submittedassignment/{id}")
	public submittedassignment updatesubmittedassignment(@RequestBody submittedassignment stu, @PathVariable int id) {
		submittedassignment existingsubmittedassignment = submittedassignmentService.findOne(id);
		if (existingsubmittedassignment == null) {
			throw new RuntimeException("submittedassignment not found with id: " + id);
		}
		stu.setId(id);
		return submittedassignmentService.updatesubmittedassignment(stu);
		
	}
}
