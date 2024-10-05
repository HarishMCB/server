package com.mcb.assessment.controller;

import com.mcb.assessment.model.Mark;
import com.mcb.assessment.model.Student;
import com.mcb.assessment.service.StudentService;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Set;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
	@Autowired
	StudentService studentService;

	@Operation(summary = "get Student by ID", description = "Returns Student object using Student Id ")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Returns Student details",
							content = {@Content(mediaType = "application/json",
									schema = @Schema(implementation = HashMap.class))}),
					@ApiResponse(responseCode = "400", description = "Bad Request",
							content = {@Content(mediaType = "application/json",
									schema = @Schema(implementation = HashMap.class))}),

			})
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById (@PathVariable("id") @ApiParam(name = "id", value = "id",
			example = "1") Long id) {
		Student student = studentService.getByStudentId (id);
		return ResponseEntity.status (HttpStatus.OK).body (student);
	}

	@Operation(summary = "get total Marks of student using student id", description = "Returns total marks of " +
			"student")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Returns total marks of student",
							content = {@Content(mediaType = "application/json",
									schema = @Schema(implementation = HashMap.class))}),
					@ApiResponse(responseCode = "400", description = "Bad Request",
							content = {@Content(mediaType = "application/json",
									schema = @Schema(implementation = HashMap.class))}),

			})
	@GetMapping("/{id}/marks")
	public ResponseEntity<BigDecimal> getMarksByStudentId (@PathVariable("id") @ApiParam(name = "id", value = "id",
			example = "1") Long id) {
		return new ResponseEntity<> (studentService.getMarksByStudentId (id), HttpStatus.OK);
	}

	@Operation(summary = "List of subject and marks using student id", description = "Returns List of subject and " +
			"marks of student"
	)
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Returns List of subject and marks of student",
							content = {@Content(mediaType = "application/json",
									schema = @Schema(implementation = HashMap.class))}),
					@ApiResponse(responseCode = "400", description = "Bad Request",
							content = {@Content(mediaType = "application/json",
									schema = @Schema(implementation = HashMap.class))}),

			})
	@GetMapping("/{id}/subjects/marks")
	public ResponseEntity<Set<Mark>> getListOfMarksByStudentId (@PathVariable("id") @ApiParam(name = "id", value =
			"id",
			example = "1") Long id) {
		Set<Mark> marks = studentService.getListOfMarksByStudentId (id);
		return new ResponseEntity<> (marks, HttpStatus.OK);
	}

	@Operation(summary = "Add new Student", description = "returns new student objects"
	)
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "returns new student objects",
							content = {@Content(mediaType = "application/json",
									schema = @Schema(implementation = HashMap.class))}),
					@ApiResponse(responseCode = "400", description = "Bad Request",
							content = {@Content(mediaType = "application/json",
									schema = @Schema(implementation = HashMap.class))}),

			})
	@PostMapping("/")
	public ResponseEntity<Student> addStudent (@RequestBody Student studentDto) {
		Student student = studentService.addStudent (studentDto);
		return new ResponseEntity<> (student, HttpStatus.CREATED);
	}

	@Operation(summary = "get No of Students using teacher id", description = "Returns No of Students"
	)
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Returns No of Students",
							content = {@Content(mediaType = "application/json",
									schema = @Schema(implementation = HashMap.class))}),
					@ApiResponse(responseCode = "400", description = "Bad Request",
							content = {@Content(mediaType = "application/json",
									schema = @Schema(implementation = HashMap.class))}),

			})
	@GetMapping("/teachers/{id}")
	public ResponseEntity<Integer> getNoOfStudentsByTeacherId (@PathVariable("id") @ApiParam(name = "id", value = "id",
			example = "1") Long id) {
		int noOfStudents = studentService.getByTeacherId (id);
		return ResponseEntity.status (HttpStatus.OK).body (noOfStudents);
	}

}
