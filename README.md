# ENSF 409 Final Project
This project implements a student course registration system, where student clients can connect to a server, view courses, register for courses, and remove their registration in courses. It runs a thread pool, and stores the data in a MySQL server. Client-server communication is handled by seralizing the data.

## Full Names and Emails
* Parker Link (parker.link1@ucalgary.ca)
* Michaela Gartner (michaela.gartner@ucalgary.ca)
* William Ledingham (william.ledingham1@ucalgary.ca)

## Run Instructions
1. Setup a MySQL server, running locally, with an account with the following login credentials:
	* Username: user
	* Password: user
2. Initialize the server by running the `/CreateDatabase.sql` file.
3. Run the server from the `/src/server/controller/ServerCommController.java` file.
4. Run the client from the `/src/client/controller/ClientController.java` file.
5. Login as a user by entering their student ID. Currently, there are 3 users registered (with IDs 1, 2, and 3).
6. Click each button at the bottom of the screen to do the following:
	* Search for a Course
	* Add (Register) for a Course
	* Remove a Registration for a Course
7. Run a second instance of the client simultaneously. See changes made in the first client reflected in the second client after clicking refresh in the second client.

## Bonus Features Implemented
* Login/Logout Feature
	* Click the "Change User" button, and then log in as another user
* Deploy the Project (Run the Server and Client on Seperate Machines)
	* See the video demonstration
	* Enter the address, or press okay to use the local computer (localhost).
	* The client run configuration was exported as a .jar file for distribution.
* JUnit Testing
	* JUnit Testing for the `Course` and `Student` classes
	* Testing shown in video demonstration