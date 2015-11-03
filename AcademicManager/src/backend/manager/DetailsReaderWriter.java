package backend.manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import backend.courses.Course;

/**
 * Class that manages the reading of all the courses details.
 */
public class DetailsReaderWriter {

	/* File format
	details
	*/
	
	/**
	 * Writes all the courses details to the details folder.
	 */
	public static void writeDetails(ArrayList<Course> courses) {
		try {
			for (Course course : courses) {
				String detailsString = FolderFileManager.adminCourseDetails + "/" + course.getInitials() + "_" + course.getSection() + ".txt";
				FileOutputStream fileOutputStream = new FileOutputStream (detailsString);
				PrintStream printStream = new PrintStream(fileOutputStream);
				printStream.println(course.getDetails());
				fileOutputStream.close();
			}
		} catch (IOException ioException) {
			System.err.println ("Unable to write to file");
			System.out.println(ioException);
		}
	}

	/**
	 * Reads all the courses details from the details folder.
	 */
	public static void readDetails(ArrayList<Course> allCourses) {
		try {
			for (Course course : allCourses) {
				String detailsString = FolderFileManager.adminCourseDetails + "/" + course.getInitials() + "_" + course.getSection() + ".txt";
				if (course.isCoordinated()) {
					detailsString = FolderFileManager.adminCourseDetails + "/" + course.getInitials() + "_" + 1 + ".txt";
				}
				FileInputStream fileInputStream = new FileInputStream(detailsString);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
				String details = bufferedReader.readLine();
				course.setDetails(details);
				fileInputStream.close();
			}
		} catch (IOException ioException) {
			System.err.println ("Unable to write to file");
			System.out.println(ioException);
		}
	}
	
}
