package server.model;

import java.sql.*;
import java.util.ArrayList;

/**
 * Manages the database of everything, on the server side.
 * Connects to the MySQL server, loads in content, and writes it back to the database.
 * @author Parker Link
 * @version 1.0
 * @since 12-04-2020
 *
 */
public class DBManager implements SQLCredentials {
	/**
	 * The list of courses to be displayed in the GUI and used to create the course catalogue.
	 */
	private ArrayList <Course> courseList;
	/**
	 * The list of students to be used for login.
	 */
	private ArrayList <Student> studentList;
	/**
	 * The catalogue of courses that the student can register in
	 */
	private CourseCatalogue cat;
	/**
	 * The connection used to connect to the database
	 */
	private Connection conn;
	/**
	 * The object used to communicate with the database
	 */
	private ResultSet rs;
	/**
	 * Initializes the connection to the MySQL database, as per the connection details
	 * defined in the SQLCredentials interface.
	 */
	public void initializeConnection() {
		try {
			// Register JDBC driver
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.err.println("Problem Opening MySQL Connection");
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes the connection to the MySQL database server.
	 */
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Creates a new database Manager. Creates a connection to the data base and reads the data in.
	 */
	public DBManager () {
		// Initialize Connection to MySQL Server
		initializeConnection();		
		// Read everything from the database
		readDatabase();
	}	
	/**
	 * Reads everything from the database, compiles it (effectively syncs the database). Reads the courses, students, and registrations from scratch.
	 */
	public void readDatabase() {
		// Read the courses
		readCoursesFromDatabase();

		// Read the students
		readStudentsFromDatabase();
		
		// Read the registrations
		readRegistrationsFromDatabase();
	}

	/**
	 * Reads in the list of courses from the courses table in the data base and adds them to the course list and
	 * catalogue.
	 */
	private void readCoursesFromDatabase() {
		// Start with a blank version of the course lists/course catalogue
		courseList = new ArrayList<Course>();
		cat = new CourseCatalogue();
		cat.setCourseList(courseList);
		
		try {
			String query = "SELECT * FROM courses";
			PreparedStatement pStat = conn.prepareStatement(query);
			rs = pStat.executeQuery();
			while (rs.next()) { // iterate through each row in the table
				Course thisCourse = new Course(rs.getInt("id"), rs.getString("name"), rs.getInt("courseNum"));
				for (String offeringStr : rs.getString("sections").split(",")) { // loop through each offering
					thisCourse.addOffering(new CourseOffering(Integer.parseInt(offeringStr), 10)); // 10 is the hardcoded capacity, look away
				}
				courseList.add(thisCourse);
			}
			pStat.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Reads in the list of students from the student table in the data base and adds them to the student list.
	 */
	private void readStudentsFromDatabase() {
		// Start with a blank version of the student list
		studentList = new ArrayList<Student>();
		
		try {
			String query = "SELECT * FROM students";
			PreparedStatement pStat = conn.prepareStatement(query);
			rs = pStat.executeQuery();
			while (rs.next()) { // iterate through each row in the table
				Student thisStudent = new Student(rs.getString("name"), rs.getInt("id"));
				studentList.add(thisStudent);
			}
			pStat.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads all the registrations from the 'registrations' table, and adds them to the respective students and course offerings.
	 * If we re-designed the system, we would implement the registration tracking system differently.
	 */
	private void readRegistrationsFromDatabase() {		
		try {
			String query = "SELECT * FROM registrations";
			PreparedStatement pStat = conn.prepareStatement(query);
			rs = pStat.executeQuery();
			while (rs.next()) { // iterate through each row in the table (each row is a registration)
				Student student = getStudentByID(rs.getInt("studentID"));
				CourseOffering offering = cat.searchCat(rs.getInt("courseID")).getCourseOfferingAt(rs.getInt("section"));
				Registration reg = new Registration(student, offering);
				reg.addRegistration();
			}
			pStat.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds this student to the course given by the last 3 parameters. Returns a success/error message.
	 * Adds it both locally and to the MySQL server.
	 * This method is only called from the DBController when a user adds a new course, not during setup.
	 * 
	 * @param student the student to be added to the course.
	 * @param cat the catalogue to find the course in
	 * @param courseName the given course name
	 * @param courseNum the given course number
	 * @param courseSection the given course section
	 * @return A success/error message (gotten from the student.registerStudentInCourse) for the user
	 */
	public String registerStudentInCourse(Student student, CourseCatalogue cat, String courseName, int courseNum, int courseSection) {
		Course course = cat.searchCat(courseName, courseNum);
		
		if (course == null) {
			return "Course Not Found (Check the Course Name and Number)";
		}

		CourseOffering offering = course.getCourseOfferingAt(courseSection);
		if (offering == null) {
			return "Course Offering Not Found (Check the Course Section)";
		}
		
		if(student.getStudentRegList().size() < 6)
		{
			Registration reg = new Registration(student, offering);
			reg.addRegistration();
			
			try {
				String query = "INSERT INTO registrations (studentID, courseID, section) VALUES (?, ?, ?)";
				PreparedStatement pStat = conn.prepareStatement(query);
				pStat.setInt(1, student.getStudentId());
				pStat.setInt(2, course.getCourseID());
				pStat.setInt(3, courseSection);
				pStat.executeUpdate(); // can be used to get the new number of rows, not important
				pStat.close();
			}
			catch (SQLException e) {
				System.err.println("Error adding a registration.");
				e.printStackTrace();
			}
			
			readDatabase();
			return "Successfully added " + courseName  + " " + courseNum + "!";
		}
		else
		{
			return "Unfortunately you have 6 classes already.";
		}
	}
	
	/**
	 * Deletes a registration from the specified course. Deletes it both locally and in the SQL database.
	 * 
	 * @param student the student to have the registration deleted from
	 * @param cat the catalogue to get the course from
	 * @param courseName the name of the course
	 * @param courseNum the course number
	 * @param courseSection the course section
	 * @return Success/failure message to user
	 */
	public String deleteStudentFromCourse(Student student, CourseCatalogue cat, String courseName, int courseNum, int courseSection) {
		Course course = cat.searchCat(courseName, courseNum);
		
		if (course == null) {
			return "Course Not Found (Check the Course Name and Number)";
		}

		CourseOffering offering = course.getCourseOfferingAt(courseSection);
		if (offering == null) {
			return "Course Offering Not Found (Check the Course Section)";
		}
		
		student.deleteRegistration(course);
		
		try {
			String query = "DELETE FROM registrations WHERE studentID=? AND courseID=? AND section=?";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setInt(1, student.getStudentId());
			pStat.setInt(2, course.getCourseID());
			pStat.setInt(3, courseSection);
			pStat.executeUpdate(); // can be used to get the new number of rows, not important
			pStat.close();
		}
		catch (SQLException e) {
			System.err.println("Error adding a registration.");
			e.printStackTrace();
		}
		
		readDatabase();
		
		return "Successfully removed you from the course!";
	}
	
	/**
	 * Searches the students list for a single student by their ID.
	 * @param studentID ID of Student
	 * @return The student, or null if not found.
	 */
	public Student getStudentByID(int studentID)
	{
		for(Student student : studentList)
		{
			if(studentID == student.getStudentId())
			{
				return student;
			}
		}
		return null;
	}
	
	/**
	 * Gets the course catalogue
	 * @return the CourseCatalogue
	 */
	public CourseCatalogue getCourseCatalogue()
	{
		return cat;
	}
	
	/**
	 * Adds a course offering (and course) to the database.
	 * @param courseID the unique course ID
	 * @param faculty the faculty of the course
	 * @param courseNumber the course number
	 * @param lectNumber the section number
	 */
	public void addCourseOffering(int courseID, String faculty, int courseNumber, int lectNumber) {
		cat.createCourseOffering(new Course(courseID, faculty, courseNumber), lectNumber, 100);		
		System.out.println("With new course added, we having the following courses: ");
		System.out.println(cat);
	}
}
