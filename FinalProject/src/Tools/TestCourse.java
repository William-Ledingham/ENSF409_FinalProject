package Tools;

import static org.junit.jupiter.api.Assertions.*;
import server.model.*;
import org.junit.jupiter.api.Test;
/**
 * Tests the methods in the Course class using the JUNIT frame work
 * @author Michaela Gartner
 * @version 1.0
 * @since 19-04-2020
 *
 */
class TestCourse {

	/**
	 * Tests the Course constructor. It does this by creating a course and then comparing
	 * the course name and num with the ones used to create the course.
	 */
	@Test
	void testCourse() {
		Course c = new Course(1,"ENSF", 309);
		String name = c.getCourseName();
		int num =c.getCourseNum();
		assertEquals("ENSF", name);
		assertEquals(309, num);
	}

	/**
	 * Tests the add offering method. It does this by creating an offering and adding
	 * it to the course. Then it compares the offering with the one stored in the course.
	 */
	@Test
	void testAddOffering() {
		Course c = new Course(1,"ENSF", 309);
		CourseOffering offering = new CourseOffering(1, 20);
		c.addOffering(offering);
		assertEquals(offering, c.getCourseOfferingAt(1));
	}

	/**
	 * Tests the get course name method. It does this by getting the course name and then
	 * comparing it to the one used to create the course.
	 */
	@Test
	void testGetCourseName() {
		Course c = new Course(1, "ENSF", 309);
		String st =c.getCourseName();
		assertEquals(c.getCourseName(), st);
	}


	/**
	 * Tests the get course num method. It does this by getting the course num and then
	 * comparing it to the one used to create the course.
	 */
	@Test
	void testGetCourseNum() {
		Course c = new Course(1, "ENSF", 309);
		int num =c.getCourseNum();
		assertEquals(c.getCourseNum(), num);
	}


	/**
	 * Tests the get course offering at method. It does this by creating a course and an offering
	 * and then adding the offering to the course. Then it calls the getCourseOfferingAt method and compares
	 * the result to the offering added to the course.
	 */

	@Test
	void testGetCourseOfferingAt() {
		Course c = new Course(1, "ENSF", 309);
		CourseOffering offering = new CourseOffering(1, 20);
		c.addOffering(offering);
		//fail("Not yet implemented");
		assertEquals(offering, c.getCourseOfferingAt(1));
	}

}

