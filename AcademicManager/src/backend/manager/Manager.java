package backend.manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;

import backend.courses.Classroom;
import backend.courses.Course;
import backend.courses.Evaluation;
import backend.courses.Schedule;
import backend.courses.Semester;
import backend.courses.StudyProgram;
import backend.enums.AcademicSemester;
import backend.others.Messages;
import backend.others.Messages.SupportedLanguage;
import backend.users.Admin;
import backend.users.Assistant;
import backend.users.Professor;
import backend.users.Student;
import backend.users.User;

/**
 * [Singleton] Main class that does what the application user requires.
 * Contains all the objects of the program.
 * Data container.
 */
public class Manager {

	public final static Calendar CALENDAR = Calendar.getInstance();
	public final static Manager INSTANCE = new Manager();
	
	public User currentUser;
	
	public ArrayList<Classroom> classrooms = new ArrayList<Classroom>();
	public ArrayList<Course> courses = new ArrayList<Course>();
	public ArrayList<StudyProgram> studyPrograms = new ArrayList<StudyProgram>();

	public ArrayList<Admin> admins = new ArrayList<Admin>();
	public ArrayList<Assistant> assistants = new ArrayList<Assistant>();
	public ArrayList<Professor> professors = new ArrayList<Professor>();
	public ArrayList<Student> students = new ArrayList<Student>();
	
	public String courseDetailsToShow;
	public Semester currentSemester;
	
	public StudyProgram currentEditingStudyProgram;
	public Course currentEditignCourse;
	public Schedule currentEditingSchedule;
	public Evaluation currentEditingEvaluation;
	
	/**
	 * Creates the instance of manager.
	 */
	private Manager() {
		
	}
	
	/**
	 * Reloads all the data from the server
	 */
	public void reloadData() {
		// TODO Implement server first
	}

	/**
	 * Loads all the data from the `database`.
	 */
	public void loadData() {
		System.out.println("Loading data...");
		
		if (getMonth() <= 6 && getDay() <= 19) {
			currentSemester = new Semester(AcademicSemester.FIRST, getYear(), 0, null, null);
		} else {
			currentSemester = new Semester(AcademicSemester.SECOND, getYear(), 0, null, null);
		}
		
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
		DetailsReaderWriter.readDetails(courses);
		students = StudentsReaderWriter.readStudents(courses, studyPrograms, classrooms, currentSemester, professors, assistants);
		
		System.out.println("Data loaded!");
	}
	
	/**
	 * Writes all the data to the `database`.
	 */
	public void saveData() {
		System.out.println("Starting to save data...");
		AdminReaderWriter.writeAdmins(admins);
		AssistantsReaderWriter.writeAssistants(assistants);
		ClassroomReaderWriter.writeClassrooms(classrooms);
		CoursesReaderWriter.writeCourses(courses);
		ProfessorsReaderWriter.writeProfessors(professors);
		StudyProgramsReaderWriter.writeStudyPrograms(studyPrograms);
		CourseCoursesReaderWriter.writeCoursesCourses(courses);
		EvaluationsReaderWriter.writeCoursesEvaluations(courses);
		CourseCoRequirementsReaderWriter.writeCoursesCoRequirements(courses);
		CourseRequirementsReaderWriter.writeCoursesRequirements(courses);
		DetailsReaderWriter.writeDetails(courses);
		StudentsReaderWriter.writeStudents(students);
		
		System.out.println("Data saved!");
	}
	
	/**
	 * Switches language between Spanish and English for the software.
	 */
	public void changeLanguage() {
		try {
			FileInputStream fileInputStream = new FileInputStream (FolderFileManager.language);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			SupportedLanguage language = Messages.SupportedLanguage.valueOf(bufferedReader.readLine());
			fileInputStream.close();

			FileOutputStream fileOutputStream = new FileOutputStream (FolderFileManager.language);
			PrintStream printStream = new PrintStream(fileOutputStream);

			switch (language) {
			case ENGLISH:
				printStream.println(SupportedLanguage.SPANISH.toString());
			case SPANISH:
				printStream.println(SupportedLanguage.ENGLISH.toString());
			default:
				break;
			}

			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}

	public int getNewStudentID() {
		int maxID = 0;
		for (Student student : students) {
			if (student.getId() > maxID) {
				maxID = student.getId();
			}
		}
		return maxID + 1;
	}
	
	public StudyProgram getStudyProgramForName(String name) {
		for (StudyProgram studyProgram : studyPrograms) {
			if (studyProgram.getName().equals(name)) {
				return studyProgram;
			}
		}
		return null;
	}
	
	public static int getYear() {
		return CALENDAR.get(Calendar.YEAR);
	}
	
	public static int getMonth() {
		return CALENDAR.get(Calendar.MONTH);
	}
	
	public static int getDay() {
		return CALENDAR.get(Calendar.DAY_OF_MONTH);
	}
}
