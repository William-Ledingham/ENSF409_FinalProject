package server.model;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Keeps track of the students information
 * @author William Ledingham
 * @version 1.0
 * @since 12-05-2020
 *
 */
public class Student implements Serializable {	
	/**
	 * The object serialization ID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The name of the student
	 */
	private String studentName;
	/**
	 * The students ID.
	 */
	private int studentId;
	/**
	 * The list of registrations for the student.
	 */
	private ArrayList<Registration> studentRegList;		
	/**
	 * Gets the registration list.
	 * @return the studentRegList
	 */
	public ArrayList<Registration> getStudentRegList() {
		return studentRegList;
	}
	/**
	 * Creates a new student with the given name and student ID.
	 * @param studentName the name of the student
	 * @param studentId the students ID.
	 */
	public Student (String studentName, int studentId) {
		this.setStudentName(studentName);
		this.setStudentId(studentId);
		studentRegList = new ArrayList<Registration>();
	}
	/**
	 * Adds a new registration to the students registration list.
	 * @param registration the registration to be added
	 */
	public void addRegistration(Registration registration) {
		studentRegList.add(registration);
	}
	/**
	 * Removes a registration from the student registration list for a given course.
	 * @param course the course that the registration contains
	 */
	public void deleteRegistration(Course course)
	{
		for(Registration reg : studentRegList)
		{
			if(reg.getTheOffering().getTheCourse() == course)
			{
				reg.deleteRegistration();
				break;
			}
		}
	}
	/**
	 * Removes a registration from the student registration list for a given registration.
	 * @param reg the registration to be removed
	 */
	public void deleteRegistration(Registration reg)
	{
		studentRegList.remove(reg);
	}
	
	/**
	 * Prints the course registration info for this student, including the student's name and ID as a reminder.
	 * @return String all of the student's registrations
	 */
	public String getAllCourseRegistrations()
	{
		String out = studentRegList.size() + " Registration(s) for " + studentName + " (ID = " + studentId + ")\n\n";
		
		for(Registration reg : studentRegList)
		{
			out += reg.getTheOffering();
			out += "\n";
		}
		if(studentRegList.size() == 0)
		{
			out += "\nStudent is not registered for any courses";
		}
		return out;
	}
	/**
	 * Prints all courses in the students registration list.
	 */
	public void printAllCourses()
	{
		for(Registration reg : studentRegList)
		{
			System.out.println(reg.getTheOffering());
		}
		if(studentRegList.size() == 0)
		{
			System.out.println("Student is not registered for any courses");
		}
	}
	/**
	 * Gets the name of the student.
	 * @return the name of the student
	 */
	public String getStudentName() {
		return studentName;
	}
	/**
	 * Sets the name of the student.
	 * @param studentName the name of the student
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	/**
	 * Gets the students ID
	 * @return the students ID
	 */
	public int getStudentId() {
		return studentId;
	}
	/**
	 * Sets the students ID.
	 * @param studentId the student ID
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	@Override
	public String toString () {
		String st = "Student Name: " + getStudentName() + "\n" +
				"Student Id: " + getStudentId() + "\n\n";
		return st;
	}

}
