package com.backend.lms_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.lms_backend.dao.attendanceDao;
import com.backend.lms_backend.entity.attendance;
import com.backend.lms_backend.entity.attendance;

@Service
public class attendanceService {
	
	@Autowired
	private attendanceDao attendanceDAO;
	
	//get all
	public List<attendance> findAll() {
		return attendanceDAO.findAll();
	}
	
	//add attendance
	public attendance save(attendance att) {
		attendanceDAO.save(att);
		return att;
	}
	
	//find one attendance
	public attendance findOne(Integer id) {
		return attendanceDAO.findById(id).get();
	}
	
	public void deleteById(Integer id) {
		attendance att = attendanceDAO.findById(id).get();
		attendanceDAO.delete(att);
	}
	
	public attendance updateattendance(attendance updatedAtt) {
		attendanceDAO.save(updatedAtt);
		return updatedAtt;
	}
}
