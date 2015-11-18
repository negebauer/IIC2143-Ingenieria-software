package backend.manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import backend.courses.Classroom;
import backend.courses.Course;
import backend.courses.Evaluation;
import backend.others.Utilities;

public class EvaluationsReaderWriter {

	/*
	 * File format course initials&course evaluation&classroom initials&date
	 * string
	 */

	/**
	 * Writes all the course evaluations to the .txt file.
	 * 
	 * @param allCourse
	 *            All the courses to write their co requirements.
	 */
	public static void writeCoursesEvaluations(ArrayList<Course> allCourses) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(FolderFileManager.adminEvaluations);
			PrintStream printStream = new PrintStream(fileOutputStream);
			for (Course course : allCourses) {
				for (Evaluation evaluation : course.getEvaluations()) {
					printStream.print(course.getInitials());
					printStream.print("&");
					printStream.print(evaluation.getCourseEvaluation().toString());
					printStream.print("&");
					printStream.print(evaluation.getClassroom().getInitials());
					printStream.print("&");
					printStream.print(Utilities.getStringFromDate(evaluation.getDate()));
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
	 * Reads all the course evaluations from the .txt file and adds them to each
	 * corresponding course.
	 * 
	 * @param allCourses
	 *            All the courses of the system.
	 * @param allClassrooms
	 *            All the classrooms of the system.
	 */
	public static void readCoursesEvaluations(ArrayList<Course> allCourses, ArrayList<Classroom> allClassrooms) {
		try {
			FileInputStream fileInputStream = new FileInputStream(FolderFileManager.adminEvaluations);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			String courseEvaluationString = bufferedReader.readLine();
			while (courseEvaluationString != null) {
				String[] arguments = courseEvaluationString.split("&");

				String courseInitials = arguments[0];
				Evaluation.CourseEvaluation courseEvaluation = Evaluation.CourseEvaluation.valueOf(arguments[1]);
				String classRoomInitials = arguments[2];
				String dateString = arguments[3];
				Classroom classroom = new Classroom("", null, null, 0);

				for (Classroom possibleClassroom : allClassrooms) {
					if (possibleClassroom.getInitials().equals(classRoomInitials)) {
						classroom = possibleClassroom;
					}
				}

				Evaluation evaluation = new Evaluation(courseEvaluation, classroom, dateString);

				for (Course course : allCourses) {
					if (course.getInitials().equals(courseInitials)) {
						course.addEvaluation(evaluation);
					}
				}

				courseEvaluationString = bufferedReader.readLine();
			}
			fileInputStream.close();
		} catch (IOException ioException) {
			System.err.println("Unable to read from file");
			System.out.println(ioException);
		}
	}
}
