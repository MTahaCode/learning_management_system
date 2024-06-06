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

import com.backend.lms_backend.entity.attendance;
import com.backend.lms_backend.entity.student;
import com.backend.lms_backend.service.attendanceService;
import com.backend.lms_backend.service.studentService;

@RestController
public class attendanceController {

	@Autowired
	private attendanceService AttendanceService;
	
	@GetMapping("/attendance")
	public List<attendance> retrieverAllattendances() {
		return AttendanceService.findAll();
	}
	
	@GetMapping("/attendance/{id}")
	public attendance retrieveattendance(@PathVariable int id) {
		return AttendanceService.findOne(id);
	}
	
	@PostMapping("/attendance")
	public attendance createattendance(@RequestBody attendance enr) {
		attendance savedattendance = AttendanceService.save(enr);
		return savedattendance;
	}
	
	@PostMapping("/attendance/createOrUpdate")
	public attendance createOrUpdateAttendance(@RequestBody attendance att) {
		if (att.getId() == null) {
			return createattendance(att);
		}
		else {
			return updateattendance(att, att.getId());
		}
	}
	
	@DeleteMapping("/attendance/{id}")
	public void deleteattendance(@PathVariable int id) throws Exception {
		try {
			AttendanceService.deleteById(id);
		}
		catch (Exception e) {
			throw new Exception("Not found id: " + id);
		}
	}
	
	@PutMapping("/attendance/{id}")
	public attendance updateattendance(@RequestBody attendance enr, @PathVariable int id) {
		attendance existingattendance = AttendanceService.findOne(id);
		if (existingattendance == null) {
			throw new RuntimeException("attendance not found with id: " + id);
		}
		enr.setId(id);
		return AttendanceService.updateattendance(enr);
		
	}
}
