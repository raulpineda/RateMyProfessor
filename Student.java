package Final.v2;

public class Student extends User {

	private int studentId;

	public Student(int id, String name, String password, int studentId) {
		super(id, name, password);
		this.studentId = studentId;
	}
	
}
