package System.Users;

import System.Users.Users.User;
import Tools.Enums.Access;
import Tools.Enums.Gender;

public class Admin extends User {

	public Admin(String name, String lastname, String rut, Gender gender, int age) {
		super(name, lastname, rut, Access.Admin);
		// TODO Auto-generated constructor stub
	}
}
