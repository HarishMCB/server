package com.mcb.assessment.service.impl;

import com.mcb.assessment.model.auth.Role;
import com.mcb.assessment.model.auth.User;
import com.mcb.assessment.repository.UserDao;
import com.mcb.assessment.service.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
	@MockBean
	private RoleService roleService;
	@MockBean
	private UserDao userDao;

	@InjectMocks
	private UserServiceImpl userService;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	private User prepareUserMock () {
		Role role = new Role (5, "USER", "User role");
		Set<Role> roleSet = new HashSet<> ();
		roleSet.add (role);
		User userResponseObject = new User (1L, "Harish", "$2a$10$6QvHtxN7RCpliXAoPrbg4ulDDKaFL3PeV.wLvmE4l" +
				".uCVTLRGRu3C",
				"Harish.shukla@gmail.com", "8919454053", "consultant", true, 0, new Date (), roleSet);
		return userResponseObject;
	}

	@Test
	void loadUserByUsername () {
		String username = "Harish";
		User user = prepareUserMock ();
		Mockito.when (userDao.findByUsername (username)).thenReturn (user);
		UserDetails actual = userService.loadUserByUsername (username);
		assert (actual.getUsername ().equals (user.getUsername ()));
	}

	@Test
	void increaseFailedAttempts () {
		String username = "Harish";
		User user = prepareUserMock ();
		user.setFailedAttempt (3);
		Mockito.when (userDao.findByUsername (username)).thenReturn (user);
		assertThrows (BadCredentialsException.class, () -> userService.increaseFailedAttempts (username));
	}


}