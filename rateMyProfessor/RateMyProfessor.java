package rateMyProfessor;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RateMyProfessor {
	
	static Database db = new Database();

	public static void main(String[] args) throws Exception {
		
		// Database db = new Database();
		// Scanner input = new Scanner(System.in);
		
		 welcome();
			
		// Previous stuff below
		
		 //Seed data -> Not to be used in production
//		Student viktorija = new Student("viktorija@email.com", "Viktorija", "123", 1); 
//		db.registerStudent(viktorija);
//		System.out.println(viktorija);
//
//		
//		Student alice = new Student("alice@email.com", "Alice", "123", 2); 
//		db.registerStudent(alice);
//		
//		
//		Student tom = new Student("tom@email.com", "Tom", "lkasjdi", 3); 
//		db.registerStudent(tom);	
//		
		// try to save all my users to users.txt
//		try {
//			db.saveUsersToFile("users.txt");
//			System.out.println("yay");
//		} catch (Exception e) {
//			// if it fails, print oops
//			System.out.println("Oops");
//		}
//		//just to see if it works. Will delete it later 
//		// ArrayList<User> my_users = db.loadUsersFromFile("users.txt");
//		List<User> my_users = db.getUsers();
//		// for each user of the class User in the List my_users
//		for ( User user : my_users) {
//			// Get the user name and print it
//			System.out.println(user.getName());
//		}
		
		

		

	}

	private static void welcome() throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome please choose an option");
		System.out.println("1. Register");
		System.out.println("2. Login");
		System.out.println("3. Exit");
		
		int option = input.nextInt();
		
		switch(option) {
			case 1: registerStudent();
					break;
			case 2: User me = doLogin();
					if(me.getId() > 0) {
						if (me instanceof Student) {
							// The user is a Student
							// TODO
							// Make a method that lists the actions (methods) that a student can take
							// Create those empty methods
						} else {
							// The user is not a Student, therefore, it is a Professor
							// TODO
							// Make a method that lists the actions (methods) that a professor can take
							// Create those empty methods
						}
					
						// IF user is a Student
						// studentActions();
						// ELSE IF user is a Professor
						// professorActions();
						// ELSE
						// End Program
					} else {
						// End Program
					}
					break;
			case 3: //endProgram();
					System.out.println("end!");
					break;
			default: welcome(); 
					 break;
		}
		input.close();
	}
	
	private static void registerStudent() throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.print("Email: ");
		String email = input.nextLine();
		System.out.print("Name: ");
		String name = input.nextLine();
		System.out.print("Password: ");
		String password = input.nextLine();
		System.out.print("Repeat your password: ");
	
		String repeatPassword = input.nextLine();
		
		
		if (password.equals(repeatPassword)) {
			int id = db.generateId();
			Student student = new Student(email, name, password, id);
			// Call the registerStudent method from the database class, and pass our student variable as an argument
			db.registerStudent(student);
		}
		else {
			registerStudent();
		}
		input.close();
	}
	
//	if(condition) {} else {}
	
	
	private static User doLogin() {
		// We create an empty user(User) variable so we ensure the return type of the method
		User user = null;
		Scanner input = new Scanner(System.in);
		System.out.println("Please, enter your email:");
		String email = input.nextLine();
		System.out.println("Please, enter your password:");
		String password = input.nextLine();
		try {
			user = db.userLogin(email, password);
		} catch (Exception e) {
			System.out.println("Wrong username or password.");
			doLogin();
		} 
		input.close();
		return user;
	}

}
