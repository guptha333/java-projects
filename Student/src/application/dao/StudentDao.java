package application.dao;

import application.pojo.Student;

import java.util.List;

import application.pojo.StudentDetail;

public interface StudentDao {
	/**
	 * @param Student object
	 * @return success(>0) or failure (0)
	 */
	int saveStudent(Student student);

	/**
	 * @return list of students data from the database
	 */
	List<Student> getStudentsList();

	/**
	 * @param studentDetail object
	 * @return success(>0) or failure (0)
	 */
	int saveStudentDetail(StudentDetail studentDetail);
}
