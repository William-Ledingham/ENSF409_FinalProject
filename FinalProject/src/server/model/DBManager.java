package server.model;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import shared.model.*;

//This class is simulating a database for our
//program
public class DBManager {
	
	private ArrayList <Course> courseList;
	private ArrayList <Student> studentList;
	private CourseCatalogue cat;

	public DBManager () {
		courseList = new ArrayList<Course>();
		studentList = new ArrayList<Student>();
		cat = new CourseCatalogue();
		cat.setCourseList(courseList);
		cat = readCoursesFromDatabase();
		studentList = readStudentsFromDatabase();
	}

	
	public CourseCatalogue readCoursesFromDatabase() {
		ObjectInputStream input = null;
		String fileName = "courseCatalogue.ser";
		CourseCatalogue courseCat = null;		        
		try
		{
			input = new ObjectInputStream(new FileInputStream( fileName ) );
		}
		catch ( IOException ioException )
		{
			System.err.println( "Error opening file." );
		}		        
		try
		{
			while ( true )
		    {
				courseCat = (CourseCatalogue)input.readObject();
				// System.out.println(courseCat.toString()); // DEBUG 
		     }
		 }catch(EOFException e) {
			 System.out.println("Done reading file (course catalogue)");
		 }
		catch (Exception e) {
			System.err.println("Other Error");
			e.printStackTrace();
		}
		return courseCat;
		
	}
	public void WriteCourseCatalogue() {
		
	}
	public ArrayList<Student> readStudentsFromDatabase() {
		ObjectInputStream input = null;
		String fileName = "studentList.ser";
		ArrayList <Student> studentList = new ArrayList<Student>();	        
		//StudentList list = new StudentList();
		Student s;
		
		try
		{
			input = new ObjectInputStream(new FileInputStream( fileName ) );
		 }catch(EOFException e) {
			 System.out.println("Done reading file (student list)");
		 }
		catch (Exception e) {
			System.err.println("Other Error");
			e.printStackTrace();
		}	        
		try
		{
			while ( true )
		    {
				
				s = (Student)input.readObject();
		       // System.out.println(s.toString()); // DEBUG
		        studentList.add(s);
		     }   
		 }catch(Exception e) {
			 System.out.println("Done reading file (student list)");
		 }
		return studentList;
		
	}
	
	
	
	
	public Student UserSearchCourseList()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter student ID: ");
		int input = Integer.parseInt(sc.nextLine());
		for(Student student : studentList)
		{
			if(input == student.getStudentId())
			{
				sc.close();
				return student;
			}
		}
		sc.close();
		return null;
	}
	
	public CourseCatalogue getCourseCatalogue()
	{
		return cat;
	}
	
	/**
	 * Adds a course offering (and course) to the database.
	 * @param faculty
	 * @param courseNumber
	 * @param lectNumber
	 */
	public void addCourseOffering(String faculty, int courseNumber, int lectNumber) {
		cat.createCourseOffering(new Course(faculty, courseNumber), lectNumber, 100);
		
		System.out.println("With new course added, we having the following courses: ");
		System.out.println(cat);
	}
	
	

}
