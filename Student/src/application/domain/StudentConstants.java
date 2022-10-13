package application.domain;

public class StudentConstants {
	private StudentConstants() {
	}

	public static final String PERCENTAGEREGEX = "^[0-9.]*$";
	public static final String INSTITUTENAMEREGEX = "^[a-zA-Z/\\ ]*$";
	public static final String NUMBERS = "^\\d*$";
	public static final String ALPHABETS = "^[a-zA-Z ]*$";
	public static final int MAX_LENGTH = 50;
	public static final String FORMERROR = "Form Error";
	public static final String ALPHABETALERTMSG = "Only Alphabets are allowed!!!";
}
