package tools;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import server.model.*;

public class SerialFileWriter {
	ObjectOutputStream objectOut = null;
	CourseCatalogue courseCatalogue = null;
	Scanner stdin = null;
	Scanner textFileIn = null;
	//private ObjectInputStream input;
	ArrayList<Student> sList;
	
	public void writeStudentListSer() {		
		System.out.println("student list ops");
		String objectFileNameStudent = "studentList.ser";
		createStudentList();
		openObjectOutputStream(objectFileNameStudent);
		System.out.println("creating file");
		createObjectFileForStudentList(); 
		System.out.println("reading file");
		for(Student s: sList) {
			System.out.println(s.toString());
		}
		try {
			objectOut.close();
		}catch(IOException ie) {
			ie.printStackTrace();
		}
	}
	
	public static void main(String [] args) {
		SerialFileWriter serialFileWriter = new SerialFileWriter();
		serialFileWriter.writeCourseCatalogueSer();
		serialFileWriter.writeStudentListSer();
		
		System.out.println("All done writing both files.");
	}
	
	public void writeCourseCatalogueSer() {
		//CourseCatalogue courseCatalogue = null;
		System.out.println("course catalogue ops");
		String objectFileName = "courseCatalogue.ser";
		createCourseCatalogue();
		//System.out.println(test.courseCatalogue.toString());
		openObjectOutputStream(objectFileName);
		
		System.out.println("creating file");
		createObjectFileForCourseCat(); 
		System.out.println("reading file");
		
		System.out.println(courseCatalogue.toString());
		try {
			objectOut.close();
		}catch(IOException ie) {
			ie.printStackTrace();
		}
	}
	

	public void createCourseCatalogue() {
		courseCatalogue = new CourseCatalogue();
		Course c1 = new Course ("ENSF", 409);
		Course c2 = new Course ("CPSC", 319);
		Course c3 = new Course ("ENEL", 400);
		Course c4 = new Course ("ENEL", 469);
		Course c5 = new Course ("ENEL", 471);
		ArrayList<Course> cList = new ArrayList<Course>();
		cList.add(c1);
		cList.add(c2);
		cList.add(c3);
		cList.add(c4);
		cList.add(c5);
		courseCatalogue.setCourseList(cList);
		courseCatalogue.createCourseOffering(c1, 1, 9);
		courseCatalogue.createCourseOffering(c2, 1, 9);
		courseCatalogue.createCourseOffering(c3, 1, 9);
		courseCatalogue.createCourseOffering(c4, 1, 9);
		courseCatalogue.createCourseOffering(c5, 1, 9);
	}
	
	public void createStudentList() {
		//sList = new StudentList();
		Student s1 = new Student ("Michaela", 1);
		Student s2 = new Student ("Wil", 2);
		Student s3 = new Student ("Parker", 3);

		// Register the student in a course (doesn't create all proper linkages, therefore not removable)
		//s1.registerStudentInCourse(courseCatalogue, "ENSF", 409, 1);
		
		// Add all the students to the list
		sList = new ArrayList<Student>();
		sList.add(s1);
		sList.add(s2);
		sList.add(s3);
//		sList.setSList(studentList);
		for(Student s: sList) {
			System.out.println(s.toString());
		}
	}
	public void openObjectOutputStream(String objectFileName) {
        
		try {
			objectOut = new ObjectOutputStream(new FileOutputStream(objectFileName));       	
        }catch(IOException e) {
        	System.err.println("error opening file");
        }
    // TO BE COMPLETED BY THE STUDENTS
        
	}
	
	public void createObjectFileForCourseCat() {
		try {
			objectOut.writeObject(courseCatalogue);
			objectOut.flush();
		}catch(IOException ie) {
			System.err.println("error writing to file");
			ie.printStackTrace();
		}catch(NoSuchElementException ne) {
			System.err.println("invalid input pls try again");
		}
	}
	
	public void createObjectFileForStudentList() {
		for(Student s: sList) {
			try {
				objectOut.writeObject(s);
				objectOut.flush();
			}catch(IOException ie) {
				System.err.println("error writing to file");
				ie.printStackTrace();
			}catch(NoSuchElementException ne) {
				System.err.println("invalid input pls try again");
			}
		}
	}

}
