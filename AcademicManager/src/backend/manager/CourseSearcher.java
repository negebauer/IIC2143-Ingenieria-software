package backend.manager;

import java.util.ArrayList;

import backend.courses.Course;

/**
 * Searches for a course.
 */
public class CourseSearcher {

	/**
	 * Searches for a course that contains any of the given parameters.
	 * @param initials
	 * @param name
	 * @return
	 */
	public static ArrayList<Course> searchCourses(String initials, String name) {
		ArrayList<Course> courses = new ArrayList<Course>();
		initials = initials != null ? initials : "";
		name = name != null ? name : "";
		for (Course course : Manager.INSTANCE.courses) {
			if (course.getInitials().contains(initials) || course.getName().contains(name)) {
				courses.add(course);
			}
		}
		return courses;
	}
}
