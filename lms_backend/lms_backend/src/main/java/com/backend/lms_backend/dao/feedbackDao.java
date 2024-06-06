package com.backend.lms_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.lms_backend.entity.feedback;


public interface feedbackDao extends JpaRepository<feedback, Integer> {

}
