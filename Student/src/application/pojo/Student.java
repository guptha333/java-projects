package application.pojo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student {
	private SimpleIntegerProperty studentId;
	private SimpleStringProperty firstName;
	private SimpleStringProperty lastName;
	private SimpleStringProperty fatherName;
	private SimpleStringProperty gender;
	private SimpleStringProperty phoneNumber;
	private SimpleStringProperty email;
	private SimpleStringProperty bloodGroup;
	private SimpleStringProperty dateOfBirth;
	private SimpleStringProperty address;
	private StudentDetail studentDetail = new StudentDetail();

	public int getStudentId() {
		return studentId.get();
	}

	public void setStudentId(int studentId) {
		this.studentId = new SimpleIntegerProperty(studentId);
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName = new SimpleStringProperty(firstName);
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName = new SimpleStringProperty(lastName);
	}

	public String getFatherName() {
		return fatherName.get();
	}

	public void setFatherName(String fatherName) {
		this.fatherName = new SimpleStringProperty(fatherName);
	}

	public String getGender() {
		return gender.get();
	}

	public void setGender(String gender) {
		this.gender = new SimpleStringProperty(gender);
	}

	public String getPhoneNumber() {
		return phoneNumber.get();
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email = new SimpleStringProperty(email);
	}

	public String getBloodGroup() {
		return bloodGroup.get();
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = new SimpleStringProperty(bloodGroup);
	}

	public String getDateOfBirth() {
		return dateOfBirth.get();
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = new SimpleStringProperty(dateOfBirth);
	}

	public String getAddress() {
		return address.get();
	}

	public void setAddress(String address) {
		this.address = new SimpleStringProperty(address);
	}

	public StudentDetail getStudentDetail() {
		return studentDetail;
	}

	public void setStudentDetail(StudentDetail studentDetail) {
		this.studentDetail = studentDetail;
	}

	@Override
	public String toString() {
		return "StudentBasicInfo [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", fatherName=" + fatherName + ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", email="
				+ email + ", bloodGroup=" + bloodGroup + ", dob=" + dateOfBirth + ", address=" + address
				+ ", additionalInfo=" + studentDetail + "]";
	}

}
