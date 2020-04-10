package server.model;

import java.util.Scanner;

public class RegistrationApp {
	
	/*
	public static void main (String [] args) {
		
		DBManager db = new DBManager();
		
		Scanner sc = new Scanner(System.in);
		int input;
		while(true)
		{
			System.out.printf("\n1. Search catalogue courses\n2. Add course to student courses\n3. "
					+ "Remove course from student courses\n4. View all courses in catalogue\n5. View all courses taken by student\n6. Quit\n");
			input = Integer.parseInt(sc.nextLine());
			switch(input)
			{
			case 1:	
				db.getCourseCatalogue().userSearchCatalogue();
				break;
			case 2:
				Student temp1 = db.UserSearchCourseList();
				temp1.userAddCourse(db.getCourseCatalogue());
				break;
			case 3:
				Student temp2 = db.UserSearchCourseList();
				temp2.userDeleteCourse(db.getCourseCatalogue());
				break;

			case 4:
				System.out.println(db.getCourseCatalogue());
				break;
			case 5:
				Student temp3 = db.UserSearchCourseList();
				temp3.printAllCourses();
				break;
			case 6:
				sc.close();
				return;
			default:
				break;
				
			}		
		}
		
		
	}

*/
}
