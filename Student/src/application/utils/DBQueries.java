package application.utils;

public class DBQueries {
	public static final String SAVESTUDENT = "insert into tbl_student(firstName,"
			+ "lastName, fatherName, gender, phoneNumber, email, bloodGroup, dateOfBirth, address) "
			+ "values(?,?,?,?,?,?,?,?,?)";

	public static final String GETSTUDENTSLIST = "select studentId ,firstName ,lastName, fatherName, gender, phoneNumber,"
			+ " email, bloodGroup, dateOfBirth, address ,studentId, aboutStudent, sscInstitute, interInstitute, graduationInstitute, "
			+ "postInstitute, sscPercentage, interPercentage, graduationPercentage, postPercentage, sscYearPassing, "
			+ "interYearPassing, graduationYearPassing, postYearPassing, courseId "
			+ "FROM (tbl_student INNER JOIN tbl_studentDetail USING(studentId))";

	public static final String SAVESTUDENTDETAIL = "insert into tbl_studentDetail( aboutStudent, sscInstitute,"
			+ " interInstitute, graduationInstitute, postInstitute, sscPercentage, interPercentage, graduationPercentage,"
			+ " postPercentage, sscYearPassing, interYearPassing, graduationYearPassing, postYearPassing, courseId )"
			+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

}