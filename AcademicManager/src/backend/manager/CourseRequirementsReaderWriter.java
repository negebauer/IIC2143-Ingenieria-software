package backend.manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import backend.courses.Course;

public class CourseRequirementsReaderWriter {

	/*
	 * File format initials course&initials requirement
	 */

	/**
	 * Writes all the course requirements to the .txt file.
	 * 
	 * @param allCourse
	 *            All the courses to write their requirements.
	 */
	public static void writeCoursesRequirements(ArrayList<Course> allCourses) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(FolderFileManager.adminCourseRequirements);
			PrintStream printStream = new PrintStream(fileOutputStream);
			for (Course course : allCourses) {
				for (Course courseRequired : course.getRequirements()) {
					printStream.print(course.getInitials());
					printStream.print("&");
					printStream.print(courseRequired.getInitials());
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
	 * Reads all the course requirements from the .txt file and adds them to
	 * each corresponding course.
	 * 
	 * @param allCourses
	 *            All the courses of the system.
	 */
	public static void readCoursesRequirements(ArrayList<Course> allCourses) {
		try {
			FileInputStream fileInputStream = new FileInputStream(FolderFileManager.adminCourseRequirements);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			String courseRequirementString = bufferedReader.readLine();
			while (courseRequirementString != null) {
				String[] arguments = courseRequirementString.split("&");

				String courseInitials = arguments[0];
				String courseRequiredInitials = arguments[1];

				for (Course course : allCourses) {
					for (Course courseRequired : allCourses) {
						if (course.getInitials().equals(courseInitials)
								&& courseRequired.getInitials().equals(courseRequiredInitials)) {
							course.addRequirement(courseRequired);
						}
					}
				}

				courseRequirementString = bufferedReader.readLine();
			}
			fileInputStream.close();
		} catch (IOException ioException) {
			System.err.println("Unable to read from file");
			System.out.println(ioException);
		}
	}
}
