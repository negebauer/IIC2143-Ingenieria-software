package backend.manager;

import java.util.ArrayList;

import backend.courses.Classroom;
import backend.courses.Course;
import backend.courses.Coursed;
import backend.courses.Curriculum;
import backend.courses.StudyProgram;
import backend.users.Admin;
import backend.users.Assistant;
import backend.users.Professor;
import backend.users.Student;

/**
 * [Singleton] Main class that does what the application user requires. 
 */
public class Manager {

	private final static Manager INSTANCE = new Manager();
	
	private ArrayList<Classroom> classrooms = new ArrayList<Classroom>();
	private ArrayList<Course> courses = new ArrayList<Course>();
	private ArrayList<Coursed> courseds = new ArrayList<Coursed>();
	private ArrayList<Curriculum> curriculums = new ArrayList<Curriculum>();
	private ArrayList<StudyProgram> studyPrograms = new ArrayList<StudyProgram>();

	private ArrayList<Admin> admins = new ArrayList<Admin>();
	private ArrayList<Assistant> assistants = new ArrayList<Assistant>();
	private ArrayList<Professor> professors = new ArrayList<Professor>();
	private ArrayList<Student> students = new ArrayList<Student>();
	
	/**
	 * Loads all the data from the `database`.
	 */
	public void loadData() {
		//TODO AdminsReaderWriter
//		INSTANCE.admins = AdminsReaderWriter
		INSTANCE.assistants = AssistantsReaderWriter.readAssistants();
		INSTANCE.professors = ProfessorsReaderWriter.readProfessors();
		//TODO StudentsReaderWriter
//		INSTANCE.students = StudentsReaderWriter
		
		INSTANCE.courses = CoursesReaderWriter.readCourses();
		
	}
	
	/**
	 * Writes all the data to the `database`.
	 */
	public void writeData() {
		CoursesReaderWriter.writeCourses(INSTANCE.courses);
	}

	/**
	 * Creates the instance of manager.
	 */
	private Manager() {
		//TODO Load files and stuff.
	}
	
}
