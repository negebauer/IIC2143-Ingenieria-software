package backend.manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import backend.courses.Course;
import backend.enums.AcademicSemester;
import backend.enums.School;

/**
 * Class that manages the reading and writing of all the courses from the 'database'.
 */
public class CoursesReaderWriter {

	/**
	 * Writes all the courses to the courses.txt file.
	 * @param courses The courses to be written.
	 */
	public static void writeCourses(ArrayList<Course> courses) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream (FolderFileManager.adminCourses);
			PrintStream printStream = new PrintStream(fileOutputStream);
			for (Course course : courses) {
				printStream.print(course.getName());
				printStream.print("&");
				printStream.print(course.getInitials());
				printStream.print("&");
				printStream.print(course.getSection());
				printStream.print("&");
				printStream.print(course.getCredits());
				printStream.print("&");
				printStream.print(course.getSchool());
				printStream.print("&");
				printStream.print(course.getSemester());
				printStream.print("&");
				printStream.print(course.isCoordinated());
				printStream.println();
			}
			fileOutputStream.close();		
		} catch (IOException ioException) {
			System.err.println ("Unable to write to file");
			System.out.println(ioException);
		}
	}
	
	/**
	 * Reads all the assistants from the assistants.txt file and returns them in a list.
	 * @return The assistants list.
	 */
	public static ArrayList<Course> readCourses() {
		ArrayList<Course> courses = new ArrayList<Course>();
		
		try {
			FileInputStream fileInputStream = new FileInputStream (FolderFileManager.adminCourses);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			String courseString = bufferedReader.readLine();
			while (courseString != null ) {
				String[] arguments = courseString.split("&");
				String name = arguments[0];
				String initials = arguments[1];
				int section = Integer.parseInt(arguments[2]);
				int credits = Integer.parseInt(arguments[3]);
				School school = School.valueOf(arguments[4]);
				AcademicSemester semester = AcademicSemester.valueOf(arguments[5]);
				Boolean coordinated = Boolean.valueOf(arguments[6]);
				Course course = new Course(name, initials, section, credits, "", school, semester, null, null, null, null, coordinated);
				courses.add(course);
				courseString = bufferedReader.readLine();
			}
			fileInputStream.close();		
		} catch (IOException ioException) {
			System.err.println ("Unable to read from file");
			System.out.println(ioException);
		}
		return courses;
	}
}
