package backend.others;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import backend.courses.Course;
import backend.courses.Coursed;
import backend.courses.Curriculum;
import backend.courses.Semester;
import backend.courses.StudyProgram;

public class Utilities {

	/**
	 * Returns a Date objected parsed from a given string representing a date.
	 * <pre>
	 * {@code
	 * Date date = getDateFromString("10.09.1993 18:58")
	 * System.out.println(date.toString());
	 * >> Fri Sep 10 18:58:00 CLT 1993
	 * }
	 * </pre>
	 * @param dateString The string representing a date in the format "dd.MM.yyyy HH:mm".
	 * @return A date object
	 * @throws ParseException
	 */
	public static Date getDateFromString(String dateString) {
		SimpleDateFormat dateFormat =  new SimpleDateFormat ("dd.MM.yyyy HH:mm");
		try { 
			Date date = dateFormat.parse(dateString); 
			return date;
		} catch (ParseException e) { 
			System.out.println(dateString + " is unparseable using " + dateFormat); 
		}
		return new Date();
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String getStringFromDate(Date date) {
		SimpleDateFormat dateFormat =  new SimpleDateFormat ("dd.MM.yyyy HH:mm", Locale.ENGLISH);
		return dateFormat.format(date);
	}

	/**
	 * Cleans the extra new line characters (\n) from a String.
	 * @param stringToClean The string to be cleaned.
	 * @return The cleaned string.
	 */
	public static String cleanNewLineCharExcessFromString(String stringToClean) {
		ArrayList<String> cleanedString = new ArrayList<String>();
		for (String character : stringToClean.split("\n")) {
			if (cleanedString.size() == 0 && !character.isEmpty()) {
				cleanedString.add(character);
			} else if (cleanedString.size() > 0 && !(cleanedString.get(cleanedString.size() - 1).isEmpty() && character.isEmpty())) {
				cleanedString.add(character);
			}
		}
		return String.join("\n", cleanedString);
	}

	/**
	 * Return approved courses or uncoursed courses depending of isFinished param
	 * @param program
	 * @param curriculum
	 * @param isFinished
	 * @return
	 */
	public static ArrayList<Course> getFinichedCourses(StudyProgram program, Curriculum curriculum, boolean isFinished) {

		ArrayList<Course> finished = new ArrayList<Course>();

		for(Semester semester : program.getSemesters())
			for(Course course : semester.getCourses()) {

				boolean check = false;	
				for(Coursed coursed : curriculum.getCoursedCourses())
					if(course.getInitials() == coursed.getInitials() && coursed.isApproved()) {
						check = true;
						break;
					}					
				if(check == isFinished)
					finished.add(course);
			}					
		return finished;
	}

	public static ArrayList<String> getCoursesList(ArrayList<Course> courses) {

		ArrayList<String> coursesList = new ArrayList<String>();	
		for(Course course : courses)
			coursesList.add(course.getInitials() + " " + course.getName());

		return coursesList;
	}

	public static ArrayList<String> getCoursesList(StudyProgram program, Curriculum curriculum, boolean isFinished) {

		ArrayList<Course> courses = getFinichedCourses(program, curriculum, isFinished);
		
		ArrayList<String> coursesList = new ArrayList<String>();	
		for(Course course : courses)
			coursesList.add(course.getInitials() + " " + course.getName());

		return coursesList;
	}
}
