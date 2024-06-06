package com.backend.lms_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.lms_backend.entity.announcement;

public interface announcementDao extends JpaRepository<announcement, Integer> {

}
