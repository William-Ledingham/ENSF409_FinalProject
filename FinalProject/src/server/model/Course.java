package server.model;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * A course that a student can be registered in.
 * @author William Ledingham
 * @version 1.0
 * @since 12-04-2020
 *
 */
public class Course implements Serializable {	
	/**
	 * serial id for object serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * the name of the course
	 */
	private String courseName;
	/**
	 * the course number
	 */
	private int courseNum;
	/**
	 * the list of prereqs for the course
	 */
	private ArrayList<Course> preReq;
	/**
	 * the sections for the course
	 */
	private ArrayList<CourseOffering> offeringList;
	/**
	 * the unique id number for the course for database management
	 */
	private int id;

	/**
	 * creates a new course with the specified name, number, and id
	 * @param id the unique id number for the course for database management
	 * @param courseName the course name
	 * @param courseNum the course number
	 */
	public Course(int id, String courseName, int courseNum) {
		this.setCourseName(courseName);
		this.setCourseNum(courseNum);
		this.id = id;
		// Both of the following are only association
		preReq = new ArrayList<Course>();
		offeringList = new ArrayList<CourseOffering>();
	}

	/**
	 * adds a new offering to the course offering list
	 * @param offering the offering to be added
	 */
	public void addOffering(CourseOffering offering) {
		if (offering != null && offering.getTheCourse() == null) {
			offering.setTheCourse(this);
			if (!offering.getTheCourse().getCourseName().equals(courseName)
					|| offering.getTheCourse().getCourseNum() != courseNum) {
				System.err.println("Error! This section belongs to another course!");
				return;
			}
			
			offeringList.add(offering);
		}
	}

	/**
	 * gets the name of the course
	 * @return String the course name
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * sets the name of the course
	 * @param courseName the course name
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * gets the course number
	 * @return int the course number
	 */
	public int getCourseNum() {
		return courseNum;
	}
	/**
	 * gets the course ID
	 * @return int the course ID
	 */
	public int getCourseID() {
		return id;
	}

	/**
	 * sets the course number
	 * @param courseNum the course number
	 */
	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}
	/**
	 * Returns string of course information.
	 */
	@Override
	public String toString () {
		String st = "\n";
		st += getCourseName() + " " + getCourseNum ();
		st += "\nAll course sections:\n";
		for (CourseOffering c : offeringList)
			st += c;
		st += "\n-------\n";
		return st;
	}

	/**
	 * Gets the CourseOffering based off of its number.
	 * @param courseSec The section of the course
	 * @return The CourseOffering of the given section number or null if the offering
	 * is not found
	 */
	public CourseOffering getCourseOfferingAt(int courseSec) {
		for (CourseOffering offering : offeringList) {
			if (offering.getSecNum() == courseSec) {
				return offering;
			}
		}
		return null;
	}	
	/**
	 * gets the preReq list for the course
	 * @return the prereq list
	 */
	public ArrayList<Course> getPreq() {
		return preReq;
	}

}
