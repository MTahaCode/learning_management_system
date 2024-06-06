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

import com.backend.lms_backend.entity.attemptquiz;
import com.backend.lms_backend.entity.teacherAssignment;
import com.backend.lms_backend.service.attemptquizService;
import com.backend.lms_backend.service.teacherService;

@RestController
public class attempquizController {
	@Autowired
	private attemptquizService attemptquizService;
	@Autowired
	private teacherService TeacherService;
	@GetMapping("/attemptquiz")
	public List<attemptquiz> retrieverAllattemptquizs() {
		return attemptquizService.findAll();
	}
	
	@GetMapping("/attemptquiz/{id}")
	public attemptquiz retrieveattemptquiz(@PathVariable int id) {
		return attemptquizService.findOne(id);
	}
	
	//Get all the attempted quizes for the teacher based on the teacher and course id that they are teaching to 
	@GetMapping("/attemptquiz/{teacherid}/{courseid}")
	public List<attemptquiz> retrieveAttemptquizforteacher(@PathVariable int teacherid,@PathVariable int courseid) {
		List<attemptquiz> attemptedQuizes= attemptquizService.findAll();
		List<attemptquiz> specificquizes= attemptquizService.findAll();
		for(attemptquiz q:attemptedQuizes)
		{
			List<teacherAssignment> teacherassigns = q.getEnrolled().getCourseEntity().getTeacherAssignments();
			for(teacherAssignment t :teacherassigns)
			{
				if(t.getTeacherEntity().getId() == t.getTeacherEntity().getId() && t.getCourseEntity().getId() == courseid)
				{
					specificquizes.add(q);
					break;
				}
			}
		}
		return specificquizes;
	}
	
	
	
	@PostMapping("/attemptquiz")
	public attemptquiz createattemptquiz(@RequestBody attemptquiz stu) {
		System.out.println("adding attemptquiz");
		attemptquiz savedattemptquiz = attemptquizService.save(stu);
		return savedattemptquiz;
	}
	
	
	
	@DeleteMapping("/attemptquiz/{id}")
	public void deleteattemptquiz(@PathVariable int id) throws Exception {
		try {
			attemptquizService.deleteById(id);
		}
		catch (Exception e) {
			throw new Exception("Not found id: " + id);
		}
	}
	
	@PutMapping("/attemptquiz/{id}")
	public attemptquiz updateattemptquiz(@RequestBody attemptquiz stu, @PathVariable int id) {
		attemptquiz existingattemptquiz = attemptquizService.findOne(id);
		if (existingattemptquiz == null) {
			throw new RuntimeException("attemptquiz not found with id: " + id);
		}
		stu.setId(id);
		return attemptquizService.updateattemptquiz(stu);
		
	}
}
