package System.Users;

public class Professor extends User {
	
	public Professor(String name, String lastname, String rut, Gender gender, int age) {
		super(name, lastname, rut, Access.EDITOR);
	}
}
