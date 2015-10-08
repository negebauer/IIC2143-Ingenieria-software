package System.Users;

import System.Users.Users.User;
import Tools.Enums.Access;
import Tools.Enums.Gender;

public class Assistant extends User {

	public Assistant(String name, String lastname, String rut, Gender gender, int age) {
		super(name, lastname, rut, Access.Assistant);
	}
}
