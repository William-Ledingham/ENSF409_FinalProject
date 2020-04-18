package server.model;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * A list of all the courses available
 * @author William Ledingham
 * @version 1.0
 * @since 12-05-2020
 *
 */
public class CourseCatalogue implements Serializable {
	
	/**
	 * The id for object serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * the list of courses available
	 */
	private ArrayList <Course> courseList;
	/**
	 * Creates a new empty course catalogue
	 */
	public CourseCatalogue () {
	}
	/**
	 * creates a new course offering and adds to the given course
	 * @param c the course for offering to be added to
	 * @param secNum the section number for the offering
	 * @param secCap the capacity for the offering
	 */
	public void createCourseOffering (Course c, int secNum, int secCap) {
		if (c!= null) {
			CourseOffering theOffering = new CourseOffering (secNum, secCap);
			c.addOffering(theOffering);
		}
	}
	
	/**
	 * Searches the course catalogue by a name and course number.
	 * @param courseName the course name
	 * @param courseNum the course num
	 * @return the course being searched for if found, otherwise reutnrs null
	 */
	public Course searchCat (String courseName, int courseNum) {
		for (Course c : courseList) {
			if (courseName.equals(c.getCourseName()) &&
					courseNum == c.getCourseNum()) {
				return c;
			}	
		}
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
	/**
	 * Gets the course list
	 * @return The course list
	 */
	public ArrayList <Course> getCourseList() {
		return courseList;
	}
	/**
	 * Sets the course list.
	 * @param courseList the course list.
	 */
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
