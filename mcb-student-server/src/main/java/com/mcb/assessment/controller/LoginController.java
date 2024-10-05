package com.mcb.assessment.controller;


import com.mcb.assessment.config.TokenProvider;
import com.mcb.assessment.model.auth.AuthToken;
import com.mcb.assessment.model.auth.LoginUser;
import com.mcb.assessment.model.auth.User;
import com.mcb.assessment.model.auth.UserDto;
import com.mcb.assessment.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.util.HashMap;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/login")
@Slf4j
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenProvider jwtTokenUtil;

	@Autowired
	private UserService userService;

	@Operation(summary = "User Authentication to retrieve JWT Token ", description = "Get JWT Token using  username " +
			"and password")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description =
					"Return JWT token for subsequent request Authorization",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = HashMap.class))})
	})
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> generateToken (@RequestBody LoginUser loginUser) throws AuthenticationException {
		String token = null;
		log.info ("Request recieved for user login with  user id {}", loginUser.getUsername ());
		try {
			final Authentication authentication = authenticationManager.authenticate (
					new UsernamePasswordAuthenticationToken (
							loginUser.getUsername (),
							loginUser.getPassword ()
					)
			);
			SecurityContextHolder.getContext ().setAuthentication (authentication);
			token = jwtTokenUtil.generateToken (authentication);

		} catch (BadCredentialsException e) {
			log.info ("Bad credentials provided for user {}", loginUser.getUsername ());
			userService.increaseFailedAttempts (loginUser.getUsername ());
		}
		userService.resetFailedAttempts (loginUser.getUsername ());
		return ResponseEntity.ok (new AuthToken (token));
	}


	@Operation(summary = "Register new User", description = "Register new User with Username, password, email and " +
			"phonenumber")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Returns User details",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = HashMap.class))}),

	})
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public User saveUser (@RequestBody UserDto user) {
		return userService.save (user);
	}


}
