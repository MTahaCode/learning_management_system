package com.backend.lms_backend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.lms_backend.entity.teacher;

public interface teacherDao extends JpaRepository<teacher, Integer> {
	Optional<teacher> findByEmailAndPassword(String email, String password);
}

