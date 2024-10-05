package com.mcb.assessment.service.impl;

import com.mcb.assessment.model.auth.User;
import com.mcb.assessment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SchedulerServiceImpl {

	@Autowired
	UserService userService;


	@Scheduled(cron = "0 0 0 * * *")
	public void run () {
		List<User> userList = userService.findAll ();
		Long totalUsercount =
				userList.stream ().filter (user -> !user.isAccountNonLocked ()).map (user -> userService.unlockWhenTimeExpired (user)).count ();
		log.info (" total no of user updated : {}", totalUsercount);
	}
}

