package com.backend.lms_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.lms_backend.entity.quiz;
import com.backend.lms_backend.service.quizService;

@RestController
public class quizController {
	@Autowired
	private quizService quizService;
	
	@GetMapping("/quiz")
	public List<quiz> retrieverAllquizs() {
		return quizService.findAll();
	}
	
	@GetMapping("/quiz/{id}")
	public quiz retrievequiz(@PathVariable int id) {
		return quizService.findOne(id);
	}
	
	
	@PostMapping("/quiz")
	public quiz createquiz(@RequestBody quiz stu) {
		System.out.println("adding quiz");
		quiz savedquiz = quizService.save(stu);
		return savedquiz;
	}
	
	
	
	@DeleteMapping("/quiz/{id}")
	public void deletequiz(@PathVariable int id) throws Exception {
		try {
			quizService.deleteById(id);
		}
		catch (Exception e) {
			throw new Exception("Not found id: " + id);
		}
	}
	
	@PutMapping("/quiz/{id}")
	public quiz updatequiz(@RequestBody quiz stu, @PathVariable int id) {
		quiz existingquiz = quizService.findOne(id);
		if (existingquiz == null) {
			throw new RuntimeException("quiz not found with id: " + id);
		}
		stu.setId(id);
		return quizService.updatequiz(stu);
		
	}
}
