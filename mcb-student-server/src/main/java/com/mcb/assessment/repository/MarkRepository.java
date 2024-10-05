package com.mcb.assessment.repository;

import com.mcb.assessment.model.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Set;

@EnableJpaRepositories
public interface MarkRepository extends JpaRepository<Mark, Long> {
	Set<Mark> findByStudentStudentId (Long studentId);
}
