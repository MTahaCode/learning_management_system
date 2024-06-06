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

import com.backend.lms_backend.entity.teacherAssignment;
import com.backend.lms_backend.entity.teacher;
import com.backend.lms_backend.service.teacherAssignmentService;
import com.backend.lms_backend.service.teacherService;

@RestController
public class teacherAssignmentController {
	
	@Autowired
	private teacherAssignmentService teacherAssignmentService;
	
	@Autowired
	private teacherService TeacherService;
	
	@GetMapping("/teacherAssignment")
	public List<teacherAssignment> retrieverAllteacherAssignments() {
		return teacherAssignmentService.findAll();
	}
	
	@GetMapping("/teacherAssignment/teacher/{teacher_id}")
	public List<teacherAssignment> getteacherAssignmentsByTeacher(@PathVariable int teacher_id) {
		teacher teacherEntity = TeacherService.findOne(teacher_id);
		return teacherAssignmentService.getTeacherAssignmentsByTeacher(teacherEntity);
	}
	
	@GetMapping("/teacherAssignment/avaliableSectionsOfCourse/{course_id}")
	public List<String> retrieveAvaliableSectionsOfCourse(@PathVariable int course_id) {
		return teacherAssignmentService.getSectionsOfCourse(course_id);
	}
	
	@GetMapping("/teacherAssignment/{id}")
	public teacherAssignment retrieveteacherAssignment(@PathVariable int id) {
		return teacherAssignmentService.findOne(id);
	}
	
	@PostMapping("/teacherAssignment")
	public teacherAssignment createteacherAssignment(@RequestBody teacherAssignment ta) {
		System.out.println("adding teacherAssignment");
		System.out.print(ta.getId());
		System.out.print(ta.getSemester());
		System.out.print(ta.getCourseEntity().getId());
		System.out.print(ta.getSection());
		System.out.println(ta.getTeacherEntity().getId());
		teacherAssignment savedteacherAssignment = teacherAssignmentService.save(ta);
		return savedteacherAssignment;
	}
	
	@DeleteMapping("/teacherAssignment/{id}")
	public void deleteteacherAssignment(@PathVariable int id) throws Exception {
		try {
			teacherAssignmentService.deleteById(id);
		}
		catch (Exception e) {
			throw new Exception("Not found id: " + id);
		}
	}
	
	@PutMapping("/teacherAssignment/{id}")
	public teacherAssignment updateteacherAssignment(@RequestBody teacherAssignment ta, @PathVariable int id) {
		teacherAssignment existingteacherAssignment = teacherAssignmentService.findOne(id);
		if (existingteacherAssignment == null) {
			throw new RuntimeException("teacherAssignment not found with id: " + id);
		}
		ta.setId(id);
		return teacherAssignmentService.updateteacherAssignment(ta);
		
	}
	
}
