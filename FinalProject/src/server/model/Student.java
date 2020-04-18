package server.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String studentName;
	private int studentId;
	//private ArrayList<CourseOffering> offeringList;
	private ArrayList<Registration> studentRegList;
		
	/**
	 * Gets the registration list.
	 * @return the studentRegList
	 */
	public ArrayList<Registration> getStudentRegList() {
		return studentRegList;
	}

	public Student (String studentName, int studentId) {
		this.setStudentName(studentName);
		this.setStudentId(studentId);
		studentRegList = new ArrayList<Registration>();
	}

	public void addRegistration(Registration registration) {
		studentRegList.add(registration);
	}
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
	
	
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getStudentId() {
		return studentId;
	}

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
