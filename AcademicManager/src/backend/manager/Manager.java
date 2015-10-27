package backend.manager;

import java.util.ArrayList;

import backend.courses.Classroom;
import backend.courses.Course;
import backend.courses.StudyProgram;
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
//		TODO
//		studyPrograms = StudyProgramsReaderWriter.readStudyPrograms();
		CourseCoursesReaderWriter.readCourseCourses(courses, assistants, professors, classrooms);
	}
	
	/**
	 * Switches language between Spanish and English for the software.
	 */
	public String changeLanguage() {
		
		return "";
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
	}

}
