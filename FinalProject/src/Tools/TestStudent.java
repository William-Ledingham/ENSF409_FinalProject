package Tools;

import static org.junit.jupiter.api.Assertions.*;
import server.model.*;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TestStudent {
	

	@Test
	void testGetStudentRegList() {
		Student s = new Student("michaela", 1);		
		CourseOffering offering = new CourseOffering(1, 20);
		Registration r = new Registration(s, offering);
		s.addRegistration(r);
		ArrayList regList = s.getStudentRegList();
		assertEquals(regList.get(0), r);
	}

	@Test
	void testStudent() {
		Student s = new Student("michaela", 1);		
		assertEquals(s.getStudentName(), "michaela");
		assertEquals(s.getStudentId(), 1);
	}

	@Test
	void testAddRegistration() {
		Student s = new Student("michaela", 1);		
		CourseOffering offering = new CourseOffering(1, 20);
		Registration r = new Registration(s, offering);
		s.addRegistration(r);
		ArrayList regList = s.getStudentRegList();
		assertEquals(regList.get(0), r);
	}

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

		ArrayList regList1 = s.getStudentRegList();
		ArrayList<Registration> regList2 = new ArrayList<Registration>();
		regList2.add(r1);
		assertEquals(s.getStudentRegList(), regList2);
	}

//	@Test
//	void testGetAllCourseRegistrations() {
//		Course c1 = new Course(1, "ENSF", 409);
//		Course c2 = new Course(2, "CPSC", 319);
//		Student s = new Student("michaela", 1);		
//		CourseOffering offering1 = new CourseOffering(1, 20);
//		offering1.setTheCourse(c1);
//		Registration r1 = new Registration();
//		r1.completeRegistration(s, offering1);
//		ArrayList<Registration> regList2 = new ArrayList<Registration>();
//		regList2.add(r1);
//		
//		ArrayList<Registration> regList1 = s.getStudentRegList();
//		String actual = regList1.size() + " Registration(s) for " + "michaela" + " (ID = " + 1 + ")\n\n";
//		actual+= regList1.get(0).getTheOffering()+ "\n";
//		
//		String theoretical = regList2.size() + " Registration(s) for " + "michaela" + " (ID = " + 1 + ")\n\n";
//		actual+= regList2.get(0).getTheOffering()+ "\n";
//		
//		assertEquals(actual, theoretical);
//		
//	}


}