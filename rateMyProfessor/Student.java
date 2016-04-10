package rateMyProfessor;

public class Student extends User {

	private int studentId;

	public Student(int id, String name, String password, int studentId, String email) {
		super(id, name, password, email);
		this.studentId = studentId;
	}
	
}
