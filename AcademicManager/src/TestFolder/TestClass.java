package TestFolder;

import java.util.Date;

import System.Courses.Schedule;
import Tools.Others.Messages;
import Tools.Others.Utilities;
import javafx.fxml.FXMLLoader;
import Tools.Others.Messages.Message;

public class TestClass {

	public static void main(String [ ] args) {
		System.out.println("Hello, World");
		
//		TODO Delete this class
		
		Schedule.Day day = Schedule.Day.MONDAY;
		System.out.println(day.getDayString());
		
		Date date;
		date = Utilities.getDateFromString("10.09.1993 18:58");
		System.out.println(Utilities.getStringFromDate(date));
	
		System.out.println(Messages.getMessage(Message.COURSE_WASNT_ADDED_TO_SEMESTER_REPEATED.index()));
		
		System.out.println(Utilities.getDateFromString("10.09.1993" + " 00:00"));
		
		String response = "\n\nHola\nque\ntal\n\nhay dos lineas antes?\n\n\n3?\nfunciono?";
		response = Utilities.cleanNewLineCharExcessFromString(response);
		System.out.println(response);
		for (String s : response.split("\n")) {
			System.out.println(s + "awdawdawdaw");
		}
		response = String.join("\n", response.split("\n"));
		
		public void initTestView() {
			try {
				FXMLLoader loader = new FXMLLoader();
								
			} catch {
				
			} finally {
				
			}
		}
		System.out.println(response);
	}
} 
