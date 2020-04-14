package shared.model;

import java.io.Serializable;

public class Registration implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Student theStudent;
	private CourseOffering theOffering;
	private char grade;
	
	public Registration (Student student, CourseOffering offering) {
		theStudent = student;
		theOffering = offering;
	}

	/**
	 * Adds this registration to the student and to the offering (all places that store the registration).
	 */
	public void addRegistration () {
		theStudent.addRegistration(this);
		theOffering.addRegistration(this);
	}
	
	public void deleteRegistration()
	{
		theStudent.deleteRegistration(this);
		theOffering.deleteRegistration(this);
	}
	public Student getTheStudent() {
		return theStudent;
	}
	public void setTheStudent(Student theStudent) {
		this.theStudent = theStudent;
	}
	public CourseOffering getTheOffering() {
		return theOffering;
	}
	public void setTheOffering(CourseOffering theOffering) {
		this.theOffering = theOffering;
	}
	public char getGrade() {
		return grade;
	}
	public void setGrade(char grade) {
		this.grade = grade;
	}
	
	@Override
	public String toString () {
		String st = "\n";
		st += "Student Name: " + getTheStudent() + "\n";
		st += "The Offering: " + getTheOffering () + "\n";
		st += "Grade: " + getGrade();
		st += "\n-----------\n";
		return st;
		
	}
	

}
