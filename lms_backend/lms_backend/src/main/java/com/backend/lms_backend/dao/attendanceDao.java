package com.backend.lms_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.lms_backend.entity.attendance;

public interface attendanceDao extends JpaRepository<attendance, Integer> {
	
}