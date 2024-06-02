package com.backend.lms_backend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.lms_backend.entity.student;

public interface studentDao extends JpaRepository<student, Integer> {
	Optional<student> findByEmailAndPassword(String email, String password);
}
