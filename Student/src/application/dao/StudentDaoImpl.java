package application.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.main.Main;
import application.pojo.Student;
import application.pojo.StudentDetail;
import application.utils.DBConnection;
import application.utils.DBQueries;

public class StudentDaoImpl implements StudentDao {

	private Connection connection = null;

	private PreparedStatement preparedStatement = null;

	public int saveStudent(Student student) {
		connection = DBConnection.open();
		try {
			preparedStatement = connection.prepareStatement(DBQueries.SAVESTUDENT);
			preparedStatement.setString(1, student.getFirstName());
			preparedStatement.setString(2, student.getLastName());
			preparedStatement.setString(3, student.getFatherName());
			preparedStatement.setString(4, student.getGender());
			preparedStatement.setString(5, student.getPhoneNumber());
			preparedStatement.setString(6, student.getEmail());
			preparedStatement.setString(7, student.getBloodGroup());
			preparedStatement.setDate(8, Date.valueOf(student.getDateOfBirth()));
			preparedStatement.setString(9, student.getAddress());
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			Main.writeToLog("Dao Save Student Method !!", e);
		} finally {
			DBConnection.close();
		}
		return 0;
	}

	public int saveStudentDetail(StudentDetail studentDetail) {
		connection = DBConnection.open();
		try {
			preparedStatement = connection.prepareStatement(DBQueries.SAVESTUDENTDETAIL);
			preparedStatement.setString(1, studentDetail.getAboutStudent());
			preparedStatement.setString(2, studentDetail.getSscInstitute());
			preparedStatement.setString(3, studentDetail.getInterInstitute());
			preparedStatement.setString(4, studentDetail.getGraduationInstitute());
			preparedStatement.setString(5, studentDetail.getPostInstitute());
			preparedStatement.setDouble(6, studentDetail.getSscPercentage());
			preparedStatement.setDouble(7, studentDetail.getInterPercentage());
			preparedStatement.setDouble(8, studentDetail.getGraduationPercentage());
			preparedStatement.setDouble(9, studentDetail.getPostPercentage());
			preparedStatement.setInt(10, studentDetail.getSscYearPassing());
			preparedStatement.setInt(11, studentDetail.getInterYearPassing());
			preparedStatement.setInt(12, studentDetail.getGraduationYearPassing());
			preparedStatement.setInt(13, studentDetail.getPostYearPassing());
			preparedStatement.setInt(14, studentDetail.getCourseId());
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			Main.writeToLog("Dao Save Student Detail Method !!", e);
		} finally {
			DBConnection.close();
		}
		return 0;
	}

	@Override
	public List<Student> getStudentsList() {
		connection = DBConnection.open();
		List<Student> list = null;
		try {
			preparedStatement = connection.prepareStatement(DBQueries.GETSTUDENTSLIST);
			ResultSet rs = preparedStatement.executeQuery();
			list = new ArrayList<>();
			Student student = null;
			while (rs.next()) {
				student = new Student();
				student.setStudentId(rs.getInt(1));
				student.setFirstName(rs.getString(2));
				student.setLastName(rs.getString(3));
				student.setFatherName(rs.getString(4));
				student.setGender(rs.getString(5));
				student.setPhoneNumber(rs.getString(6));
				student.setEmail(rs.getString(7));
				student.setBloodGroup(rs.getString(8));
				student.setDateOfBirth(rs.getDate(9).toString());
				student.setAddress(rs.getString(10));

				student.getStudentDetail().setStudentId(11);
				student.getStudentDetail().setAboutStudent(rs.getString(12));
				student.getStudentDetail().setSscInstitute(rs.getString(13));
				student.getStudentDetail().setInterInstitute(rs.getString(14));
				student.getStudentDetail().setGraduationInstitute(rs.getString(15));
				student.getStudentDetail().setPostInstitute(rs.getString(16));
				student.getStudentDetail().setSscPercentage(rs.getDouble(17));
				student.getStudentDetail().setInterPercentage(rs.getDouble(18));
				student.getStudentDetail().setGraduationPercentage(rs.getDouble(19));
				student.getStudentDetail().setPostPercentage(rs.getDouble(19));
				student.getStudentDetail().setSscYearPassing(rs.getInt(21));
				student.getStudentDetail().setInterYearPassing(rs.getInt(22));
				student.getStudentDetail().setGraduationYearPassing(rs.getInt(23));
				student.getStudentDetail().setPostYearPassing(rs.getInt(24));
				student.getStudentDetail().setCourseId(rs.getInt(25));
				list.add(student);
			}
			return list;
		} catch (SQLException e) {
			Main.writeToLog("Dao Get Students List Method !!", e);
		} finally {
			DBConnection.close();
		}
		return list;
	}
}
