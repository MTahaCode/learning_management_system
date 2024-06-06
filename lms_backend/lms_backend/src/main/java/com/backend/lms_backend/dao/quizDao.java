package com.backend.lms_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.lms_backend.entity.course;
import com.backend.lms_backend.entity.quiz;

public interface quizDao extends JpaRepository<quiz, Integer> {

}
