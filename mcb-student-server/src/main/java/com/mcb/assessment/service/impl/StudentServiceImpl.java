package com.mcb.assessment.service.impl;

import com.mcb.assessment.exceptionhandler.StudentNotFound;
import com.mcb.assessment.exceptionhandler.TeacherNotFound;
import com.mcb.assessment.model.Mark;
import com.mcb.assessment.model.Student;
import com.mcb.assessment.model.Teacher;
import com.mcb.assessment.repository.MarkRepository;
import com.mcb.assessment.repository.StudentRepository;
import com.mcb.assessment.repository.TeacherRepository;
import com.mcb.assessment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	MarkRepository markRepository;

	@Autowired
	TeacherRepository teacherRepository;

	@Override
	public Student addStudent (Student student) {
		//  if(student.getGroup().getStudents())
		return studentRepository.save (student);
	}

	@Override
	public Student getByStudentId (long id) {
		studentRepository.findById (id).orElseThrow (() -> new StudentNotFound (id));
		return studentRepository.findById (id).get ();
	}

	@Override
	public List<Student> findAll () {
		return studentRepository.findAll ();
	}

	@Override
	public BigDecimal getMarksByStudentId (long id) {
		Set<Mark> marks = markRepository.findByStudentStudentId (id);
		Optional<BigDecimal> res = marks.stream ().map (m -> m.getMark ()).reduce ((m1, m2) -> m1.add (m2));
		res.orElseThrow (() -> new StudentNotFound (id));
		return res.get ();
	}

	@Override
	public Set<Mark> getListOfMarksByStudentId (long id) {
		return markRepository.findByStudentStudentId (id);
	}

	@Override
	public int getByTeacherId (long id) {
		Optional<Teacher> teacher = teacherRepository.findById (id);
		teacher.orElseThrow (() -> new TeacherNotFound (id));
		Teacher responseObj = teacher.get ();
		return responseObj.getGroup ().getStudents ().size ();
	}
}
