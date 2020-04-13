package server.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
		cat =readCoursesFromDataBase();
		studentList =readStudentsFromDatabase();
	}

	
	public CourseCatalogue readCoursesFromDataBase() {
		ObjectInputStream input = null;
		String fileName = "courseCatalogue.ser";
		CourseCatalogue courseCat =null;		        
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
		       // System.out.println(courseCat.toString());
		     }   
		 }catch(Exception e) {
			 System.out.println("done reading file");
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
		}
		catch ( IOException ioException )
		{
			System.err.println( "Error opening file." );
		}		        
		try
		{
			while ( true )
		    {
				
				s = (Student)input.readObject();
		       // System.out.println(s.toString());
		        studentList.add(s);
		     }   
		 }catch(Exception e) {
			 System.out.println("done reading file");
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
	
	public void addCourseOffering(CourseOffering courseToAdd) {
		
	}

}
