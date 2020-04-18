package server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseCatalogue implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private ArrayList <Course> courseList;
	
	public CourseCatalogue () {
		//loadFromDataBase ();
	}

	public void createCourseOffering (Course c, int secNum, int secCap) {
		if (c!= null) {
			CourseOffering theOffering = new CourseOffering (secNum, secCap);
			c.addOffering(theOffering);
		}
	}
	public Course searchCat (String courseName, int courseNum) {
		for (Course c : courseList) {
			if (courseName.equals(c.getCourseName()) &&
					courseNum == c.getCourseNum()) {
				return c;
			}	
		}
		// displayCourseNotFoundError(); // too many messages
		return null;
	}
/*
	private void displayCourseNotFoundError() {
		System.err.println("Course was not found!");
		
	}
	*/
	
	public ArrayList <Course> getCourseList() {
		return courseList;
	}


	public void setCourseList(ArrayList <Course> courseList) {
		this.courseList = courseList;
	}
	@Override
	public String toString () {
		String st = "All courses in the catalogue: \n";
		for (Course c : courseList) {
			st += c;  //This line invokes the toString() method of Course
			st += "\n";
		}
		return st;
	}
	
	public void userSearchCatalogue()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter course Name: ");
		String searchCourseName = sc.nextLine();	
		System.out.println("Enter course ID: ");
		int searchCourseID = Integer.parseInt(sc.nextLine());
		System.out.println(searchCat(searchCourseName, searchCourseID));
		sc.close();
	}

}
