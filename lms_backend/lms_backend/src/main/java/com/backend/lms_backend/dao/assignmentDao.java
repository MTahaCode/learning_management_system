package com.backend.lms_backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.lms_backend.entity.announcement;
import com.backend.lms_backend.entity.assignment;
import com.backend.lms_backend.entity.teacher;
import com.backend.lms_backend.entity.teacherAssignment;


public interface assignmentDao extends JpaRepository<assignment, Integer>{
    List<assignment> findByTeacherAssignmentEntity(teacherAssignment teacherAssignmentEntity);

}
