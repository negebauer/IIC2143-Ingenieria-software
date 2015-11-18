package backend.manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import backend.courses.Classroom;
import backend.courses.Classroom.Campus;
import backend.enums.School;

/**
 * Class that manages the reading and writing of all the classrooms from the
 * 'database'.
 */
public class ClassroomReaderWriter {

	/*
	 * File format initials&school&campus&size
	 */

	/**
	 * Writes all the classrooms to the classrooms.txt file.
	 * 
	 * @param classrooms
	 *            The classrooms to be written.
	 */
	public static void writeClassrooms(ArrayList<Classroom> classrooms) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(FolderFileManager.adminClassrooms);
			PrintStream printStream = new PrintStream(fileOutputStream);
			for (Classroom classroom : classrooms) {
				printStream.print(classroom.getInitials());
				printStream.print("&");
				printStream.print(classroom.getSchool().toString());
				printStream.print("&");
				printStream.print(classroom.getCampus().toString());
				printStream.print("&");
				printStream.print(classroom.getSize());
				printStream.println();
			}
			fileOutputStream.close();
		} catch (IOException ioException) {
			System.err.println("Unable to write to file");
			System.out.println(ioException);
		}
	}

	/**
	 * Reads all the classrooms from the classrooms.txt file and returns them in
	 * a list.
	 * 
	 * @return The classrooms list.
	 */
	public static ArrayList<Classroom> readClasrooms() {
		ArrayList<Classroom> clasrooms = new ArrayList<Classroom>();
		try {
			FileInputStream fileInputStream = new FileInputStream(FolderFileManager.adminClassrooms);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			String classroomString = bufferedReader.readLine();
			while (classroomString != null) {
				String[] arguments = classroomString.split("&");
				String initials = arguments[0];
				School school = School.valueOf(arguments[1]);
				Campus campus = Campus.valueOf(arguments[2]);
				int size = Integer.parseInt(arguments[3]);
				Classroom classroom = new Classroom(initials, school, campus, size);
				clasrooms.add(classroom);
				classroomString = bufferedReader.readLine();
			}
			fileInputStream.close();
		} catch (IOException ioException) {
			System.err.println("Unable to read from file");
			System.out.println(ioException);
		}
		return clasrooms;
	}
}
