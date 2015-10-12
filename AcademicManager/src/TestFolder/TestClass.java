package TestFolder;

import java.util.ArrayList;
import java.util.Date;

import System.Courses.Schedule;
import Tools.Others.Messages;
import Tools.Others.Utilities;
import Tools.Others.Messages.Message;

public class TestClass {

	public static void main(String [ ] args) {
		System.out.println("Hello, World");
		
//		User usuario = new Admin("Admin", "Bacan", "1", User.Gender.MALE, 22);
//		System.out.println(usuario.getName());
		
		Schedule.Day day = Schedule.Day.MONDAY;
		System.out.println(day.getDayString());
		
		Date date;
		date = Utilities.getDateFromString("10.09.1993 18:58");
		System.out.println(Utilities.getStringFromDate(date));
	
		System.out.println(Messages.getMessage(Message.COURSE_WASNT_ADDED_TO_SEMESTER_REPEATED.index()));
		
		System.out.println(Utilities.getDateFromString("10.09.1993" + " 00:00"));
		
		String response = "\n\nHola\nque\ntal\n\nhay dos lineas antes?\n\n\n3?\nfunciono?";
		response = cleanNewLineCharExcessFromString(response);
		System.out.println(response);
		//for (String s : response.split("\n")) {
		//	System.out.println(s + "awdawdawdaw");
		//}
		//response = String.join("\n", response.split("\n"));
		//System.out.println(response);
	}
	
	public static String cleanNewLineCharExcessFromString(String stringToClean) {
		ArrayList<String> cleanedString = new ArrayList<String>();
		for (String character : stringToClean.split("\n")) {
//			System.out.println("AH: " + character);
			if (cleanedString.size() == 0 && !character.isEmpty()) {
				cleanedString.add(character);
			} else if (cleanedString.size() > 0 && !(cleanedString.get(cleanedString.size() - 1).isEmpty() && character.isEmpty())) {
//				System.out.println("AH: " + character);
				cleanedString.add(character);
			}
		}
		return String.join("\n", cleanedString);
	}
} 
