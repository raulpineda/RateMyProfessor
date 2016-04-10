package rateMyProfessor;

public class Professor extends User {
	
	private Subject subject; // TODO convert to List<Subject>

	public Professor(int id, String name, String password, Subject subject, String email) {
		super(id, name, password, email);
		this.subject = subject;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	

}
