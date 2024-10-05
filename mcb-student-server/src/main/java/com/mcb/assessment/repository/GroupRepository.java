package com.mcb.assessment.repository;

import com.mcb.assessment.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group,Long> {
}
