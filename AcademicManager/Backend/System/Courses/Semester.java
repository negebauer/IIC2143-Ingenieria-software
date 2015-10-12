package System.Courses;

import java.util.ArrayList;
import java.util.Set;

import Tools.Enums.AcademicSemester;
import Tools.Interfaces.ICourse;
import Tools.Others.Messages;
import Tools.Others.Messages.Message;

/**
 * Class that represents a Semester to be coursed.
 */
public class Semester {
	
	private AcademicSemester semester;
	private int year;
	private ArrayList<Course> courses;
	private int maxCredits;
	private int actualCredits = 0;
	private Set<String> approvedCourses;
	
	/**
	 * Creates a new instance of Semester.
	 * @param semester The academic semester in which this Semester takes place.
	 * @param year The year in which this Semester takes place.
	 */
	public Semester(AcademicSemester semester, int year, int maxCredits, ArrayList<Course> approvedCourses, ArrayList<Course> courses) {
		this.semester = semester;
		this.year = year;
		this.courses = courses != null ? courses : new ArrayList<Course>();
		this.maxCredits = maxCredits;
		
		if (approvedCourses != null){
			for (Course course : approvedCourses){
				this.approvedCourses.add(course.getInitials());
			}
		} else {
			this.approvedCourses = null;
		}
	}
	
	/**
	 * Adds a course to the semester if the conditions for doing so are met.
	 * @param course The course that wants to be added to the semester
	 */
	public AddOrRemoveCourseResponse addCourse(Course course) {
		AddOrRemoveCourseResponse response;
		ArrayList<String> notApprovedRequirements = verifyRequirements(course);
		ArrayList<String> notValidatedCoRequirements = verifyCoRequirements(course);
		int Eclashes = evaluationClashes(course);
		
		if (courses.contains(course)) {
			response = new AddOrRemoveCourseResponse(false, Messages.getMessage(Message.COURSE_WASNT_ADDED_TO_SEMESTER_REPEATED.index()));
		
		} else if (course.getSemester() != AcademicSemester.BOTH && course.getSemester() != semester) {
			response = new AddOrRemoveCourseResponse(false, Messages.getMessage(Message.COURSE_WASNT_ADDED_TO_SEMESTER_NOT_COURSED_IN_SEMESTER.index()));
		
		} else if (actualCredits + course.getCredits() > maxCredits) {
			response = new AddOrRemoveCourseResponse(false, Messages.getMessage(Message.COURSE_WASNT_ADDED_TO_SEMESTER_MAX_CREDITS_EXCEEDED.index()));
		
		} else if (notApprovedRequirements.size() > 0) {
			String notApprovedCourses = "";
			for (String requisito : notApprovedRequirements) {
				notApprovedCourses += requisito + ", \n";
			}
			notApprovedCourses = notApprovedCourses.substring(0, notApprovedCourses.length() - 2) + ".";
			response = new AddOrRemoveCourseResponse(false, Messages.getMessage(Message.COURSE_WASNT_ADDED_TO_SEMESTER_REQUIREMENTS.index(), notApprovedCourses));
		
		} else if (Eclashes > 0) {
			response = new AddOrRemoveCourseResponse(false, Messages.getMessage(Message.COURSE_WASNT_ADDED_TO_SEMESTER_EVALUATION_CLASH.index(), Integer.toString(Eclashes)));
		
		} else if (scheduleClash(course)) {
			response = new AddOrRemoveCourseResponse(false, Messages.getMessage(Message.COURSE_WASNT_ADDED_TO_SEMESTER_SCHEDULE_CLASH.index())); 
		
		} else if (notValidatedCoRequirements.size() > 0) {
			String notValidatedCourses = "";
			for (String coRequisito : notValidatedCoRequirements) {
				notValidatedCourses += coRequisito + ", \n";
			}
			notValidatedCourses = notValidatedCourses.substring(0, notValidatedCourses.length() - 2) + ".";
			response = new AddOrRemoveCourseResponse(false, Messages.getMessage(Message.COURSE_WASNT_ADDED_TO_SEMESTER_COREQUIREMENTS.index(), notValidatedCourses));
					
		} else{
			response = new AddOrRemoveCourseResponse(true, Messages.getMessage(Message.COURSE_WAS_ADDED_TO_SEMESTER.index()));
			addCourseToSemester(course);
		}
		return response;
	}
	
	private ArrayList<String> verifyCoRequirements(Course course) {
		ArrayList<String> nonValidCoRequirements = new ArrayList<String>();
		for (Course coRequirement : course.getCoRequirements()){
			if (!this.getCourses().contains(coRequirement) && !this.approvedCourses.contains(coRequirement.getInitials())){
				nonValidCoRequirements.add(coRequirement.getInitials());
			}
		}
		
		return nonValidCoRequirements;
	}

	/**
	 * Actually adds the course to this semester courses.
	 * @param course The course to be added.
	 */
	private void addCourseToSemester(Course course) {
		courses.add(course);
		actualCredits += course.getCredits();
	}
	
	/**
	 * Checks whether a course has a schedule trouble with the other courses in the semester.
	 * @param course The course that wants to be checked.
	 */
	public boolean scheduleClash(Course course){
		for (Course courseInSemester : this.getCourses()){
			for (ICourse iCourseInSemester : courseInSemester.getCourses()){
				for (ICourse iCourse : course.getCourses()){
					if (iCourse.getSchedule().scheduleClash(iCourseInSemester.getSchedule())){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Verifies if there is any conflict between the evaluations of the semester and a course.
	 * @param course The course that wants to be verified.
	 */
	public int evaluationClashes(Course course){
		int clashes = 0;
		for (Course courseInSemester : this.getCourses()){
			for (Evaluation evaluationInSemester : courseInSemester.getEvaluations()){
				for (Evaluation evaluation : course.getEvaluations()){
					if (evaluationInSemester.getDate() == evaluation.getDate()){
						clashes++;
					}
				}
			}
		}
		return clashes;
	}
	
	/**
	 * Verifies if the requirements of a course, were approved by the student.
	 * @param course The course that will need to be reviewed the requirements.
	 * @return The requirements that have not been approved.
	 */
	public ArrayList<String> verifyRequirements(Course course){
		ArrayList<String> response = new ArrayList<String>();
		
		for (Course requirement : course.getRequirements()){
			if (!approvedCourses.contains(requirement.getInitials())){
				response.add(requirement.getInitials());
			}
		}
		
		return response;
	}
	
	/**
	 * Removes a course of the semester.
	 * @param course The course that wants to be removed.
	 * @return
	 */
	public AddOrRemoveCourseResponse removeCourse(Course course){
		AddOrRemoveCourseResponse response;
		
		if (courses.contains(course)){
			response = new AddOrRemoveCourseResponse(true, Messages.getMessage(Message.COURSE_WAS_REMOVED_OF_SEMESTER.index()));
		} else {
			response = new AddOrRemoveCourseResponse(false, Messages.getMessage(Message.COURSE_WASNT_REMOVED_OF_SEMESTER_NOT_IN_SEMESTER.index()));
		}
		return response;
	}
	
	// Getters and Setters
	/**
	 * @return The courses registered in this semester.
	 */
	public ArrayList<Course> getCourses() {
		return courses;
	}
	
	/**
	 * Set the courses of this semester.
	 * @param courses The courses that will be in the semester.
	 */
	public AddOrRemoveCourseResponse setCourses(ArrayList<Course> courses) {
		AddOrRemoveCourseResponse response;
		int credits = 0;
		
		for (Course course : courses){
			credits += course.getCredits();
		}
		
		if (credits <= maxCredits){
			response = new AddOrRemoveCourseResponse(true, Messages.getMessage(Message.COURSES_WERE_ADDED_TO_SEMESTER.index()));
			this.courses = courses;
		}
		else {
			response = new AddOrRemoveCourseResponse(false, Messages.getMessage(Message.COURSE_WASNT_ADDED_TO_SEMESTER_MAX_CREDITS_EXCEEDED.index()));
		}
		
		return response;
	}
	
	/**
	 * @return The year in which this Semester took place.
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Class used as a data container for answering an add or remove course call.
	 */
	public class AddOrRemoveCourseResponse {
		
		public boolean success;
		public String response;
		
		/**
		 * Creates a new instance of AddOrRemoveCourseResponse.
		 * @param success Whether the course was added/removed or not.
		 * @param response A String containing a User friendly response specifying the result of the addOrRemoveCourse call.
		 */
		public AddOrRemoveCourseResponse(boolean success, String response) {
			this.success = success;
			this.response = response;
		}
	}
	
	/**
	 * @return The maximum of credits this semester can have.
	 */
	public int getMaxCredits(){
		return this.maxCredits;
	}
}
