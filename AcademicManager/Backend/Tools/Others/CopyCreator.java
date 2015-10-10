package Tools.Others;

import java.util.ArrayList;

import System.Courses.Assistantship;
import System.Courses.Classroom;
import System.Courses.Evaluation;
import System.Courses.Laboratory;
import System.Courses.Lecture;
import System.Courses.Schedule;
import System.Courses.Schedule.DayModuleTuple;
import System.Users.Assistant;
import System.Users.Professor;
import Tools.Interfaces.ICourse;

/**
 * Class that manages the creation of copies.
 * For working with particular instances of an object (example: A coursed course).
 */
public class CopyCreator {
	
	// Copying courses methods
	/**
	 * Copies an ICourse Array List, dereferencing the contained objects.
	 * @param courses The courses to be copied.
	 * @return A copy of courses.
	 */
	public static ArrayList<ICourse> copyICourses(ArrayList<ICourse> courses) {
		ArrayList<ICourse> copiedCourses = new ArrayList<ICourse>();
		for (ICourse course : courses) {
			// Yes, we have to do type casting, argh..
			if (course instanceof Assistantship) {
				Assistantship copiedAssistantship = copyAssistantship((Assistantship) course);
				copiedCourses.add(copiedAssistantship);
			} else if (course instanceof Laboratory) {
				Laboratory copiedLaboratory = copyLaboratory((Laboratory) course);
				copiedCourses.add(copiedLaboratory);
			} else if (course instanceof Lecture) {
				Lecture copiedLecture = copyLecture((Lecture) course);
				copiedCourses.add(copiedLecture);
			}
		}
		return copiedCourses;
	}
	
	/**
	 * Copies all the data from an assistantship into a new instance of Assistanship.
	 * @param original The original Assistanship.
	 * @return The copy of the original Assistantship.
	 */
	public static Assistantship copyAssistantship(Assistantship original) {
		ArrayList<Assistant> copiedAssistants = copyAssistants(original.getAssistants());
		Classroom copiedClassroom = copyClassroom(original.getClassroom());
		Schedule copiedSchedule = copySchedule(original.getSchedule());
		
		return new Assistantship(copiedAssistants, copiedClassroom, copiedSchedule);
	}
	
	/**
	 * Copies all the data from a laboratory into a new instance of Laboratory.
	 * @param original The original Laboratory.
	 * @return The copy of the original Laboratory.
	 */
	public static Laboratory copyLaboratory(Laboratory original) {
		ArrayList<Professor> copiedProfessors = copyProfessors(original.getProfessors());
		Classroom copiedClassroom = copyClassroom(original.getClassroom());
		Schedule copiedSchedule = copySchedule(original.getSchedule());
		
		return new Laboratory(copiedProfessors, copiedClassroom, copiedSchedule);
	}
	
	/**
	 * Copies all the data from a Lecture into a new instance of Lecture.
	 * @param original The original Lecture.
	 * @return The copy of the original Lecture.
	 */
	public static Lecture copyLecture(Lecture original) {
		ArrayList<Professor> copiedProfessors = copyProfessors(original.getProfessors());
		Classroom copiedClassroom = copyClassroom(original.getClassroom());
		Schedule copiedSchedule = copySchedule(original.getSchedule());
		
		return new Lecture(copiedProfessors, copiedClassroom, copiedSchedule);
	}
	
	// Copying evaluations methods
	/**
	 * Copies an array list of evaluations into a new array list of new instances of evaluations.
	 * @param evaluations The original evaluations.
	 * @return The copy of the original evaluations.
	 */
	public static ArrayList<Evaluation> copyEvaluations(ArrayList<Evaluation> evaluations) {
		ArrayList<Evaluation> copiedEvaluations = new ArrayList<Evaluation>();
		for (Evaluation originalEvaluation : evaluations) {
			Classroom copiedClassroom = copyClassroom(originalEvaluation.getClassroom());
			Evaluation copiedEvaluation = new Evaluation(originalEvaluation.getCourseEvaluation(), copiedClassroom, Utilities.getStringFromDate(originalEvaluation.getDate()));
			copiedEvaluations.add(copiedEvaluation);
		}
		return copiedEvaluations;
	}
	
	// General copies
	/**
	 * Copies an array list of professors into a new array list of new instances of professors.
	 * @param original The original professors.
	 * @return The copy of the original professors.
	 */
	public static ArrayList<Professor> copyProfessors(ArrayList<Professor> original) {
		ArrayList<Professor> copiedProfessors = new ArrayList<Professor>();
		for (Professor originalProfessor : original) {
			Professor copiedProfessor = new Professor(null, originalProfessor.getName(), originalProfessor.getLastnameFather(), originalProfessor.getLastnameMother(), null, null, 0, null);
			copiedProfessors.add(copiedProfessor);
		}
		return copiedProfessors;
	}
	
	/**
	 * Copies an array list of assistants into a new array list of new instances of assistants.
	 * @param original The original assistants.
	 * @return The copy of the original assistants.
	 */
	public static ArrayList<Assistant> copyAssistants(ArrayList<Assistant> original) {
		ArrayList<Assistant> copiedAssistants = new ArrayList<Assistant>();
		for (Assistant originalAssistant : original) {
			Assistant copiedAssistant = new Assistant(null, originalAssistant.getName(), originalAssistant.getLastnameFather(), originalAssistant.getLastnameMother(), null, null, 0, null);
			copiedAssistants.add(copiedAssistant);
		}
		return copiedAssistants;
	}
	
	/**
	 * Copies a classroom into a new instance of classroom.
	 * @param original The original classroom.
	 * @return The copy of the original classroom.
	 */
	public static Classroom copyClassroom(Classroom original) {
		return new Classroom(original.getInitials(), original.getSchool(), original.getCampus(), original.getSize());
	}
	
	/**
	 * Copies a schedule into a new instance of schedule.
	 * In order to do it, copies every DayModuleTuple into a new instance of DayModuleTuple. 
	 * @param original The original schedule.
	 * @return The copy of the original schedule.
	 */
	public static Schedule copySchedule(Schedule original) {
		Schedule copiedSchedule = new Schedule();
		for (DayModuleTuple originalDayModuleTuple : original.getModules()){
			DayModuleTuple copiedDayModuleTuple = copiedSchedule.new DayModuleTuple(originalDayModuleTuple.getDay(), originalDayModuleTuple.getModule());
			copiedSchedule.addModule(copiedDayModuleTuple);
		}
		return copiedSchedule;
	}
}
