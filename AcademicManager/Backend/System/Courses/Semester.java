package System.Courses;

import java.util.ArrayList;
import Tools.Enums.AcademicSemester;
import Tools.Others.Messages;
import Tools.Others.Messages.Message;

/**
 * Class that represents a Semester to be coursed.
 */
public class Semester {
	
	private AcademicSemester semester;
	private int year;
	private ArrayList<Course> courses;
	
	/**
	 * Creates a new instance of Semester.
	 * @param semester The academic semester in which this Semester takes place.
	 * @param year The year in which this Semester takes place.
	 */
	public Semester(AcademicSemester semester, int year) {
		this.semester = semester;
		this.year = year;
		this.courses = new ArrayList<Course>();
	}
	
	/**
	 * Adds a course to the semester
	 * @param course course that wants to be added to the semester
	 */
	public AddCourseResponse addCourse(Course course) {
		AddCourseResponse response;
		if (courses.contains(course)) {
			response = new AddCourseResponse(false, Messages.getMessage(Message.COURSE_WASNT_ADDED_TO_SEMESTER_REPEATED.index()));
		} else if (course.getSemester() != AcademicSemester.BOTH && course.getSemester() != semester) {
			response = new AddCourseResponse(false, Messages.getMessage(Message.COURSE_WASNT_ADDED_TO_SEMESTER_REPEATED.index()));
		} else {
			addCourse(course);
			response = new AddCourseResponse(true, Messages.getMessage(Message.COURSE_WAS_ADDED_TO_SEMESTER.index()));
		}
		return response;
	}
	
	// Getters and Setters
	/**
	 * @return The courses registered in this semester.
	 */
	public ArrayList<Course> getCursos() {
		return courses;
	}
	
	/**
	 * set the courses of this semester
	 * @param courses
	 */
	public void setCursos(ArrayList<Course> courses) {
		this.courses = courses;
	}
	
	/**
	 * @return The year in which this Semester took place.
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Class used as a data container for answering an add course call.
	 */
	public class AddCourseResponse {
		public boolean success;
		public String response;
		
		/**
		 * Creates a new instance of AddCourseResponse.
		 * @param success Whether the course was added or not.
		 * @param response A String containing a User friendly response specifying the result of the addCourse call.
		 */
		public AddCourseResponse(boolean success, String response) {
			this.success = success;
			this.response = response;
		}
	}
}
