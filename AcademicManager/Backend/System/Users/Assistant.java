package System.Users;

public class Assistant extends User {

	public Assistant(String name, String lastname, String rut, Gender gender, int age) {
		super(name, lastname, rut, Access.READONLY);
	}
}
