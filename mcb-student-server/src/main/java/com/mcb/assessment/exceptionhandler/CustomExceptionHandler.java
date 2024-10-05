package com.mcb.assessment.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@Repository
@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<ErrorDetails> handleValidationError (MethodArgumentNotValidException ex) {
		log.error ("Data Validation failed");
		StringBuilder sb = new StringBuilder ();
		String errorMsg =
				ex.getBindingResult ().getFieldErrors ().stream ().map (e -> e.getField () + " : " + e.getDefaultMessage ()).collect (Collectors.joining (","));
		return new ResponseEntity<> (new ErrorDetails ("Input Validation failed", errorMsg), HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(GroupNotFound.class)
	public final ResponseEntity<ErrorDetails> handleCityNotFoundException (GroupNotFound ex) {
		return new ResponseEntity<> (new ErrorDetails ("Group doesn't exist", ex.getMessage ()), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(StudentNotFound.class)
	public final ResponseEntity<ErrorDetails> handleCityNotFoundException (StudentNotFound ex) {
		return new ResponseEntity<> (new ErrorDetails ("Group doesn't exist", ex.getMessage ()), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(TeacherNotFound.class)
	public final ResponseEntity<ErrorDetails> handleTeacherNotFoundException (TeacherNotFound ex) {
		return new ResponseEntity<> (new ErrorDetails ("Teacher doesn't exist", ex.getMessage ()),
				HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(AccountLockedException.class)
	public final ResponseEntity<ErrorDetails> handleAccountLockedException (AccountLockedException ex) {
		return new ResponseEntity<> (new ErrorDetails ("Account has been locked", ex.getMessage ()),
				HttpStatus.NOT_ACCEPTABLE);

	}


	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<ErrorDetails> handleConstraintViolation (ConstraintViolationException ex) {
		return new ResponseEntity<> (new ErrorDetails ("Invalid parameter passed", ex.getMessage ()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleUnKnownException (Exception ex) {
		return new ResponseEntity<> (new ErrorDetails ("Server Error", ex.getMessage ()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public final ResponseEntity<ErrorDetails> handleConstraintViolation (BadCredentialsException ex) {
		return new ResponseEntity<> (new ErrorDetails ("INVALID Password", ex.getMessage ()),
				HttpStatus.BAD_REQUEST);
	}


}


