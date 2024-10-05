package com.mcb.assessment.repository;


import com.mcb.assessment.model.auth.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
	User findByUsername (String username);

	@Query("UPDATE User u SET u.failedAttempt = ?1 WHERE u.username = ?2")
	@Modifying
	@Transactional
	Integer updateFailedAttempts (int failAttempts, String username);

}