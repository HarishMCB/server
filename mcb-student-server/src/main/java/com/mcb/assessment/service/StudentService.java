package com.mcb.assessment.service;

import com.mcb.assessment.model.Mark;
import com.mcb.assessment.model.Student;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface StudentService {
	Student addStudent (Student student);

	Student getByStudentId (long id);

	List<Student> findAll ();

	BigDecimal getMarksByStudentId (long id);

	Set<Mark> getListOfMarksByStudentId (long id);

	int getByTeacherId (long id);
}
