package com.mcb.assessment.repository;

import com.mcb.assessment.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface TeacherRepository extends JpaRepository< Teacher,Long> {
}
