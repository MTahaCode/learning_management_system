package com.backend.lms_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.lms_backend.entity.course;

public interface courseDao extends JpaRepository<course, Integer> {

}

