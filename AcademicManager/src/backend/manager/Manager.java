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
import backend.courses.StudyProgram;
import backend.others.Messages;
import backend.others.Messages.SupportedLanguage;
import backend.users.Admin;
import backend.users.Assistant;
import backend.users.Professor;
import backend.users.Student;

/**
 * [Singleton] Main class that does what the application user requires.
 * Contains all the objects of the program.
 * Data container.
 */
public class Manager {

	public final static Manager INSTANCE = new Manager();
	
	public ArrayList<Classroom> classrooms = new ArrayList<Classroom>();
	public ArrayList<Course> courses = new ArrayList<Course>();
	public ArrayList<StudyProgram> studyPrograms = new ArrayList<StudyProgram>();

	public ArrayList<Admin> admins = new ArrayList<Admin>();
	public ArrayList<Assistant> assistants = new ArrayList<Assistant>();
	public ArrayList<Professor> professors = new ArrayList<Professor>();
	public ArrayList<Student> students = new ArrayList<Student>();
	
	/**
	 * Creates the instance of manager.
	 */
	private Manager() {
		
	}

	/**
	 * Loads all the data from the `database`.
	 */
	public void loadData() {
		admins = AdminReaderWriter.readAdmins();
		assistants = AssistantsReaderWriter.readAssistants();
		classrooms = ClassroomReaderWriter.readClasrooms();
		courses = CoursesReaderWriter.readCourses();
		professors = ProfessorsReaderWriter.readProfessors();
		studyPrograms = StudyProgramsReaderWriter.readStudyPrograms(courses);
		CourseCoursesReaderWriter.readCourseCourses(courses, assistants, professors, classrooms);
		EvaluationsReaderWriter.readCoursesEvaluations(courses, classrooms);
		CourseCoRequirementsReaderWriter.readCoursesCoRequirements(courses);
		CourseRequirementsReaderWriter.readCoursesRequirements(courses);
	}
	
	/**
	 * Writes all the data to the `database`.
	 */
	public void saveData() {
		AdminReaderWriter.writeAdmins(admins);
		AssistantsReaderWriter.writeAssistants(assistants);
		ClassroomReaderWriter.writeClassrooms(classrooms);
		CoursesReaderWriter.writeCourses(courses);
		ProfessorsReaderWriter.writeProfessors(professors);
//		TODO
//		StudyProgramsReaderWriter.writerStudyPrograms(studyPrograms);
		CourseCoursesReaderWriter.writeCoursesCourses(courses);
		EvaluationsReaderWriter.writeCoursesEvaluations(courses);
		CourseCoRequirementsReaderWriter.writeCoursesCoRequirements(courses);
		CourseRequirementsReaderWriter.writeCoursesRequirements(courses);
	}
	
	/**
	 * Switches language between Spanish and English for the software.
	 */
	public String changeLanguage() {
		String returnString = "44";
		try {
			FileInputStream fileInputStream = new FileInputStream (FolderFileManager.language);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			SupportedLanguage language = Messages.SupportedLanguage.valueOf(bufferedReader.readLine());
			fileInputStream.close();

			FileOutputStream fileOutputStream = new FileOutputStream (FolderFileManager.language);
			PrintStream printStream = new PrintStream(fileOutputStream);

			switch (language) {
			case ENGLISH:
				returnString =  "ES";
				printStream.println("SPANISH");
			case SPANISH:
				returnString =  "EN";
				printStream.println("ENGLISH");
			default:
				returnString =  "44";
			}

			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
		return returnString;
	}

}
