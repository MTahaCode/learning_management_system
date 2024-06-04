package com.backend.lms_backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.lms_backend.entity.enrollment;
import com.backend.lms_backend.entity.student;

public interface enrollmentDao extends JpaRepository<enrollment, Integer> {
	List<enrollment> findByStudentEntity(student studentEntity);
}

