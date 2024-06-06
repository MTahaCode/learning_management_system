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

import com.backend.lms_backend.entity.enrollment;
import com.backend.lms_backend.entity.student;
import com.backend.lms_backend.service.enrollmentService;
import com.backend.lms_backend.service.studentService;

@RestController
public class enrollmentController {

	@Autowired
	private enrollmentService EnrollmentService;
	
	@Autowired
	private studentService StudentService;
	
	@GetMapping("/enrollment")
	public List<enrollment> retrieverAllenrollments() {
		return EnrollmentService.findAll();
	}
	
	@GetMapping("/enrollment/student/{student_id}")
	public List<enrollment> getEnrollmentsByStudent(@PathVariable int student_id) {
		student studentEntity = StudentService.findOne(student_id);
		return EnrollmentService.getEnrollmentsByStudent(studentEntity);
	}
	
	@GetMapping("/enrollment/course/{course_id}/section/{section}")
	public List<enrollment> retrieveEnrollmentsByCourse(
			@PathVariable int course_id,
			@PathVariable String section
			) {
		return EnrollmentService.getByCourseIdAndSection(course_id, section);
	}
	
	@GetMapping("/enrollment/{id}")
	public enrollment retrieveenrollment(@PathVariable int id) {
		return EnrollmentService.findOne(id);
	}
	
	@PostMapping("/enrollment")
	public enrollment createenrollment(@RequestBody enrollment enr) {
		System.out.println("adding enrollment");
		System.out.print(enr.getId());
		System.out.print(enr.getGrade());
		System.out.print(enr.getSemester());
		System.out.print(enr.getCourseEntity().getId());
		System.out.print(enr.getSection());
		System.out.println(enr.getStudentEntity().getId());
		enrollment savedenrollment = EnrollmentService.save(enr);
		return savedenrollment;
	}
	
	@DeleteMapping("/enrollment/{id}")
	public void deleteenrollment(@PathVariable int id) throws Exception {
		try {
			EnrollmentService.deleteById(id);
		}
		catch (Exception e) {
			throw new Exception("Not found id: " + id);
		}
	}
	
	@PutMapping("/enrollment/{id}")
	public enrollment updateenrollment(@RequestBody enrollment enr, @PathVariable int id) {
		enrollment existingenrollment = EnrollmentService.findOne(id);
		if (existingenrollment == null) {
			throw new RuntimeException("enrollment not found with id: " + id);
		}
		enr.setId(id);
		return EnrollmentService.updateenrollment(enr);
		
	}
}
