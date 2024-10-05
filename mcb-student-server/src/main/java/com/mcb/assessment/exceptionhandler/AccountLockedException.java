package com.mcb.assessment.exceptionhandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountLockedException extends RuntimeException {

	public AccountLockedException (String userName) {
		super ();
		String message = "Account has been locked due to maximum login attempt expired :" + userName;
		log.error (message);

	}
}
