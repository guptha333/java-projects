package application.service;

import java.util.List;

import application.dao.StudentDaoImpl;
import application.exceptions.StudentDataRequiredException;
import application.pojo.Student;

public class StudentServiceImp implements StudentService {

	private StudentDaoImpl studentDao;

	public int saveStudent(Student student) {
		studentDao = new StudentDaoImpl();
		if (student.getFirstName().isEmpty() || student.getPhoneNumber().isEmpty() || student.getAddress().isEmpty()
				|| student.getBloodGroup().isEmpty() || student.getDateOfBirth().isEmpty()
				|| student.getStudentDetail().getCourseId() == 0
				|| student.getStudentDetail().getAboutStudent().isEmpty()) {
			throw new StudentDataRequiredException();
		}
		int result = studentDao.saveStudent(student);
		studentDao.saveStudentDetail(student.getStudentDetail());
		return result;
	}

	@Override
	public List<Student> getStudentsList() {
		studentDao = new StudentDaoImpl();
		return studentDao.getStudentsList();
	}
}
