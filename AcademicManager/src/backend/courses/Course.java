package backend.courses;

import java.util.ArrayList;

import backend.enums.AcademicSemester;
import backend.enums.School;
import backend.interfaces.ICourse;
import backend.others.Const;
import backend.others.Messages;
import backend.others.Messages.Message;

/**
 * Class that represents a Course that is dictated.
 */
public class Course {

	private String name;
	private String initials;
	private int section;
	private int credits;
	private String details;
	private School school;
	private AcademicSemester semester;
	private ArrayList<ICourse> courses;
	private ArrayList<Evaluation> evaluations;
	private ArrayList<Course> requirements;
	private ArrayList<Course> coRequirements;
	private Boolean coordinated;
	private Forum forum;

	/**
	 * Creates an instance of Course. Supports default values for every
	 * parameter, therefore null is a valid value for every parameter.
	 * 
	 * @param name
	 *            The name of the Course.
	 * @param initials
	 *            The initials of the Course.
	 * @param section
	 *            The section of the Course.
	 * @param credits
	 *            How much credits this Course is worth.
	 * @param details
	 *            The details of this Course.
	 * @param school
	 *            The school to which this Course belongs.
	 * @param semester
	 *            The semester in which this Course is dictated.
	 * @param courses
	 *            The courses (physical classes) of this Course.
	 * @param evaluations
	 *            The evaluations of the Course.
	 * @param requirements
	 *            The requirements of this Course.
	 * @param coRequirements
	 *            The co requirements of this Course.
	 * @param coordinated
	 *            If the Course is coordinated or not.
	 */
	public Course(String name, String initials, int section, int credits, String details, School school,
			AcademicSemester semester, ArrayList<ICourse> courses, ArrayList<Evaluation> evaluations,
			ArrayList<Course> requirements, ArrayList<Course> coRequirements, Boolean coordinated) {
		this.name = name != null ? name : "NameNil";
		this.initials = initials != null ? initials : "InitialsNil";
		this.section = section > 0 ? section : 0;
		this.credits = credits > -1 ? credits : Const.DEFAULT_CREDITS;
		this.details = details != null ? details : "DetailsNil";
		this.school = school != null ? school : School.defaultSchool();
		this.semester = semester != null ? semester : AcademicSemester.defaultSemester();
		this.courses = courses != null ? courses : new ArrayList<ICourse>();
		this.evaluations = evaluations != null ? evaluations : new ArrayList<Evaluation>();
		this.requirements = requirements != null ? requirements : new ArrayList<Course>();
		this.coRequirements = coRequirements != null ? coRequirements : new ArrayList<Course>();
		this.coordinated = coordinated != null ? coordinated : false;
		this.forum = new Forum();
	}

	/**
	 * Adds a course as a requirement of this course.
	 * 
	 * @param course
	 *            The course that wan to be added as a requirement.
	 */
	public AddOrRemoveRequirementResponse addRequirement(Course course) {
		AddOrRemoveRequirementResponse response;

		if (this.requirements.contains(course)) {
			response = new AddOrRemoveRequirementResponse(false,
					Messages.getMessage(Message.REQUIREMENT_WASNT_ADDED_TO_REQUIREMENTS_REPEATED));
		} else if (this == course) {
			response = new AddOrRemoveRequirementResponse(false,
					Messages.getMessage(Message.REQUIREMENT_WASNT_ADDED_TO_REQUIREMENTS_SAME_COURSE));
		} else {
			response = new AddOrRemoveRequirementResponse(true,
					Messages.getMessage(Message.REQUIREMENT_WAS_ADDED_TO_REQUIREMENTS));
			this.requirements.add(course);
		}

		return response;
	}

	/**
	 * Removes a course of the requirements of this course.
	 * 
	 * @param course
	 *            The course that want to be removed of the requirements.
	 */
	public AddOrRemoveRequirementResponse removeRequirement(Course course) {
		AddOrRemoveRequirementResponse response;

		if (this.requirements.contains(course)) {
			response = new AddOrRemoveRequirementResponse(false,
					Messages.getMessage(Message.COREQUIREMENT_WAS_REMOVED_OF_COREQUIREMENTS));
			this.requirements.remove(course);
		} else {
			response = new AddOrRemoveRequirementResponse(false,
					Messages.getMessage(Message.COREQUIREMENT_WASNT_REMOVED_OF_COREQUIREMENTS_NOT_IN_COREQUIREMENTS));
		}
		return response;
	}

	/**
	 * Adds a course as a requirement of this course.
	 * 
	 * @param course
	 *            The course that wan to be added as a requirement.
	 */
	public AddOrRemoveRequirementResponse addCoRequirement(Course course) {
		AddOrRemoveRequirementResponse response;

		if (this.coRequirements.contains(course)) {
			response = new AddOrRemoveRequirementResponse(false,
					Messages.getMessage(Message.COREQUIREMENT_WASNT_ADDED_TO_COREQUIREMENTS_REPEATED));
		} else if (this == course) {
			response = new AddOrRemoveRequirementResponse(false,
					Messages.getMessage(Message.COREQUIREMENT_WASNT_ADDED_TO_COREQUIREMENTS_SAME_COURSE));
		} else {
			response = new AddOrRemoveRequirementResponse(true,
					Messages.getMessage(Message.COREQUIREMENT_WAS_ADDED_TO_COREQUIREMENTS));
			this.coRequirements.add(course);
		}
		return response;
	}

	/**
	 * Removes a course of the coRequirements of this course.
	 * 
	 * @param course
	 *            The course that want to be removed of the coRequirements.
	 */
	public AddOrRemoveRequirementResponse removeCoRequirement(Course course) {
		AddOrRemoveRequirementResponse response;

		if (this.coRequirements.contains(course)) {
			response = new AddOrRemoveRequirementResponse(false,
					Messages.getMessage(Message.REQUIREMENT_WAS_REMOVED_OF_REQUIREMENTS));
			this.coRequirements.remove(course);
		} else {
			response = new AddOrRemoveRequirementResponse(false,
					Messages.getMessage(Message.REQUIREMENT_WASNT_REMOVED_OF_REQUIREMENTS_NOT_IN_REQUIREMENTS));
		}
		return response;
	}

	// Getters and Setters
	/**
	 * @return The name of this Course.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Modifies this Course's name.
	 * 
	 * @param name
	 *            The new name of this Course.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return The initials of this Course.
	 */
	public String getInitials() {
		return initials;
	}

	/**
	 * Modifies this Course's initials.
	 * 
	 * @param initials
	 *            The new initials of this Course.
	 */
	public void setInitials(String initials) {
		this.initials = initials;
	}

	/**
	 * @return The section of this Course.
	 */
	public int getSection() {
		return section;
	}

	/**
	 * Modifies this Course's section.
	 * 
	 * @param initials
	 *            The new section of this Course.
	 */
	public void setSection(int section) {
		this.section = section;
	}

	/**
	 * @return The details of this Course.
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * Modifies this Course's details.
	 * 
	 * @param details
	 *            The new details of this Course.
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * @return The credits of this Course.
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Modifies this Course's credits.
	 * 
	 * @param credits
	 *            The new credits of this Course.
	 */
	public void setCredits(int credits) {
		this.credits = credits;
	}

	/**
	 * @return The school to which this Course belongs.
	 */
	public School getSchool() {
		return school;
	}

	/**
	 * Modifies the school to which this Course belongs.
	 * 
	 * @param credits
	 *            The new school to which this Course will belong.
	 */
	public void setSchool(School school) {
		this.school = school;
	}

	/**
	 * @return The semester in which this Course is dictated.
	 */
	public AcademicSemester getSemester() {
		return this.semester;
	}

	/**
	 * Modifies the semester in which this Course is dictated.
	 * 
	 * @param credits
	 *            The new semester in which this Course will be dictated.
	 */
	public void setSemester(AcademicSemester semester) {
		this.semester = semester;
	}

	/**
	 * @return The courses (Lecture, Assistantship, Laboratory, etc.) of this
	 *         Course.
	 */
	public ArrayList<ICourse> getCourses() {
		return courses;
	}

	public void addCourse(ICourse icourse) {
		this.courses.add(icourse);
	}

	/**
	 * Modifies the courses of this Course.
	 * 
	 * @param credits
	 *            The new courses of this Course.
	 */
	public void setCourses(ArrayList<ICourse> courses) {
		this.courses = courses;
	}

	/**
	 * @return The evaluations of this Course.
	 */
	public ArrayList<Evaluation> getEvaluations() {
		return evaluations;
	}

	/**
	 * Adds a new evaluation to this Course.
	 * 
	 * @param evaluation
	 *            The new evaluation to be added.
	 */
	public void addEvaluation(Evaluation evaluation) {
		this.evaluations.add(evaluation);
	}

	/**
	 * Removes a new evaluation to this Course.
	 * 
	 * @param evaluation
	 *            The new evaluation to be removed.
	 */
	public void removeEvaluation(Evaluation evaluation) {
		this.evaluations.remove(evaluation);
	}

	/**
	 * Modifies the evaluations of this Course.
	 * 
	 * @param credits
	 *            The new evaluations of this Course.
	 */
	public void setEvaluations(ArrayList<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

	/**
	 * @return The requirements of this course.
	 */
	public ArrayList<Course> getRequirements() {
		return requirements;
	}

	/**
	 * @return The coRequirements of this course.
	 */
	public ArrayList<Course> getCoRequirements() {
		return coRequirements;
	}

	public Boolean isCoordinated() {
		return coordinated;
	}

	public void setCoordinated(Boolean coordinated) {
		this.coordinated = coordinated;
	}

	public Assistantship getAssistantship() {
		for (ICourse icourse : courses) {
			if (icourse instanceof Assistantship) {
				return (Assistantship) icourse;
			}
		}
		return null;
	}

	public Laboratory getLaboratory() {
		for (ICourse icourse : courses) {
			if (icourse instanceof Laboratory) {
				return (Laboratory) icourse;
			}
		}
		return null;
	}

	public Lecture getLecture() {
		for (ICourse icourse : courses) {
			if (icourse instanceof Lecture) {
				return (Lecture) icourse;
			}
		}
		return null;
	}
	
	public Forum getForum() {
		return forum;
	}
	
	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public class AddOrRemoveRequirementResponse {
		public boolean success;
		public String response;

		/**
		 * Creates a new instance of AddOrRemoveRequirementResponse.
		 * 
		 * @param success
		 *            Whether the requirement was added/removed or not.
		 * @param response
		 *            A String containing a User friendly response specifying
		 *            the result of the addOrRemoveRequirement call.
		 */
		public AddOrRemoveRequirementResponse(boolean success, String response) {
			this.success = success;
			this.response = response;
		}
	}
}
