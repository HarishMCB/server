package com.mcb.assessment.service.impl;


import com.mcb.assessment.model.auth.Role;
import com.mcb.assessment.repository.RoleDao;
import com.mcb.assessment.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public Role findByName (String name) {
		Role role = roleDao.findRoleByName (name);
		return role;
	}
}
