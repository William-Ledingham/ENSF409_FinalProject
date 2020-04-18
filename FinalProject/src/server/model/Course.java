package server.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String courseName;
	private int courseNum;
	private ArrayList<Course> preReq;
	private ArrayList<CourseOffering> offeringList;
	private int id;

	public Course(int id, String courseName, int courseNum) {
		this.setCourseName(courseName);
		this.setCourseNum(courseNum);
		this.id = id;
		// Both of the following are only association
		preReq = new ArrayList<Course>();
		offeringList = new ArrayList<CourseOffering>();
	}

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

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCourseNum() {
		return courseNum;
	}
	
	public int getCourseID() {
		return id;
	}

	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}
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
	 * This is changed, where the original version got it by an index instead of by the section number.
	 * 
	 * @param courseSec The section of the course
	 * @return The CourseOffering of the given section number
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
	 * 
	 * @return
	 */
	public ArrayList<Course> getPreq() {
		return preReq;
	}

}
