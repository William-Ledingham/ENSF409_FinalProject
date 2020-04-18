package server.model;

import java.io.Serializable;
import java.util.ArrayList;

public class CourseCatalogue implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private ArrayList <Course> courseList;
	
	public CourseCatalogue () {
	}

	public void createCourseOffering (Course c, int secNum, int secCap) {
		if (c!= null) {
			CourseOffering theOffering = new CourseOffering (secNum, secCap);
			c.addOffering(theOffering);
		}
	}
	
	/**
	 * Searches the course catalogue by a name and course number.
	 * @param courseName
	 * @param courseNum
	 * @return
	 */
	public Course searchCat (String courseName, int courseNum) {
		for (Course c : courseList) {
			if (courseName.equals(c.getCourseName()) &&
					courseNum == c.getCourseNum()) {
				return c;
			}	
		}
		// displayCourseNotFoundError(); // too many messages in the server now
		return null;
	}
	
	/**
	 * Searches the course catalogue by a course ID number (different than a course number).
	 * @param courseID The course ID number (different than a course number).
	 * @return the course.
	 */
	public Course searchCat(int courseID) {
		for (Course course : courseList) {
			if (course.getCourseID() == courseID) {
				return course;
			}
		}
		return null;
	}
	
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

}
