package TestFolder;

import System.Users.*;
import Tools.Enums.*;

public class TestClass {

	public static void main(String [ ] args) {
		System.out.println("Hello, World");
		
		User usuario = new Admin("Admin", "Bacan", "1", Gender.MASCULINO, 22);
		System.out.println(usuario.getName());
		
		Day day = Day.MONDAY;
		System.out.println(day.getDay());
	}
} 
