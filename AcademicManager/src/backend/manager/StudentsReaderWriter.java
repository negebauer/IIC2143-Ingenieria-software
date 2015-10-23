package backend.manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import backend.courses.StudyProgram;
import backend.others.Utilities;
import backend.users.Student;
import backend.users.User.Gender;

/**
 * Class that manages the reading and writing of all the students from the 'database'.
 */
public class StudentsReaderWriter {

	/**
	 * Writes all the students to the professors.txt file.
	 * @param students The students to be written.
	 */
	public static void writeStudents(ArrayList<Student> students) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream (FolderFileManager.adminProfessors);
			PrintStream printStream = new PrintStream(fileOutputStream);
			for (Student student : students) {
				// TODO Save students
				printStream.println("SOMETHING");
			}
			fileOutputStream.close();		
		} catch (IOException ioException) {
			System.err.println ("Unable to write to file");
			System.out.println(ioException);
		}
	}
	
	/**
	 * Reads all the professors from the professors.txt file and returns them in a list.
	 * <br><b>IMPORTANT</b>: This method <b>MUST</b> be called <b>AFTER</b> reading all the study programs.
	 * @param studyPrograms All the study programs of the system.
	 * @return The professors list.
	 */
	public static ArrayList<Student> readStudents(ArrayList<StudyProgram> studyPrograms) {
		ArrayList<Student> students = new ArrayList<Student>();
		
		try {
			FileInputStream fileInputStream = new FileInputStream (FolderFileManager.students);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			String studentString = bufferedReader.readLine();
			while (studentString != null ) {
				String[] arguments = studentString.split("&");
				int id = Integer.parseInt(arguments[0]);
				int yearEntrance = Integer.parseInt(arguments[1]);
				
				ArrayList<StudyProgram> studentStudyPrograms = new ArrayList<StudyProgram>();
				for (String studyProgramNameYearString : arguments[2].split(";;")) {
					String[] studyProgramArguments = studyProgramNameYearString.split(";");
					String name = studyProgramArguments[0];
					int year = Integer.parseInt(studyProgramArguments[1]);
					for (StudyProgram studyProgram : studyPrograms) {
						if (studyProgram.getName() == name && studyProgram.getyearEntrance() == year) {
							studentStudyPrograms.add(studyProgram);
						}
					}
				}
				
				String rut = arguments[3];
				String name = arguments[4];
				String lastnameFather = arguments[5];
				String lastnameMother = arguments[6];
				String address = arguments[7];
				Gender gender = Gender.valueOf(arguments[8]);
				int phone = Integer.parseInt(arguments[9]);
				String birthdayString = arguments[10];
				int yearGraduation = Integer.parseInt(arguments[11]);
				Boolean regularStudent = Boolean.valueOf(arguments[12]);
				
				Student student = new Student(id, yearEntrance, studentStudyPrograms, rut, name, lastnameFather, lastnameMother, address, gender, phone, birthdayString);
				student.setYearGraduation(yearGraduation);
				student.setRegularStudent(regularStudent);
				
				students.add(student);
				studentString = bufferedReader.readLine();
			}
			fileInputStream.close();		
		} catch (IOException ioException) {
			System.err.println ("Unable to read from file");
			System.out.println(ioException);
		}
		return professors;
	}
}
