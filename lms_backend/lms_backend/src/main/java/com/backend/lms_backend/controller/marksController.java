package com.backend.lms_backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.lms_backend.entity.assignment;
import com.backend.lms_backend.entity.attemptquiz;
import com.backend.lms_backend.entity.submittedassignment;
import com.backend.lms_backend.service.attemptquizService;
import com.backend.lms_backend.service.submittedassignmentService;

@RestController
public class marksController {
	@Autowired
	private attemptquizService attemptquizService;
	
	private submittedassignmentService submittedassignmentService;
	
	//Function for assigning marks to the attempted quiz
	@PutMapping("/assignmarkstoquiz/{id}/{marksobtained}")
	public attemptquiz assignMarksToAttemptQuiz(@RequestBody attemptquiz stu, @PathVariable int id,@PathVariable double marksobtained) {
		attemptquiz existingattemptquiz = attemptquizService.findOne(id);
		if (existingattemptquiz == null) {
			throw new RuntimeException("attemptquiz not found with id: " + id);
		}
		stu.setId(id);
		//Marks that are obtained are set here
		stu.setMarksAttained(marksobtained);
		return attemptquizService.updateattemptquiz(stu);
		
	}
	
	//Assign marks to a submitted assignment
	@PutMapping("/assignmarkstoquiz/{id}/{marksobtained}")
	public submittedassignment assignMarksToSubmittedAssignment(@RequestBody submittedassignment stu, @PathVariable int id,@PathVariable double marksobtained) {
		submittedassignment existingsubmittedassignment = submittedassignmentService.findOne(id);
		if (existingsubmittedassignment == null) {
			throw new RuntimeException("submittedassignment not found with id: " + id);
		}
		stu.setId(id);
		//Marks that are obtained are set here
		stu.setMarksAttained(marksobtained);
		return submittedassignmentService.updatesubmittedassignment(stu);
		
	}
	
	//Function to get marks for a particular attempted quiz
	@GetMapping("/attemptquiz/{id}")
	public Double retrieveMarksOfAttemptQuiz(@PathVariable int id) {
		attemptquiz existingattemptquiz = attemptquizService.findOne(id);
		return existingattemptquiz.getMarksAttained();
	}
	
	
	//Function to get marks for a particular attempted quiz
		@GetMapping("/submittedassignment/{id}")
		public Double retrieveMarksOfSubmittedAssignment(@PathVariable int id) {
			submittedassignment existingsubmittedassignment = submittedassignmentService.findOne(id);
			return existingsubmittedassignment.getMarksAttained();
		}
		
		
	//Get all the marks obtained of each submittedasssignment of a particular enrolled student with his enrollmentid
	@GetMapping("/submittedassignment/{enrollmentid}")
	public List<Double> ObtainedMarksAllsubmittedassignments(@PathVariable int enrollmentid) {
		List<submittedassignment> submissions = submittedassignmentService.findAll();
		List<Double> marks= new ArrayList<Double>();
		for(submittedassignment submitted:submissions)
		{
			if(submitted.getEnrolled().getId() == enrollmentid) //Check if it is the same student
			{
				marks.add(submitted.getMarksAttained());
			}
		}
		return marks;
	}
		
	//Get all the marks obtained of each Attempted Quiz of a particular enrolled student with his enrollmentid
	@GetMapping("/submittedassignment/{enrollmentid}")
	public List<Double> ObtainedMarksAllAttemptedQuizes(@PathVariable int enrollmentid) {
		List<attemptquiz> attempted =  attemptquizService.findAll();
		List<Double> marks= new ArrayList<Double>();
		for(attemptquiz attempt:attempted)
		{
			if(attempt.getEnrolled().getId() == enrollmentid) //Check if it is the same student
			{
				marks.add(attempt.getMarksAttained());
			}
		}
		return marks;
	}
}
