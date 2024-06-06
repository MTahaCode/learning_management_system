package com.backend.lms_backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.lms_backend.entity.course;
import com.backend.lms_backend.entity.student;
import com.backend.lms_backend.entity.teacher;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface courseDao extends JpaRepository<course, Integer> {
	
	@Query("Select c FROM course c WHERE c.id NOT IN "
			+ "( SELECT e.courseEntity.id FROM enrollment e WHERE e.studentEntity = :studentEntity )")
	List<course> findUnenrolledCourseByStudent(@Param("studentEntity") student studentEntity);
	
	@Query("Select c FROM course c WHERE c.id NOT IN "
			+ "( SELECT ta.courseEntity.id FROM teacherAssignment ta WHERE ta.teacherEntity = :teacherEntity )")
	List<course> findUnassignedCourseByTeacher(@Param("teacherEntity") teacher teacherEntity);
}

