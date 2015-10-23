package backend.manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import backend.courses.Assistantship;
import backend.courses.Classroom;
import backend.courses.Course;
import backend.courses.Laboratory;
import backend.courses.Lecture;
import backend.courses.Schedule;
import backend.courses.Schedule.Day;
import backend.courses.Schedule.DayModuleTuple;
import backend.courses.Schedule.Module;
import backend.enums.AcademicSemester;
import backend.interfaces.ICourse;
import backend.users.Assistant;
import backend.users.Professor;

/**
 * Class that manages the reading and writing of all the courses from the 'database' for each course.
 */
public class CourseCoursesReaderWriter {

	/* File format
		initials&section&semester&TypeOfCourse&assistants or professors rut&classroom initials&day:module;day:module
		
		initials&section&semester&ASSISTANTSHIP&rut1;rut2&classroomInitials&dia1:modulo1;dia2:modulo2
		initials&section&semester&LABORATORY&rut1;rut2&classroomInitials&dia1:modulo1;dia2:modulo2
		initials&section&semester&LECTURE&rut1;rut2&classroomInitials&dia1:modulo1;dia2:modulo2
	*/

	/**
	 * Writes all the course courses to the courseCourses.txt file.
	 * @param allCourse All the courses to write their icourses
	 */
	public static void writeCoursesCourses(ArrayList<Course> allCourses) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream (FolderFileManager.adminCourseCourses);
			PrintStream printStream = new PrintStream(fileOutputStream);
			for (Course course : allCourses) {
				for (ICourse icourse : course.getCourses()) {
					printStream.print(course.getInitials());
					printStream.print("&");
					printStream.print(course.getSection());
					printStream.print("&");
					printStream.print(course.getSemester());
					printStream.print("&");
					Boolean moreThanOneProfessorOrAssistant = false;
					if (icourse instanceof Assistantship) {
						printStream.print("ASSISTANTSHIP");
						printStream.print("&");
						for (Assistant assistant : ((Assistantship) icourse).getAssistants()) {
							if (moreThanOneProfessorOrAssistant) {
								printStream.print(";");
							}
							printStream.print(assistant.getRut());
							moreThanOneProfessorOrAssistant = true;
						}
					} else if (icourse instanceof Laboratory) {
						printStream.print("LABORATORY");
						printStream.print("&");
						for (Professor professor : ((Laboratory) icourse).getProfessors()) {
							if (moreThanOneProfessorOrAssistant) {
								printStream.print(";");
							}
							printStream.print(professor.getRut());
							moreThanOneProfessorOrAssistant = true;
						}
					} else if (icourse instanceof Lecture) {
						printStream.print("LECTURE");
						printStream.print("&");
						for (Professor professor : ((Lecture) icourse).getProfessors()) {
							if (moreThanOneProfessorOrAssistant) {
								printStream.print(";");
							}
							printStream.print(professor.getRut());
							moreThanOneProfessorOrAssistant = true;
						}
					}
					printStream.print("&");
					printStream.print(icourse.getClassroom().getInitials());
					printStream.print("&");
					Boolean moreThanOneDayModuleTuple = false;
					for (DayModuleTuple dayModuleTuple : icourse.getSchedule().getModules()) {
						if (moreThanOneDayModuleTuple) {
							printStream.print(";");
						}
						printStream.print(dayModuleTuple.day);
						printStream.print(":");
						printStream.print(dayModuleTuple.module);
						moreThanOneDayModuleTuple = true;
					}
					printStream.println();
				}
			}
			fileOutputStream.close();		
		} catch (IOException ioException) {
			System.err.println ("Unable to write to file");
			System.out.println(ioException);
		}
	}
	
	/**
	 * Reads all the course courses from the courseCourses.txt file and returns them in a list.
	 * @param allCourses All the courses to reference the ICourse from them.
	 * @param allAssistants All the assistants of the system.
	 * @param allProfessors All the professors of the system.
	 * @param allClassroooms All the classrooms of the system.
	 */
	public static void readCourseCourses(ArrayList<Course> allCourses, ArrayList<Assistant> allAssistants, ArrayList<Professor> allProfessors, ArrayList<Classroom> allClassroooms) {
		try {
			FileInputStream fileInputStream = new FileInputStream (FolderFileManager.adminCourseCourses);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			String icourseString = bufferedReader.readLine();
			System.out.print(icourseString);
			while (icourseString != null ) {
				String[] arguments = icourseString.split("&");
				
				String initials = arguments[0];
				int section = Integer.parseInt(arguments[1]);
				AcademicSemester semester = AcademicSemester.valueOf(arguments[2]);
				String typeOfCourse = arguments[3];
				String assitantsOrProfessorsString = arguments[4];
				String classroomInitials = arguments[5];
				String dayModuleStrings = arguments[6];
				
				Schedule schedule = new Schedule();
				ArrayList<DayModuleTuple> dayModuleTuples = new ArrayList<DayModuleTuple>();
				for (String dayModuleString : dayModuleStrings.split(";")) {
					String[] dayModuleArguments = dayModuleString.split(":");
					Day day = Day.valueOf(dayModuleArguments[0]);
					Module module = Module.valueOf(dayModuleArguments[1]);
					DayModuleTuple dayModuleTuple = schedule.new DayModuleTuple(day, module);
					dayModuleTuples.add(dayModuleTuple);
				}
				
				for (DayModuleTuple dayModuleTuple : dayModuleTuples) {
					schedule.addModule(dayModuleTuple);
				}
				
				Classroom classroom = new Classroom(null, null, null, 0);
				for (Classroom possibleClassroom : allClassroooms) {
					if (possibleClassroom.getInitials().equals(classroomInitials)) {
						classroom = possibleClassroom;
					}
				}

				ICourse icourse = new Assistantship(null, null, null);

				if (typeOfCourse.equals("ASSISTANTSHIP")) {
					ArrayList<Assistant> assistants = new ArrayList<Assistant>();
					for (String rut : assitantsOrProfessorsString.split(";")) {
						for (Assistant assistant : allAssistants) {
							if (assistant.getRut().equals(rut)) {
								assistants.add(assistant);
							}
						}
					}
					Assistantship assistantship = new Assistantship(assistants, classroom, schedule);
					icourse = assistantship;
				} else if (typeOfCourse.equals("LABORATORY")) {
					ArrayList<Professor> professors = new ArrayList<Professor>();
					for (String rut : assitantsOrProfessorsString.split(";")) {
						for (Professor professor : allProfessors) {
							if (professor.getRut().equals(rut)) {
								professors.add(professor);
							}
						}
					}
					Laboratory laboratory = new Laboratory(professors, classroom, schedule);
					icourse = laboratory;
				} else if (typeOfCourse.equals("LECTURE")) {
					ArrayList<Professor> professors = new ArrayList<Professor>();
					for (String rut : assitantsOrProfessorsString.split(";")) {
						System.out.println(rut);
						for (Professor professor : allProfessors) {
							if (professor.getRut().equals(rut)) {
								professors.add(professor);
							}
						}
					}
					Lecture lecture = new Lecture(professors, classroom, schedule);
					icourse = lecture;
				}

				for (Course possibleCourse : allCourses) {
					if (possibleCourse.getInitials().equals(initials) && possibleCourse.getSection() == section && possibleCourse.getSemester() == semester) {
						possibleCourse.addCourse(icourse);
					}
				}

				icourseString = bufferedReader.readLine();
			}
			fileInputStream.close();		
		} catch (IOException ioException) {
			System.err.println ("Unable to read from file");
			System.out.println(ioException);
		}
	}
}
