package System.Users;

import System.Users.Users.User;
import Tools.Enums.Access;
import Tools.Enums.Gender;

public class Professor extends User {

	public Professor(String name, String lastname, String rut, Gender gender, int age) {
		super(name, lastname, rut, Access.Professor);
		this.setAge(age);
		this.setGender(gender);
	}
}
