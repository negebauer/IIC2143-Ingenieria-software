package System.Users;

public class Admin extends User {

	public Admin(String name, String lastname, String rut, Gender gender, int age) {
		super(name, lastname, rut, Access.ADMIN);
	}
}
