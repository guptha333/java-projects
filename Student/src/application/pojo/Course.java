package application.pojo;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Course {
	private SimpleIntegerProperty courseId;
	private SimpleStringProperty courseName;
	private SimpleDoubleProperty courseFee;
	private SimpleIntegerProperty courseDuration;

	public Course(int courseId, String courseName, double courseFee, int courseDuration) {
		super();
		this.courseId = new SimpleIntegerProperty(courseId);
		this.courseName = new SimpleStringProperty(courseName);
		this.courseFee = new SimpleDoubleProperty(courseFee);
		this.courseDuration = new SimpleIntegerProperty(courseDuration);
	}

	public int getCourseId() {
		return courseId.get();
	}

	public String getCourseName() {
		return courseName.get();
	}

	public double getCourseFee() {
		return courseFee.get();
	}

	public int getCourseDuration() {
		return courseDuration.get();
	}
}
