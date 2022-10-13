package application.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;

import application.domain.StudentConstants;
import application.domain.StudentEnum;
import application.helper.Helper;
import application.main.Main;
import application.pojo.Course;
import application.pojo.Student;
import application.pojo.StudentDetail;
import application.service.StudentServiceImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class MainController implements Initializable {
	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private TextField fatherName;
	@FXML
	private ToggleGroup gender;
	@FXML
	private TextField phoneNumber;
	@FXML
	private TextField email;
	@FXML
	private ChoiceBox<String> bloodGroupChoiceBox;
	private ObservableList<String> bloodGroupsList = FXCollections.observableArrayList();
	@FXML
	private DatePicker dateOfBirth;
	@FXML
	private TextArea address;
	@FXML
	private Label studentFormStatus;
	@FXML
	private TextField sscInstitute;
	@FXML
	private TextField interInstitute;
	@FXML
	private TextField graduationInstitute;
	@FXML
	private TextField postInstitute;
	@FXML
	private TextField sscPercentage;
	@FXML
	private TextField interPercentage;
	@FXML
	private TextField graduationPercentage;
	@FXML
	private TextField postPercentage;
	@FXML
	private TextField sscYearPassing;
	@FXML
	private TextField interYearPassing;
	@FXML
	private TextField graduationYearPassing;
	@FXML
	private TextField postYearPassing;
	@FXML
	private Label academicStatus;
	@FXML
	private TextArea aboutStudent;
	@FXML
	private Label introStatus;
	@FXML
	private ComboBox<String> courses;
	@FXML
	private Label courseDetails;
	private ObservableList<String> coursesNames = FXCollections.observableArrayList();
	private ObservableList<Course> coursesList = FXCollections.observableArrayList();
	@FXML
	private Label courseStatus;

	@FXML
	private TableView<Student> stdInfoTable;
	@FXML
	private TableColumn<Student, String> colFirstName;
	@FXML
	private TableColumn<Student, String> colGender;
	@FXML
	private TableColumn<Student, String> colPhoneNumber;
	@FXML
	private TableColumn<Student, String> colBloodGroup;
	@FXML
	private TableColumn<Student, String> colDateOfBirth;
	@FXML
	private TableColumn<Student, String> colAddress;
	@FXML
	private TableColumn<Course, String> colCourseName;
	@FXML
	private TableColumn<Student, Integer> colId;
	@FXML
	private TextField filteredField;
	@FXML
	private ListView<String> listView;
	private Course courseSelected;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		initializeValues();

		ObservableList<Student> studentsList = FXCollections.observableArrayList();
		studentsList.addAll((new StudentServiceImp()).getStudentsList());

		colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		colBloodGroup.setCellValueFactory(new PropertyValueFactory<>("bloodGroup"));
		colDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dob"));
		colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
		colId.setCellValueFactory(new PropertyValueFactory<>("studentId"));

		stdInfoTable.setItems(studentsList);

		filteredField.textProperty().addListener((obs, oldValue, newValue) -> {
			stdInfoTable.setVisible(true);
			FilteredList<Student> filteredData = new FilteredList<>(FXCollections.observableArrayList(studentsList));
			filteredData.setPredicate(student -> {
				if (newValue == null || newValue.isEmpty()) {
					stdInfoTable.setVisible(false);
					return true;
				}
				String filteredNewValue = newValue.toLowerCase();
				return (student.getFirstName().toLowerCase().contains(filteredNewValue)
						|| student.getGender().toLowerCase().contains(filteredNewValue)
						|| student.getPhoneNumber().contains(filteredNewValue)
						|| student.getAddress().toLowerCase().contains(filteredNewValue)
						|| student.getBloodGroup().toLowerCase().contains(filteredNewValue));
			});
			stdInfoTable.setItems(filteredData);
		});

		stdInfoTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
			if (stdInfoTable.getSelectionModel().getSelectedItem() != null) {
				listView.getItems().clear();
				listView.setVisible(true);
				Student student = stdInfoTable.getSelectionModel().getSelectedItem();
				listView.getItems().add("Student ID : " + Integer.toString(student.getStudentId()));
				listView.getItems().add("First Name : " + student.getFirstName());
				listView.getItems().add("Last Name: " + student.getLastName());
				listView.getItems().add("Father Name : " + student.getFatherName());
				listView.getItems().add("Gender :" + student.getGender());
				listView.getItems().add("Phone Number : " + student.getPhoneNumber());
				listView.getItems().add("Email : " + student.getEmail());
				listView.getItems().add("Blood Group : " + student.getBloodGroup());
				listView.getItems().add("Date Of Birth : " + student.getDateOfBirth());
				listView.getItems().add("Address : " + student.getAddress());

				Course filteredCourse = coursesList.filtered(courseTemp -> {
					int courseId = student.getStudentDetail().getCourseId();
					return (courseId == courseTemp.getCourseId());
				}).get(0);
				listView.getItems().add("Course Name :" + filteredCourse.getCourseName());
				listView.getItems().add("Course Fee :" + filteredCourse.getCourseFee());
				listView.getItems().add("Course Duration :" + filteredCourse.getCourseDuration());

				listView.getItems().add("About : " + student.getStudentDetail().getAboutStudent());
				listView.getItems().add("SSC From : " + student.getStudentDetail().getSscInstitute());
				listView.getItems().add("SSC Percentage : " + student.getStudentDetail().getSscPercentage());
				listView.getItems().add("SSC Year Passing : " + student.getStudentDetail().getSscYearPassing());

				listView.getItems().add("Inter/Diploma From  :" + student.getStudentDetail().getInterInstitute());
				listView.getItems()
						.add("Inter/Diploma Percentage  :" + student.getStudentDetail().getInterPercentage());
				listView.getItems()
						.add("Inter/Diploma Year Passing  :" + student.getStudentDetail().getInterYearPassing());

				listView.getItems().add("Graduated From :" + student.getStudentDetail().getGraduationInstitute());
				listView.getItems()
						.add("Graduation Percentage :" + student.getStudentDetail().getGraduationPercentage());
				listView.getItems().add("Graduated Year :" + student.getStudentDetail().getGraduationYearPassing());

				listView.getItems().add("Post Graduated From :" + student.getStudentDetail().getPostInstitute());
				listView.getItems()
						.add("Post Graduation Percentage :" + student.getStudentDetail().getPostPercentage());
				listView.getItems().add("Post Graduated Year :" + student.getStudentDetail().getPostYearPassing());
			}
		});

		textPropertyStudent1Listeners();
		textPropertyStudentDetail1Listeners();
		focusPropertyStudent1Listeners();
		focusPropertyStudentDetail1Listeners();

		dateOfBirth.setDayCellFactory(param -> new DateCell() {
			@Override
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				setDisable(empty || date.compareTo(LocalDate.now().minusYears(10)) > 0);
			}
		});
		dateOfBirth.setValue(LocalDate.now().minusYears(10));

	}

	public void initializeValues() {
		Arrays.asList(StudentEnum.BloodGroupsListEnum.values())
				.forEach(bloodGroup -> bloodGroupsList.add(bloodGroup.toString()));
		bloodGroupChoiceBox.setItems(bloodGroupsList);
		coursesList.addAll(new Course(401, "C", 5000, 30), new Course(402, "C++", 6000, 30),
				new Course(403, "Java", 7000, 45), new Course(404, "Python", 8000, 45),
				new Course(405, "React", 9000, 45), new Course(406, "Spring", 10000, 45));
		for (Course course : coursesList) {
			coursesNames.add(course.getCourseName());
		}
		courses.setItems(coursesNames);
	}

	private void focusPropertyStudentDetail1Listeners() {
		sscInstitute.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (Boolean.FALSE.equals(newValue))
				validateSchoolName(sscInstitute.getText());
		});
		interInstitute.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (Boolean.FALSE.equals(newValue))
				validateSchoolName(interInstitute.getText());
		});
		graduationInstitute.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (Boolean.FALSE.equals(newValue))
				validateSchoolName(graduationInstitute.getText());
		});
		postInstitute.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (Boolean.FALSE.equals(newValue))
				validateSchoolName(postInstitute.getText());
		});
		sscPercentage.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (Boolean.FALSE.equals(newValue))
				validatePercent(sscPercentage.getText());
		});
		interPercentage.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (Boolean.FALSE.equals(newValue))
				validatePercent(interPercentage.getText());
		});
		graduationPercentage.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (Boolean.FALSE.equals(newValue))
				validatePercent(graduationPercentage.getText());
		});
		focusPropertyStudentDetail2Listeners();
	}

	private void focusPropertyStudentDetail2Listeners() {
		postPercentage.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (Boolean.FALSE.equals(newValue))
				validatePercent(postPercentage.getText());
		});
		sscYearPassing.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (Boolean.FALSE.equals(newValue))
				validateYearOfPassing(sscYearPassing.getText());
		});
		interYearPassing.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (Boolean.FALSE.equals(newValue))
				validateYearOfPassing(interYearPassing.getText());
		});
		graduationYearPassing.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (Boolean.FALSE.equals(newValue))
				validateYearOfPassing(graduationYearPassing.getText());
		});
		postYearPassing.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (Boolean.FALSE.equals(newValue))
				validateYearOfPassing(postYearPassing.getText());
		});
		aboutStudent.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (Boolean.FALSE.equals(newValue))
				checkAboutStudentLength();
		});
	}

	private void focusPropertyStudent1Listeners() {
		firstName.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (Boolean.FALSE.equals(newValue))
				validateFirstName();
		});
		lastName.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (Boolean.FALSE.equals(newValue))
				validateLastName();
		});
		fatherName.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (Boolean.FALSE.equals(newValue))
				validateFatherName();
		});
		phoneNumber.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (Boolean.FALSE.equals(newValue))
				validatePhoneNumber();
		});
		email.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (Boolean.FALSE.equals(newValue))
				validateEmail();
		});
		address.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (Boolean.FALSE.equals(newValue))
				validateAddress();
		});
	}

	private void textPropertyStudent1Listeners() {
		firstName.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue.length() > 20 || !newValue.matches(StudentConstants.ALPHABETS)) {
				Helper.showAlert(AlertType.ERROR, StudentConstants.FORMERROR, StudentConstants.ALPHABETALERTMSG);
				firstName.setText(oldValue);
			}
		});
		lastName.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue.length() > 20 || !newValue.matches(StudentConstants.ALPHABETS)) {
				Helper.showAlert(AlertType.ERROR, StudentConstants.FORMERROR, StudentConstants.ALPHABETALERTMSG);
				lastName.setText(oldValue);
			}
		});
		fatherName.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue.length() > 20 || !newValue.matches(StudentConstants.ALPHABETS)) {
				Helper.showAlert(AlertType.ERROR, StudentConstants.FORMERROR, StudentConstants.ALPHABETALERTMSG);
				fatherName.setText(oldValue);
			}
		});
		textPropertyStudent2Listeners();
	}

	private void textPropertyStudent2Listeners() {
		phoneNumber.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue.length() > 10 || !newValue.matches(StudentConstants.NUMBERS)) {
				Helper.showAlert(AlertType.ERROR, StudentConstants.FORMERROR, "Only Numbers are allowed ");
				phoneNumber.setText(oldValue);
			}
		});
		email.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue.length() > 30 || !newValue.matches("^[a-zA-Z0-9@._-]*$")) {
				email.setText(oldValue);
			}
		});
		address.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue.length() > 50 || !newValue.matches("^[a-zA-Z0-9-/\\:., ]*$")) {
				address.setText(oldValue);
			}
		});
	}

	private void textPropertyStudentDetail1Listeners() {
		aboutStudent.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue.length() > 300) {
				aboutStudent.setText(oldValue);
			}
		});
		sscInstitute.textProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue.matches(StudentConstants.INSTITUTENAMEREGEX) || newValue.length() > 30)
				sscInstitute.setText(oldValue);
		});
		interInstitute.textProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue.matches(StudentConstants.INSTITUTENAMEREGEX) || newValue.length() > 30)
				interInstitute.setText(oldValue);
		});
		graduationInstitute.textProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue.matches(StudentConstants.INSTITUTENAMEREGEX) || newValue.length() > 30)
				graduationInstitute.setText(oldValue);
		});
		postInstitute.textProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue.matches(StudentConstants.INSTITUTENAMEREGEX) || newValue.length() > 30)
				postInstitute.setText(oldValue);
		});
		textPropertyStudentDetail2Listeners();
		textPropertyStudentDetail3Listeners();
	}

	private void textPropertyStudentDetail2Listeners() {
		sscPercentage.textProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue.matches(StudentConstants.PERCENTAGEREGEX) || newValue.length() > 5)
				sscPercentage.setText(oldValue);
		});
		interPercentage.textProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue.matches(StudentConstants.PERCENTAGEREGEX) || newValue.length() > 5)
				interPercentage.setText(oldValue);
		});
		graduationPercentage.textProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue.matches(StudentConstants.PERCENTAGEREGEX) || newValue.length() > 5)
				graduationPercentage.setText(oldValue);
		});
		postPercentage.textProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue.matches(StudentConstants.PERCENTAGEREGEX) || newValue.length() > 5)
				postPercentage.setText(oldValue);
		});

	}

	private void textPropertyStudentDetail3Listeners() {
		sscYearPassing.textProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue.matches(StudentConstants.NUMBERS) || newValue.length() > 4)
				sscYearPassing.setText(oldValue);
		});
		interYearPassing.textProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue.matches(StudentConstants.NUMBERS) || newValue.length() > 4)
				interYearPassing.setText(oldValue);
		});
		graduationYearPassing.textProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue.matches(StudentConstants.NUMBERS) || newValue.length() > 4)
				graduationYearPassing.setText(oldValue);
		});
		postYearPassing.textProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue.matches(StudentConstants.NUMBERS) || newValue.length() > 4)
				postYearPassing.setText(oldValue);
		});
	}

	private void validateYearOfPassing(String yearPassing) {
		if (!yearPassing.isEmpty() && !yearPassing.matches("^\\d{4}+$")) {
			academicStatus.setText("Enter Valid Year Passing");
			return;
		}
		academicStatus.setText("");
	}

	private void validatePercent(String percent) {
		if (!percent.isEmpty() && !percent.matches(StudentConstants.PERCENTAGEREGEX)) {
			academicStatus.setText("Enter Valid Percent");
			return;
		}
		academicStatus.setText("");
	}

	private void validateSchoolName(String schoolName) {
		if (!schoolName.isEmpty() && !schoolName.matches("^[a-z A-Z]*$")) {
			academicStatus.setText("Enter Valid School Name");
			return;
		}
		academicStatus.setText("");
	}

	public void validateFirstName() {
		Main.writeToLog("First Name : " + firstName.getText());
		if (firstName.getText().isEmpty()) {
			studentFormStatus.setText("Enter First Name");
		} else if (!firstName.getText().matches(StudentConstants.ALPHABETS)) {
			studentFormStatus.setText("Enter Valid First Name");
		} else {
			studentFormStatus.setText("");
		}
	}

	public void validateLastName() {
		Main.writeToLog("Last Name : " + lastName.getText());
		if (!lastName.getText().matches(StudentConstants.ALPHABETS)) {
			studentFormStatus.setText("Enter Valid Last Name");
		} else {
			studentFormStatus.setText("");
		}
	}

	public void validateFatherName() {
		Main.writeToLog("Father Name : " + fatherName.getText());
		if (!fatherName.getText().matches(StudentConstants.ALPHABETS)) {
			studentFormStatus.setText("Enter Valid Father Name");
		} else {
			studentFormStatus.setText("");
		}
	}

	private void validateGender() {
		if (gender.getSelectedToggle() == null) {
			studentFormStatus.setText("Select Gender");
			return;
		}
		Main.writeToLog("Selected Gender : " + ((Labeled) gender.getSelectedToggle()).getText());
		studentFormStatus.setText("");
	}

	public void validatePhoneNumber() {
		Main.writeToLog("Phone Number : " + phoneNumber.getText());
		if (phoneNumber.getText().isEmpty()) {
			studentFormStatus.setText("Enter Phone Number");
		} else if (!phoneNumber.getText().matches(StudentConstants.NUMBERS)) {
			studentFormStatus.setText("Enter Valid Phone Number");
		} else {
			studentFormStatus.setText("");
		}
	}

	public void validateEmail() {
		Main.writeToLog("Email: " + email.getText());
		if (email.getText().isEmpty()) {
			studentFormStatus.setText("");
			return;
		}
		if (!email.getText().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
			studentFormStatus.setText("Enter Valid Email Address ");
		} else {
			studentFormStatus.setText("");
		}
	}

	private void validateDateOfBirth() {
		Main.writeToLog("Date Of Birth : " + dateOfBirth.getValue());
		if (dateOfBirth.getValue() == null) {
			studentFormStatus.setText("Select Date Of Birth");
		} else if (LocalDate.now().minusYears(10).isBefore(dateOfBirth.getValue())) {
			studentFormStatus.setText("Select Valid Date Of Birth");
		} else {
			studentFormStatus.setText("");
		}
	}

	private void validateBloodGroup() {
		if (bloodGroupChoiceBox.getSelectionModel().getSelectedItem() == null) {
			studentFormStatus.setText("Select Blood Group");
			return;
		}
		Main.writeToLog(" Blood Group " + bloodGroupChoiceBox.getSelectionModel().getSelectedItem());
		studentFormStatus.setText("");
	}

	private void validateAddress() {
		studentFormStatus.setText(address.getText().isEmpty() ? "Enter Address " : "");
	}

	public void checkAboutStudentLength() {
		if (aboutStudent.getText().isEmpty()) {
			introStatus.setText("Enter About YourSelf!!!");
			return;
		} else if (aboutStudent.getText().trim().split(" ").length > StudentConstants.MAX_LENGTH) {
			introStatus.setText("Words Exceeded Minimize the Introduction ");
			return;
		}
		introStatus.setText("");
		Main.writeToLog("Text : " + aboutStudent.getText());
	}

	public void courseSelect(ActionEvent event) {
		Main.writeToLog(courses.getValue());
		String courseSelectedName = courses.getValue();
		courseSelected = coursesList.filtered(course -> course.getCourseName().equals(courseSelectedName)).get(0);
		courseDetails.setText("Course Name : " + courseSelected.getCourseName() + " \nCourse Fee : "
				+ courseSelected.getCourseFee() + "\nCourseDuration " + courseSelected.getCourseDuration());
	}

	public void onSubmit(ActionEvent event) {
		StudentServiceImp studentService = new StudentServiceImp();
		if (!studentFormStatus.getText().isEmpty()) {
			Helper.showAlert(AlertType.ERROR, StudentConstants.FORMERROR, studentFormStatus.getText());
			return;
		}
		validateFirstName();
		if (!studentFormStatus.getText().isEmpty()) {
			Helper.showAlert(AlertType.ERROR, StudentConstants.FORMERROR, studentFormStatus.getText());
			return;
		}
		validateLastName();
		if (!studentFormStatus.getText().isEmpty()) {
			Helper.showAlert(AlertType.ERROR, StudentConstants.FORMERROR, studentFormStatus.getText());
			return;
		}
		validateFatherName();
		if (!studentFormStatus.getText().isEmpty()) {
			Helper.showAlert(AlertType.ERROR, StudentConstants.FORMERROR, studentFormStatus.getText());
			return;
		}
		validateGender();
		if (!studentFormStatus.getText().isEmpty()) {
			Helper.showAlert(AlertType.ERROR, StudentConstants.FORMERROR, studentFormStatus.getText());
			return;
		}
		validatePhoneNumber();
		if (!studentFormStatus.getText().isEmpty()) {
			Helper.showAlert(AlertType.ERROR, StudentConstants.FORMERROR, studentFormStatus.getText());
			return;
		}
		validateEmail();
		if (!studentFormStatus.getText().isEmpty()) {
			Helper.showAlert(AlertType.ERROR, StudentConstants.FORMERROR, studentFormStatus.getText());
			return;
		}
		validateBloodGroup();
		if (!studentFormStatus.getText().isEmpty()) {
			Helper.showAlert(AlertType.ERROR, StudentConstants.FORMERROR, studentFormStatus.getText());
			return;
		}
		validateDateOfBirth();
		if (!studentFormStatus.getText().isEmpty()) {
			Helper.showAlert(AlertType.ERROR, StudentConstants.FORMERROR, studentFormStatus.getText());
			return;
		}
		validateAddress();
		if (!studentFormStatus.getText().isEmpty()) {
			Helper.showAlert(AlertType.ERROR, StudentConstants.FORMERROR, studentFormStatus.getText());
			return;
		}
		checkAboutStudentLength();
		if (courses.getValue() == null) {
			courseStatus.setText("select Course");
			Helper.showAlert(AlertType.ERROR, StudentConstants.FORMERROR, courseStatus.getText());
			return;
		}
		if (!studentFormStatus.getText().isEmpty()) {
			Helper.showAlert(AlertType.ERROR, StudentConstants.FORMERROR, studentFormStatus.getText());
			return;
		}
		StudentDetail studentDetail = new StudentDetail();
		studentDetail.setAboutStudent(aboutStudent.getText());
		studentDetail.setSscInstitute(sscInstitute.getText());
		studentDetail.setInterInstitute(interInstitute.getText());
		studentDetail.setGraduationInstitute(graduationInstitute.getText());
		studentDetail.setPostInstitute(postInstitute.getText());
		studentDetail
				.setSscPercentage(sscPercentage.getText().isEmpty() ? 0 : Double.parseDouble(sscPercentage.getText()));
		studentDetail.setInterPercentage(
				interPercentage.getText().isEmpty() ? 0 : Double.parseDouble(interPercentage.getText()));
		studentDetail.setGraduationPercentage(
				graduationPercentage.getText().isEmpty() ? 0 : Double.parseDouble(graduationPercentage.getText()));
		studentDetail.setPostPercentage(
				postPercentage.getText().isEmpty() ? 0 : Double.parseDouble(postPercentage.getText()));
		studentDetail
				.setSscYearPassing(sscYearPassing.getText().isEmpty() ? 0 : Integer.parseInt(sscYearPassing.getText()));
		studentDetail.setInterYearPassing(
				interYearPassing.getText().isEmpty() ? 0 : Integer.parseInt(interYearPassing.getText()));
		studentDetail.setGraduationYearPassing(
				graduationYearPassing.getText().isEmpty() ? 0 : Integer.parseInt(graduationYearPassing.getText()));
		studentDetail.setPostYearPassing(
				postYearPassing.getText().isEmpty() ? 0 : Integer.parseInt(postYearPassing.getText()));
		studentDetail.setCourseId(courseSelected.getCourseId());

		Student student = new Student();
		student.setFirstName(firstName.getText());
		student.setLastName(lastName.getText());
		student.setFatherName(fatherName.getText());
		student.setGender(((Labeled) gender.getSelectedToggle()).getText());
		student.setPhoneNumber(phoneNumber.getText());
		student.setEmail(email.getText());
		student.setBloodGroup(bloodGroupChoiceBox.getSelectionModel().getSelectedItem());
		student.setDateOfBirth(dateOfBirth.getValue().toString());
		student.setAddress(address.getText());
		student.setStudentDetail(studentDetail);

		if (studentService.saveStudent(student) < 0) {
			Helper.showAlert(AlertType.ERROR, StudentConstants.FORMERROR, "Re-verify all details");
			return;
		}
		Helper.showAlert(AlertType.CONFIRMATION, "Success", "Data Saved Sucessfully!!!! ");
	}
}
