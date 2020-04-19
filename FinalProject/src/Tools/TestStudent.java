package Tools;

import static org.junit.jupiter.api.Assertions.*;
import server.model.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
/**
 * Tests the methods in the Student class using the JUNIT frame work
 * @author Michaela Gartner
 * @version 1.0
 * @since 19-04-2020
 *
 */
class TestStudent {
	

	/**
	 * Tests the getStudentRegList method. It does this by creating a reg list and setting
	 * the reg list in student, then asserting that the two are equal.
	 */
	@Test
	void testGetStudentRegList() {
		Student s = new Student("michaela", 1);		
		CourseOffering offering = new CourseOffering(1, 20);
		Registration r = new Registration(s, offering);
		s.addRegistration(r);
		ArrayList<Registration> regList = s.getStudentRegList();
		assertEquals(regList.get(0), r);
	}
	/**
	 * Tests the student constructor. It does this by creating a student and then comparing
	 * the student name and number with the ones used in the constructor
	 */

	@Test
	void testStudent() {
		Student s = new Student("michaela", 1);		
		assertEquals(s.getStudentName(), "michaela");
		assertEquals(s.getStudentId(), 1);
	}
	/**
	 * Test the add registration method. It does this by creating a registration
	 * and then calling the add registration method. It then compares the registration passed
	 * to the add registration method and then comparing it to the reglist stored in student
	 */

	@Test
	void testAddRegistration() {
		Student s = new Student("michaela", 1);		
		CourseOffering offering = new CourseOffering(1, 20);
		Registration r = new Registration(s, offering);
		s.addRegistration(r);
		ArrayList<Registration> regList = s.getStudentRegList();
		assertEquals(regList.get(0), r);
	}

	/**
	 * Tests the delete registration method. It does this by creating 2 registrations and adding
	 * them to the student reg list, then deleting one of the registrations. It then compares the student
	 * reg list to one created with one of the registrations.
	 */
	@Test
	void testDeleteRegistrationCourse() {
		Course c1 = new Course(1, "ENSF", 409);
		Course c2 = new Course(2, "CPSC", 319);
		Student s = new Student("michaela", 1);		
		CourseOffering offering1 = new CourseOffering(1, 20);
		offering1.setTheCourse(c1);
		Registration r1 = new Registration(s,offering1);
		s.addRegistration(r1);
		CourseOffering offering2 = new CourseOffering(2, 20);
		offering2.setTheCourse(c2);
		Registration r2 = new Registration(s, offering2);
		s.addRegistration(r2);
		s.deleteRegistration(c2);

		ArrayList<Registration> regList2 = new ArrayList<Registration>();
		regList2.add(r1);
		assertEquals(s.getStudentRegList(), regList2);
	}


}