package backend.manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import backend.courses.Course;

public class CourseCoRequirementsReaderWriter {

	/*
	 * File format initials course&initials co requirement
	 */

	/**
	 * Writes all the course co requirements to the .txt file.
	 * 
	 * @param allCourse
	 *            All the courses to write their co requirements.
	 */
	public static void writeCoursesCoRequirements(ArrayList<Course> allCourses) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(FolderFileManager.adminCourseCoRequirements);
			PrintStream printStream = new PrintStream(fileOutputStream);
			for (Course course : allCourses) {
				for (Course courseCoRequired : course.getCoRequirements()) {
					printStream.print(course.getInitials());
					printStream.print("&");
					printStream.print(courseCoRequired.getInitials());
					printStream.println("");
				}
			}
			fileOutputStream.close();
		} catch (IOException ioException) {
			System.err.println("Unable to write to file");
			System.out.println(ioException);
		}
	}

	/**
	 * Reads all the course co requirements from the .txt file and adds them to
	 * each corresponding course.
	 * 
	 * @param allCourses
	 *            All the courses of the system.
	 */
	public static void readCoursesCoRequirements(ArrayList<Course> allCourses) {
		try {
			FileInputStream fileInputStream = new FileInputStream(FolderFileManager.adminCourseCoRequirements);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			String courseCoRequirementString = bufferedReader.readLine();
			while (courseCoRequirementString != null) {
				String[] arguments = courseCoRequirementString.split("&");

				String courseInitials = arguments[0];
				String courseCoRequiredInitials = arguments[1];

				for (Course course : allCourses) {
					for (Course courseCoRequired : allCourses) {
						if (course.getInitials().equals(courseInitials)
								&& courseCoRequired.getInitials().equals(courseCoRequiredInitials)) {
							course.addCoRequirement(courseCoRequired);
						}
					}
				}

				courseCoRequirementString = bufferedReader.readLine();
			}
			fileInputStream.close();
		} catch (IOException ioException) {
			System.err.println("Unable to read from file");
			System.out.println(ioException);
		}
	}
}
