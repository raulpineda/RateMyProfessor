package rateMyProfessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Database {
	
	private List<Student> students;
	private List<Professor> professors;
	private List<Rating> ratings;
	private List<User> users; 
	
	public Database() {
		students = new ArrayList<Student>();
		professors = new ArrayList<Professor>();
		ratings = new ArrayList<Rating>();
//		users = new ArrayList<User>();
		try {
			users = loadUsersFromFile("users.txt"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<User> getUsers() {
		return this.users;
	}
	
	public void registerStudent(Student student) throws Exception {
		students.add(student);
		users.add(student);
		// appendUserToFile("users.txt", student);
	}
	
	public void registerProfessor(Professor professor) {
		professors.add(professor);
		users.add(professor);
	}

	public void rateProfessor(Student student, Professor professor, int score) {
		ratings.add(new Rating(student, professor, score));
	}
	
	public float getAverage(Professor professor) {
		int totalScore = 0;
		int scoreCount = 0;
		for (Rating rating : ratings) {
			if(rating.getProfessor().equals(professor)) {
				totalScore += rating.getScore();
				scoreCount++;
			}
		}
		return (float) totalScore / scoreCount;
	}
	
	//We will go with this if we go with the subject class
	
//	public void rateProfessor(Student student, Professor professor, Subject subject, int score) throws Exception {
		// TODO: Refactor this after rebuilding the SubjectProfessor class
//		if (!professor.getSubject().equals(subject)) {
//			throw new Exception("Subject not applicable for this professor");
//		}
		
//		ratings.add(new Rating(student, professor, subject, score));
//	}
//	
//	public float getAverage(Professor professor) {
//		int totalScore = 0;
//		int scoreCount = 0;
//		for (Rating rating : ratings) {
//			if (rating.getProfessor().equals(professor)) {
//				totalScore += rating.getScore();
//				scoreCount++;
//			}
//		}
//		return (float) totalScore / scoreCount;
//	}
	
	
	
    // steps for working with files:
	// Create file
	// Write each user to file
	// Flush and close the file
	
	//think of how to explain it better (codewise)
	public void saveUsersToFile(String filename) throws Exception{
		// for each student of the class Student in the ArrayList students
		FileOutputStream file = new FileOutputStream(filename);
		ObjectOutputStream oos = new ObjectOutputStream(file);
		for (User user : users) {
			try {
				oos.writeObject(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		oos.close();
		file.close();
//		for (User user : users) {
//			// write the student to users.txt
//			appendUserToFile(filename, user);
//		}
	}
			
// think of explanation 
	
	public ArrayList<User> loadUsersFromFile(String filename) throws Exception {
		FileInputStream file = new FileInputStream(filename);
		ObjectInputStream ois = new ObjectInputStream(file);
		ArrayList<User> users = new ArrayList<User>();
		boolean stop = false;
		while(stop == false) {
			try {
				Object user = ois.readObject();
				users.add((User) user);
			} catch (EOFException e) {
				// EOF means End Of File
				// TODO make this prettier - EOFe are ugly
				stop = true;
			}
		}
		ois.close();
		return users;
	}

	// the method userLogin will return a User
	public User userLogin(String email, String password) throws Exception {
		// we create a new empty(null) User called loggedInUser
		User loggedInUser = null;
		for (User user : this.users) {
			if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
				loggedInUser = user;
			}
		}
		if(loggedInUser.getEmail() != "" ) {
			return loggedInUser;
		} else {
			throw new Exception("Wrong email or password");
		}
		
	}
	
	//method that give us a new id
	public int generateId() {
		int userCount = users.size();
		return userCount++;
	}
	
	// adds a user to the end of a file
	// TODO Ask why does this cause the file to be corrupted - we want to be able to open the file, add a user and close the file many times
	private void appendUserToFile(String filename, User user) throws Exception {
		// Creates a new File object with either the existing users.txt or a new one. 
	    //note:it will exists after the first time i run my program 
		FileOutputStream file = new FileOutputStream(filename, true);
		ObjectOutputStream oos = new ObjectOutputStream(file);
		
		// Creates a new PrintWriter object that will allow us to write and save stuff
		// into our users.txt file
		// java.io.PrintWriter output = new java.io.PrintWriter(file);
		
		try {
			oos.writeObject(user);
		} catch (Exception e) {
			// printStackTrace prints to System.out the last operations the computer
			// tried to execute before the Exception happened
			e.printStackTrace();
		} finally {
			// Whatever is part of the finally block will always be executed
			oos.close();
		}
	}

}
