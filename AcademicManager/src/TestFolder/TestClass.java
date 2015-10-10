package TestFolder;

import java.util.Date;

import System.Courses.Schedule;
import System.Users.Admin;
import System.Users.User;
import Tools.Others.Messages;
import Tools.Others.Utilities;
import Tools.Others.Messages.Message;

public class TestClass {

	public static void main(String [ ] args) {
		System.out.println("Hello, World");
		
		User usuario = new Admin("Admin", "Bacan", "1", User.Gender.MALE, 22);
		System.out.println(usuario.getName());
		
		Schedule.Day day = Schedule.Day.MONDAY;
		System.out.println(day.getDayString());
		
		Date date;
		date = Utilities.getDateFromString("10.09.1993 18:58");
		System.out.println(Utilities.getStringFromDate(date));
	
		System.out.println(Messages.getMessage(Message.COURSE_WASNT_ADDED_TO_SEMESTER_REPEATED.index()));
	}
} 
