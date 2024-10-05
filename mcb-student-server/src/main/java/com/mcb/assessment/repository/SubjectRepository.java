package com.mcb.assessment.repository;

import com.mcb.assessment.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface SubjectRepository extends JpaRepository< Subject,Long>  {
}
