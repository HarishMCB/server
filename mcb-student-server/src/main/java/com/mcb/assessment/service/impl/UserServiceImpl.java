package com.mcb.assessment.service.impl;

import com.mcb.assessment.exceptionhandler.AccountLockedException;
import com.mcb.assessment.model.auth.Role;
import com.mcb.assessment.model.auth.User;
import com.mcb.assessment.model.auth.UserDto;
import com.mcb.assessment.repository.UserDao;
import com.mcb.assessment.service.RoleService;
import com.mcb.assessment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service(value = "userService")
@Slf4j
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private RoleService roleService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;


	public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException, AccountLockedException {
		log.info ("Loading user with username {}", username);
		User user = userDao.findByUsername (username);
		if (user == null) {
			log.error ("Invalid username or password for {}", username);
			throw new UsernameNotFoundException ("Invalid username or password.");
		}
		if (!user.isAccountNonLocked ()) {
			log.error ("User account is loacked : {}", username);
			throw new AccountLockedException (username);
		}
		return new org.springframework.security.core.userdetails.User (user.getUsername (), user.getPassword (),
				getAuthority (user));
	}

	public void increaseFailedAttempts (String userName) {
		User user = userDao.findByUsername (userName);
		log.error ("total failed attempt for user : {}", user.getFailedAttempt ());
		if (user != null && user.getFailedAttempt () > 2) {
			lock (user);
			throw new BadCredentialsException ("User account is Locked for 24hrs and Maximum login attempt exceeded");
		} else {
			int newFailAttempts = user.getFailedAttempt () + 1;
			int updatedRow = userDao.updateFailedAttempts (newFailAttempts, user.getUsername ());
			log.info ("FAILED Attempt no of rows updated " + updatedRow);
			throw new BadCredentialsException ("Invalid Credential"
			);

		}
	}

	@Transactional
	public void resetFailedAttempts (String userName) {

		userDao.updateFailedAttempts (0, userName);
	}

	public void lock (User user) {
		log.info ("User account to be locked : {}", user.getUsername ());
		user.setAccountNonLocked (false);
		user.setLockTime (new Date ());
		userDao.save (user);
	}

	@Transactional
	public boolean unlockWhenTimeExpired (User user) {
		long lockTimeInMillis = user.getLockTime ().getTime ();
		long currentTimeInMillis = System.currentTimeMillis ();

		if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
			user.setAccountNonLocked (true);
			user.setLockTime (null);
			user.setFailedAttempt (0);

			userDao.save (user);
			log.info ("User account is Unlocked and user is : {}", user.getUsername ());
			return true;
		}

		return false;
	}

	private Set<SimpleGrantedAuthority> getAuthority (User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<> ();
		user.getRoles ().forEach (role -> {
			authorities.add (new SimpleGrantedAuthority ("ROLE_" + role.getName ()));
		});
		return authorities;
	}

	public List<User> findAll () {
		List<User> list = new ArrayList<> ();
		userDao.findAll ().iterator ().forEachRemaining (list :: add);
		return list;
	}

	@Override
	public User findOne (String username) {
		return userDao.findByUsername (username);
	}

	@Override
	public User save (UserDto user) {

		User nUser = user.getUserFromDto ();
		nUser.setPassword (bcryptEncoder.encode (user.getPassword ()));

		Role role = roleService.findByName ("USER");
		Set<Role> roleSet = new HashSet<> ();
		roleSet.add (role);

		if (nUser.getEmail ().split ("@")[1].equals ("admin.com")) {
			role = roleService.findByName ("ADMIN");
			roleSet.add (role);
		}

		nUser.setRoles (roleSet);
		return userDao.save (nUser);
	}
}
