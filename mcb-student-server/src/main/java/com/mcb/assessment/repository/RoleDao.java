package com.mcb.assessment.repository;

import com.mcb.assessment.model.auth.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role, Long> {
	Role findRoleByName (String name);
}