package backend.manager;

import java.util.ArrayList;

import backend.courses.Course;

/**
 * Searches for a course.
 */
public class CourseSearcher {

	/**
	 * Searches for a course that contains any of the given parameters.
	 * 
	 * @param initials
	 * @param name
	 * @return
	 */
	public static ArrayList<Course> searchCourses(String searchParam) {
		ArrayList<Course> courses = new ArrayList<Course>();
		if (searchParam == "" || searchParam == null) {
			return Manager.INSTANCE.courses;
		}
		for (Course course : Manager.INSTANCE.courses) {
			if (course.getInitials().contains(searchParam) || course.getName().contains(searchParam)) {
				courses.add(course);
			}
		}
		return courses;
	}
}
