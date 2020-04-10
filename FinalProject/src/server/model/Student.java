package server.model;

import java.util.ArrayList;
import java.util.Scanner;

public class Student {
	
	private String studentName;
	private int studentId;
	//private ArrayList<CourseOffering> offeringList;
	private ArrayList<Registration> studentRegList;
	
	public Student (String studentName, int studentId) {
		this.setStudentName(studentName);
		this.setStudentId(studentId);
		studentRegList = new ArrayList<Registration>();
	}

	public void addRegistration(Registration registration) {
		// TODO Auto-generated method stub
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
	
	public void addCourse(Course course, int courseSection)
	{
		if(studentRegList.size() < 6)
		{
			Registration reg = new Registration();
			reg.completeRegistration(this, course.getCourseOfferingAt(courseSection));			
		}
		else
		{
			System.out.println("Student has 6 classes already.");
		}

	}
	
	public void userAddCourse(CourseCatalogue cat)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter course Name: ");
		String courseName = sc.nextLine();
		System.out.println("Enter course ID: ");
		int courseID = Integer.parseInt(sc.nextLine());
		System.out.println("Enter course section: ");
		int courseSection = Integer.parseInt(sc.nextLine());

		Course temp = cat.searchCat(courseName, courseID);
		addCourse(temp, courseSection);
		sc.close();
	}
	
	public void userDeleteCourse(CourseCatalogue cat)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter course Name: ");
		String courseName = sc.nextLine();
		System.out.println("Enter course ID: ");
		int courseID = Integer.parseInt(sc.nextLine());
		Course tempCourse = cat.searchCat(courseName, courseID);
		this.deleteRegistration(tempCourse);
		sc.close();
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
