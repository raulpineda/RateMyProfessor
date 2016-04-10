package rateMyProfessor;
import java.io.Serializable;
import java.util.Scanner;


public abstract class User implements Serializable {
	
	public User(String email, String name, String password, int id) {	
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
	}
	
	// TODO Read chapter 17 about how to make objects serializable
	private static final long serialVersionUID = 1L;
		
	// TODO Add email property, getter, setter and add it to the toString serializer
	private int id;
	private String name;
	private String password;
	private String email;

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	//read chapter 11
	@Override
	public String toString() {
		return new StringBuffer( " Id: ")
				.append( this.id )
				.append( " Name: ")
				.append( this.name)
				.append( " Password: ")
				.append( this.password )
				.append("Email:")
				.append(this.email).toString();
		
		//adds all things together and converts it to just one string
		
				
				
	}
}
