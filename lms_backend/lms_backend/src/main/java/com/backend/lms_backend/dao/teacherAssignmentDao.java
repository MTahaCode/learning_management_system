package com.backend.lms_backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.lms_backend.entity.teacherAssignment;
import com.backend.lms_backend.entity.teacher;

public interface teacherAssignmentDao extends JpaRepository<teacherAssignment, Integer> {
	List<teacherAssignment> findByTeacherEntity(teacher teacherEntity);
}
