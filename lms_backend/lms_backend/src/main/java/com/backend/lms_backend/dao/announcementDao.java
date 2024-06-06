package com.backend.lms_backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.lms_backend.entity.announcement;
import com.backend.lms_backend.entity.teacherAssignment;

public interface announcementDao extends JpaRepository<announcement, Integer> {
    List<announcement> findByTeacherAssignmentEntity(teacherAssignment teacherAssignmentEntity);

}
