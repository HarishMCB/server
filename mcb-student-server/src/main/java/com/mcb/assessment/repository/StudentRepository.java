package com.mcb.assessment.repository;

import com.mcb.assessment.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface StudentRepository extends JpaRepository<Student,Long>  {
}
