package server.model;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * the section of a given course
 * @author William Ledingham
 * @version 1.0
 * @since 12-05-2020
 *
 *
 */
public class CourseOffering implements Serializable {
	
	/**
	 * The object serialization ID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The section number for the course
	 */
	private int secNum;
	/**
	 * The section capacity
	 */
	private int secCap;
	/**
	 * The course the section is for
	 */
	private Course theCourse;
	/**
	 * The list of registrations for the offering
	 */
	private ArrayList <Registration> offeringRegList;
	/**
	 * Creates a new course offering with the specified section number and section capacity.
	 * @param secNum the section number
	 * @param secCap the section capacity
	 */
	public CourseOffering (int secNum, int secCap) {
		this.setSecNum(secNum);
		this.setSecCap(secCap);
		offeringRegList = new ArrayList <Registration>();
	}
	/**
	 * Gets the section number.
	 * @return the section number
	 */
	public int getSecNum() {
		return secNum;
	}
	/**
	 * Sets the section number.
	 * @param secNum the section number
	 */
	public void setSecNum(int secNum) {
		this.secNum = secNum;
	}
	/**
	 * Gets the section capacity.
	 * @return the section capacity
	 */
	public int getSecCap() {
		return secCap;
	}
	/**
	 * Sets the section capacity.
	 * @param secCap the section capacity
	 */
	public void setSecCap(int secCap) {
		this.secCap = secCap;
	}
	/**
	 * Gets the course for this section.
	 * @return the course
	 */
	public Course getTheCourse() {
		return theCourse;
	}
	/**
	 * Sets the course for this section.
	 * @param theCourse the course
	 */
	public void setTheCourse(Course theCourse) {
		this.theCourse = theCourse;
	}
	@Override
	public String toString () {
		String st = "\n";
		st += getTheCourse().getCourseName() + " " + getTheCourse().getCourseNum() + "\n";
		st += "Section Num: " + getSecNum() + ", Section Cap: "+ getSecCap();
		if(offeringRegList.size() < 8)
		{
			st += ",  Offering Requires More Students to Run";
		}
		 st += "\n";
		return st;
	}
	/**
	 * Adds new registration to the registration list.
	 * @param registration the registration to be added
	 */
	public void addRegistration(Registration registration) {
		offeringRegList.add(registration);		
	}
	/**
	 * Removes a registration from the registration list.
	 * @param registration the registration to be removed
	 */
	public void deleteRegistration(Registration registration)
	{
		offeringRegList.remove(registration);
	}
	
	

}
