package application.exceptions;

public class StudentDataRequiredException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StudentDataRequiredException() {
		super("Student Data Required!!!!");
	}

}
