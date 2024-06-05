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
import com.backend.lms_backend.service.teacherService;

@RestController
public class teacherController {

	@Autowired
	private teacherService TeacherService;
	
	@GetMapping("/teacher")
	public List<teacher> retrieverAllTeachers() {
		return TeacherService.findAll();
	}
	
	@GetMapping("/teacher/{id}")
	public teacher retrieveTeacher(@PathVariable int id) {
		return TeacherService.findOne(id);
	}
	
	@GetMapping("/teacher/teacherAssignments/{teacher_id}")
	public List<teacherAssignment> retrieveTeacherAssignmentByTeacher(@PathVariable int teacher_id) {
		teacher teacherEntity = TeacherService.findOne(teacher_id);
		return teacherEntity.getTeacherAssignments();
	}
	
	@PostMapping("/teacher")
	public teacher createTeacher(@RequestBody teacher tea) {
		System.out.println("adding teacher");
		teacher savedTeacher = TeacherService.save(tea);
		return savedTeacher;
	}
	
	//getting by email and password
	@PostMapping("teacher/getByEmailAndPassword") 
	public teacher getByEmailPassword(@RequestBody teacher tea) throws Exception {
		teacher foundTeacher= TeacherService.findTeacherByEmailAndPassword(tea.getEmail(), tea.getPassword());
		//System.out.println(tea.getTeacher_id() + " " + tea.getName() + " " + tea.getEmail() + " " + tea.getPassword());
		//System.out.println(foundTeacher.getTeacher_id() + " " + foundTeacher.getName() + " " + foundTeacher.getEmail() + " " + foundTeacher.getPassword());
		if (foundTeacher != null) {
			return foundTeacher;
		}
		return tea;
	}
	
	@DeleteMapping("/teacher/{id}")
	public void deleteTeacher(@PathVariable int id) throws Exception {
		try {
			TeacherService.deleteById(id);
		}
		catch (Exception e) {
			throw new Exception("Not found id: " + id);
		}
	}
	
	@PutMapping("/teacher/{id}")
	public teacher updateTeacher(@RequestBody teacher tea, @PathVariable int id) {
		teacher existingTeacher = TeacherService.findOne(id);
		if (existingTeacher == null) {
			throw new RuntimeException("Teacher not found with id: " + id);
		}
		tea.setId(id);
		return TeacherService.updateTeacher(tea);
		
	}
}
