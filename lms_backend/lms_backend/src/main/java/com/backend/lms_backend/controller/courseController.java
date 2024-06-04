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

import com.backend.lms_backend.entity.course;
import com.backend.lms_backend.entity.student;
import com.backend.lms_backend.service.courseService;
import com.backend.lms_backend.service.studentService;

@RestController
public class courseController {

	@Autowired
	private courseService CourseService;

	@Autowired
	private studentService StudentService;
	
	@GetMapping("/course")
	public List<course> retrieverAllcourses() {
		return CourseService.findAll();
	}
	
	@GetMapping("/course/{id}")
	public course retrievecourse(@PathVariable int id) {
		return CourseService.findOne(id);
	}
	
	@GetMapping("/course/enrollable-courses/{student_id}")
	public List<course> getEnrollableCourses(@PathVariable int student_id) {
		student studentEntity = StudentService.findOne(student_id);
		return CourseService.getEnrollableCourses(studentEntity);
	}
	
	@PostMapping("/course")
	public course createcourse(@RequestBody course stu) {
		System.out.println("adding course");
		course savedcourse = CourseService.save(stu);
		return savedcourse;
	}
	
	@DeleteMapping("/course/{id}")
	public void deletecourse(@PathVariable int id) throws Exception {
		try {
			CourseService.deleteById(id);
		}
		catch (Exception e) {
			throw new Exception("Not found id: " + id);
		}
	}
	
	@PutMapping("/course/{id}")
	public course updatecourse(@RequestBody course stu, @PathVariable int id) {
		course existingcourse = CourseService.findOne(id);
		if (existingcourse == null) {
			throw new RuntimeException("course not found with id: " + id);
		}
		stu.setId(id);
		return CourseService.updatecourse(stu);
		
	}
}


