package com.backend.lms_backend.controller;

import java.util.ArrayList;
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
import com.backend.lms_backend.entity.enrollment;
import com.backend.lms_backend.entity.teacherAssignment;
import com.backend.lms_backend.service.assignmentService;
import com.backend.lms_backend.service.enrollmentService;
import com.backend.lms_backend.service.studentService;
import com.backend.lms_backend.service.teacherAssignmentService;

@RestController
public class assignmentController {
	@Autowired
	private assignmentService assignmentService;
	@Autowired
	private enrollmentService EnrollmentService;
	@Autowired
	private studentService StudentService;
	@Autowired
	private teacherAssignmentService TeacherAssignmentService;
	
	@GetMapping("/assignment")
	public List<assignment> retrieverAllassignments() {
		return assignmentService.findAll();
	}
	
	@GetMapping("/assignment/{id}")
	public assignment retrieveassignment(@PathVariable int id) {
		return assignmentService.findOne(id);
	}
	@GetMapping("/assignments/teacherAssignment/{teacher_assignment_id}")
	public List<assignment> retrieveassignmentbyteacher(@PathVariable int teacher_assignment_id) {
		teacherAssignment TeacherAssignment = TeacherAssignmentService.findOne(teacher_assignment_id);
		return assignmentService.getTeacherAssignmentsByTeacher(TeacherAssignment);
	}
	//Give list of assigned assignment of specific student enrolled in that specifc course 
	@GetMapping("/assignment/{studentid}/{courseid}")
	public List<assignment> retrieveEnrollmentAssignments(@PathVariable int studentid,@PathVariable int courseid) {
		List<assignment> assignments =new ArrayList<assignment>();

		List<enrollment> enrolls = EnrollmentService.getEnrollmentsByStudent(StudentService.findOne(studentid));
		for(assignment a: assignmentService.findAll())
		{
			for(enrollment e : enrolls)
			{
				if(a.getTeacherAssignmentEntity().getCourseEntity().getId() == e.getCourseEntity().getId() &&e.getCourseEntity().getId() == courseid)
				{
					assignments.add(a);
					break;
					
				}
			}
		}
		return assignments;
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
