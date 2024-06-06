package com.backend.lms_backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.lms_backend.entity.teacherAssignment;
import com.backend.lms_backend.entity.teacher;

public interface teacherAssignmentDao extends JpaRepository<teacherAssignment, Integer> {
	List<teacherAssignment> findByTeacherEntity(teacher teacherEntity);
	
	@Query("SELECT DISTINCT ta.section FROM teacherAssignment ta WHERE ta.courseEntity.id = :course_id")
	List<String> findDistinctSectionsByCourseId(@Param("course_id") int course_id);
}
