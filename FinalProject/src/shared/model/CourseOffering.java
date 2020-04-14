package shared.model;

import java.io.Serializable;
import java.util.ArrayList;

public class CourseOffering implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int secNum;
	private int secCap;
	private Course theCourse;
	//private ArrayList<Student> studentList;
	private ArrayList <Registration> offeringRegList;
	
	public CourseOffering (int secNum, int secCap) {
		this.setSecNum(secNum);
		this.setSecCap(secCap);
		offeringRegList = new ArrayList <Registration>();
	}
	public int getSecNum() {
		return secNum;
	}
	public void setSecNum(int secNum) {
		this.secNum = secNum;
	}
	public int getSecCap() {
		return secCap;
	}
	public void setSecCap(int secCap) {
		this.secCap = secCap;
	}
	public Course getTheCourse() {
		return theCourse;
	}
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
	public void addRegistration(Registration registration) {
		offeringRegList.add(registration);
		
	}
	public void deleteRegistration(Registration registration)
	{
		offeringRegList.remove(registration);
	}
	
	

}
