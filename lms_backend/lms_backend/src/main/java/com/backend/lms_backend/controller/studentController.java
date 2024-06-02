package com.backend.lms_backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.lms_backend.entity.student;
import com.backend.lms_backend.service.studentService;

@RestController
public class studentController {

	@Autowired
	private studentService service;
	
	@GetMapping("/student")
	public List<student> retrieverAllStudents() {
		return service.findAll();
	}
	
	@GetMapping("/student/{id}")
	public student retrieveStudent(@PathVariable int id) {
		return service.findOne(id);
	}
	
	@PostMapping("/student")
	public student createStudent(@RequestBody student stu) {
		System.out.println("adding student");
		student savedStudent = service.save(stu);
		return savedStudent;
	}
	
	//getting by email and password
	@PostMapping("student/getByEmailAndPassword") 
	public student getByEmailPassword(@RequestBody student stu) throws Exception {
		student foundStudent= service.findStudentByEmailAndPassword(stu.getEmail(), stu.getPassword());
		//System.out.println(stu.getStudent_id() + " " + stu.getName() + " " + stu.getEmail() + " " + stu.getPassword());
		//System.out.println(foundStudent.getStudent_id() + " " + foundStudent.getName() + " " + foundStudent.getEmail() + " " + foundStudent.getPassword());
		if (foundStudent != null) {
			return foundStudent;
		}
		return stu;
	}
	
	@DeleteMapping("/student/{id}")
	public void deleteStudent(@PathVariable int id) throws Exception {
		try {
			service.deleteById(id);
		}
		catch (Exception e) {
			throw new Exception("Not found id: " + id);
		}
	}
	
	@PutMapping("/student/{id}")
	public student updateStudent(@RequestBody student stu, @PathVariable int id) {
		student existingStudent = service.findOne(id);
		if (existingStudent == null) {
			throw new RuntimeException("Student not found with id: " + id);
		}
		stu.setId(id);
		return service.updateStudent(stu);
		
	}
}






