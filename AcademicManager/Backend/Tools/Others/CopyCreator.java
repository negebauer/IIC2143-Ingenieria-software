package Tools.Others;

import java.util.ArrayList;

import System.Courses.Assistantship;
import System.Courses.Classroom;
import System.Courses.Laboratory;
import System.Courses.Professorship;
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
			} else if (course instanceof Professorship) {
				Professorship copiedProfessorship = copyProfessorship((Professorship) course);
				copiedCourses.add(copiedProfessorship);
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
		ArrayList<Assistant> originalAssistants = original.getAssistants();
		ArrayList<Assistant> copiedAssistants = new ArrayList<Assistant>();
		for (Assistant originalAssistant : originalAssistants) {
			Assistant copiedAssistant = new Assistant(originalAssistant.getName(), originalAssistant.getLastname(), originalAssistant.getRut(), originalAssistant.getGender(), originalAssistant.getAge());
			copiedAssistants.add(copiedAssistant);
		}
		
		Classroom originalClassroom = original.getClassroom();
		Classroom copiedClassroom = new Classroom(originalClassroom.getInitials(), originalClassroom.getSchool(), originalClassroom.getCampus());
		
		Schedule originalSchedule = original.getSchedule();
		Schedule copiedSchedule = new Schedule();
		for (DayModuleTuple originalDayModuleTuple : originalSchedule.getModules()){
			DayModuleTuple copiedDayModuleTuple = copiedSchedule.new DayModuleTuple(originalDayModuleTuple.getDay(), originalDayModuleTuple.getModule());
			copiedSchedule.addModule(copiedDayModuleTuple);
		}
		
		return new Assistantship(copiedAssistants, copiedClassroom, copiedSchedule);
	}
	
	/**
	 * Copies all the data from a laboratory into a new instance of Laboratory.
	 * @param original The original Laboratory.
	 * @return The copy of the original Laboratory.
	 */
	public static Laboratory copyLaboratory(Laboratory original) {
		ArrayList<Professor> originalProfessors = original.getProfessors();
		ArrayList<Professor> copiedProfessors = new ArrayList<Professor>();
		for (Professor originalProfessor : originalProfessors) {
			Professor copiedProfessor = new Professor(originalProfessor.getName(), originalProfessor.getLastname(), originalProfessor.getRut(), originalProfessor.getGender(), originalProfessor.getAge());
			copiedProfessors.add(copiedProfessor);
		}
		
		Classroom originalClassroom = original.getClassroom();
		Classroom copiedClassroom = new Classroom(originalClassroom.getInitials(), originalClassroom.getSchool(), originalClassroom.getCampus());
		
		Schedule originalSchedule = original.getSchedule();
		Schedule copiedSchedule = new Schedule();
		for (DayModuleTuple originalDayModuleTuple : originalSchedule.getModules()){
			DayModuleTuple copiedDayModuleTuple = copiedSchedule.new DayModuleTuple(originalDayModuleTuple.getDay(), originalDayModuleTuple.getModule());
			copiedSchedule.addModule(copiedDayModuleTuple);
		}
		
		return new Laboratory(copiedProfessors, copiedClassroom, copiedSchedule);
	}
	
	/**
	 * Copies all the data from a professorship into a new instance of Professorship.
	 * @param original The original Professorship.
	 * @return The copy of the original Professorship.
	 */
	public static Professorship copyProfessorship(Professorship original) {
		ArrayList<Professor> originalProfessors = original.getProfessors();
		ArrayList<Professor> copiedProfessors = new ArrayList<Professor>();
		for (Professor originalProfessor : originalProfessors) {
			Professor copiedProfessor = new Professor(originalProfessor.getName(), originalProfessor.getLastname(), originalProfessor.getRut(), originalProfessor.getGender(), originalProfessor.getAge());
			copiedProfessors.add(copiedProfessor);
		}
		
		Classroom originalClassroom = original.getClassroom();
		Classroom copiedClassroom = new Classroom(originalClassroom.getInitials(), originalClassroom.getSchool(), originalClassroom.getCampus());
		
		Schedule originalSchedule = original.getSchedule();
		Schedule copiedSchedule = new Schedule();
		for (DayModuleTuple originalDayModuleTuple : originalSchedule.getModules()){
			DayModuleTuple copiedDayModuleTuple = copiedSchedule.new DayModuleTuple(originalDayModuleTuple.getDay(), originalDayModuleTuple.getModule());
			copiedSchedule.addModule(copiedDayModuleTuple);
		}
		
		return new Professorship(copiedProfessors, copiedClassroom, copiedSchedule);
	}
}
