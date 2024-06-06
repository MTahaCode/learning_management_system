package com.backend.lms_backend.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.lms_backend.entity.teacher;

public interface teacherDao extends JpaRepository<teacher, Integer> {
	Optional<teacher> findByEmailAndPassword(String email, String password);
	
	@Query("SELECT t FROM teacher t WHERE t.id NOT IN "
			+ "(SELECT ta.teacherEntity.id FROM teacherAssignment ta WHERE ta.courseEntity.id = :course_id "
			+ "AND ta.section = :section)")
	List<teacher> findTeacherNotAssignedToCourse(@Param("course_id") int course_id, @Param("section") String section);
}

