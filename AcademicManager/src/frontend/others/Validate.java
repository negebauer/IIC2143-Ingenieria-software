package frontend.others;

import java.util.ArrayList;
import java.util.Collections;

import backend.courses.Course;
import backend.courses.Semester;
import backend.courses.StudyProgram;
import backend.courses.Schedule.DayModuleTuple;
import backend.interfaces.ICourse;
import backend.manager.Manager;

public final class Validate {

	/***
	 * Revisa si el rut es valido
	 * @param rut
	 * @return
	 */
	public static boolean checkRUT(String rut) {

		char num = rut.charAt(rut.length() - 1);
		char[] aux = rut.substring(0, rut.length() - 1).toCharArray();

		int sum = 0;
		ArrayList<Integer> digits = new ArrayList<Integer>();
		for (char i : aux)
			digits.add(i - '0');

		Collections.reverse(digits);
		int k = 2;
		for (int i : digits) {
			sum += i * k;
			k++;
			if (k > 7)
				k = 2;
		}

		int ver = 11 - sum % 11;
		char fn = 'k';

		switch (ver) {
		case 11:
			fn = '0';
			break;
		case 10:
			fn = 'k';
			break;
		default:
			fn = Integer.toString(ver).toCharArray()[0];
			break;
		}

		return num == fn;
	}
	
	/**
	 * Revisa si un curso pertence a una carrera
	 * @param initials
	 * @param carreer
	 * @return
	 */
	public static boolean checkCourse(String initials, String carreer) {
		for (StudyProgram sp : Manager.INSTANCE.studyPrograms) {
			if (sp.getName().equals(carreer)) {
				for (Semester s : sp.getSemesters()) {
					for (Course c : s.getCourses()) {
						if (c.getInitials().equals(initials)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Revisa si el curso es el seleccionado
	 * @param course
	 * @param selection
	 * @return
	 */
	public static boolean checkCourse(Course course, String[] selection) {		
		String initials = selection[0];
		int section = Integer.valueOf(selection[1]);
		String name = selection[2];		
		return course.getInitials().equals(initials) && course.getSection() == section && course.getName().equals(name);
	}
	
	/**
	 * Revisa si hay tope de horario en una lista
	 * @param course
	 * @param courses
	 * @return
	 */
	public static boolean checkOverlap(Course course, ArrayList<Course> courses) {
		if(course.getCourses() != null) {
			for (ICourse i : course.getCourses()) {						
				ArrayList<DayModuleTuple> t = i.getSchedule().getModules();
				ArrayList<DayModuleTuple> s;											
				for (Course c : courses) {						
					s = c.getLecture().getSchedule().getModules();
					for (DayModuleTuple sm : s) {
						for (DayModuleTuple tm : t) {
							if (sm.day.equals(tm.day) && sm.module.equals(tm.module)) {
								return true;
							}
						}
					}
				}	
			}
		}
		return false;
	}
	
	/**
	 * Retorna el curso con el que hay tope de horario
	 * @param course
	 * @param courses
	 * @return
	 */
	public static Course getOverlap(Course course, ArrayList<Course> courses) {
		if(course.getCourses() != null) {
			for (ICourse i : course.getCourses()) {						
				ArrayList<DayModuleTuple> t = i.getSchedule().getModules();
				ArrayList<DayModuleTuple> s;											
				for (Course c : courses) {						
					s = c.getLecture().getSchedule().getModules();
					for (DayModuleTuple sm : s) {
						for (DayModuleTuple tm : t) {
							if (sm.day.equals(tm.day) && sm.module.equals(tm.module)) {
								return c;
							}
						}
					}
				}	
			}
		}
		return null;
	}
}