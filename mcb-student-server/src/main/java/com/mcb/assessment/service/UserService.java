package com.mcb.assessment.service;


import com.mcb.assessment.model.auth.User;
import com.mcb.assessment.model.auth.UserDto;

import java.util.List;

public interface UserService {
	int MAX_FAILED_ATTEMPTS = 3;
	long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000; // 24 hours

	User save (UserDto user);

	List<User> findAll ();

	User findOne (String username);

	void increaseFailedAttempts (String userName);

	void lock (User user);

	boolean unlockWhenTimeExpired (User user);

	void resetFailedAttempts (String username);
}
