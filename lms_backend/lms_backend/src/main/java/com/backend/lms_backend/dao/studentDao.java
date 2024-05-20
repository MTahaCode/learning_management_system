package com.backend.lms_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.lms_backend.entity.student;

public interface studentDao extends JpaRepository<student, Integer> {

}
