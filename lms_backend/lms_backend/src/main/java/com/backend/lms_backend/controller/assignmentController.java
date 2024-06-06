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

import com.backend.lms_backend.entity.assignment;
import com.backend.lms_backend.service.assignmentService;

@RestController
public class assignmentController {
	@Autowired
	private assignmentService assignmentService;
	
	@GetMapping("/assignment")
	public List<assignment> retrieverAllassignments() {
		return assignmentService.findAll();
	}
	
	@GetMapping("/assignment/{id}")
	public assignment retrieveassignment(@PathVariable int id) {
		return assignmentService.findOne(id);
	}
	
	
	@PostMapping("/assignment")
	public assignment createassignment(@RequestBody assignment stu) {
		System.out.println("adding assignment");
		assignment savedassignment = assignmentService.save(stu);
		return savedassignment;
	}
	
	
	
	@DeleteMapping("/assignment/{id}")
	public void deleteassignment(@PathVariable int id) throws Exception {
		try {
			assignmentService.deleteById(id);
		}
		catch (Exception e) {
			throw new Exception("Not found id: " + id);
		}
	}
	
	@PutMapping("/assignment/{id}")
	public assignment updateassignment(@RequestBody assignment stu, @PathVariable int id) {
		assignment existingassignment = assignmentService.findOne(id);
		if (existingassignment == null) {
			throw new RuntimeException("assignment not found with id: " + id);
		}
		stu.setId(id);
		return assignmentService.updateassignment(stu);
		
	}
}
