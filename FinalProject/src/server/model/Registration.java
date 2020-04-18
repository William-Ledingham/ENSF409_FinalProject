package server.model;

import java.io.Serializable;
/**
 * An object to keep track of which students are registered in which course offerings.
 * @author Michaela Gartner
 * @version 1.0
 * @since 12-05-2020
 *
 */
public class Registration implements Serializable {
	/**
	 * The object serialization ID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The student that is to be registered.
	 */
	private Student theStudent;
	/**
	 * The offering the student is registering in.
	 */
	private CourseOffering theOffering;
	/**
	 * The mark the student has received in the course.
	 */
	private char grade;
	/**
	 * Creates a new registration for the specified student in the specified offering.
	 * @param student the student to be registered
	 * @param offering the offering the student is registering in
	 */
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
	/**
	 * Deletes a registration from the student and the offering (all places that store the registration).
	 */
	public void deleteRegistration()
	{
		theStudent.deleteRegistration(this);
		theOffering.deleteRegistration(this);
	}
	/**
	 * Gets the student in the registration.
	 * @return the student
	 */
	public Student getTheStudent() {
		return theStudent;
	}
	/**
	 * Sets the student in the registration.
	 * @param theStudent the student 
	 */
	public void setTheStudent(Student theStudent) {
		this.theStudent = theStudent;
	}
	/**
	 * Gets the course offering that the student is registered in.
	 * @return the course offering
	 */
	public CourseOffering getTheOffering() {
		return theOffering;
	}
	/**
	 * Sets the course offering that the student is registered in.
	 * @param theOffering the course offering
	 */
	public void setTheOffering(CourseOffering theOffering) {
		this.theOffering = theOffering;
	}
	/**
	 * Gets the grade of the student in this offering.
	 * @return the grade
	 */
	public char getGrade() {
		return grade;
	}
	/**
	 * Sets the grade of the student in this offering
	 * @param grade the grade
	 */
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
