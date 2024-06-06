package com.backend.lms_backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.lms_backend.entity.enrollment;
import com.backend.lms_backend.entity.student;

public interface enrollmentDao extends JpaRepository<enrollment, Integer> {
	List<enrollment> findByStudentEntity(student studentEntity);
	
	@Query("SELECT e FROM enrollment e WHERE e.courseEntity.id = :course_id AND e.section = :section")
	List<enrollment> findByCourseEntity_idAndSection(@Param("course_id") int course_id, @Param("section") String section);
}

