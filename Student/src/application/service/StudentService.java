package application.service;

import java.util.List;

import application.pojo.Student;

public interface StudentService {
	int saveStudent(Student student);

	List<Student> getStudentsList();
}
