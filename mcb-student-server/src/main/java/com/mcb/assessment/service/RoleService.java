package com.mcb.assessment.service;

import com.mcb.assessment.model.auth.Role;

public interface RoleService {
	Role findByName (String name);
}
