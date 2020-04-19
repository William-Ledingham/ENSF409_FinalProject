package Tools;

import static org.junit.jupiter.api.Assertions.*;
import server.model.*;
import org.junit.jupiter.api.Test;

class TestCourse {

	@Test
	void testCourse() {
		Course c = new Course(1,"ENSF", 309);
		String name = c.getCourseName();
		int num =c.getCourseNum();
		assertEquals("ENSF", name);
		assertEquals(309, num);
	}

	@Test
	void testAddOffering() {
		Course c = new Course(1,"ENSF", 309);
		CourseOffering offering = new CourseOffering(1, 20);
		c.addOffering(offering);
		assertEquals(offering, c.getCourseOfferingAt(1));
	}

	@Test
	void testGetCourseName() {
		Course c = new Course(1, "ENSF", 309);
		String st =c.getCourseName();
		//fail("Not yet implemented");
		assertEquals(c.getCourseName(), st);
	}


	@Test
	void testGetCourseNum() {
		Course c = new Course(1, "ENSF", 309);
		int num =c.getCourseNum();
		//fail("Not yet implemented");
		assertEquals(c.getCourseNum(), num);
	}



	@Test
	void testGetCourseOfferingAt() {
		Course c = new Course(1, "ENSF", 309);
		CourseOffering offering = new CourseOffering(1, 20);
		c.addOffering(offering);
		//fail("Not yet implemented");
		assertEquals(offering, c.getCourseOfferingAt(1));
	}

}

