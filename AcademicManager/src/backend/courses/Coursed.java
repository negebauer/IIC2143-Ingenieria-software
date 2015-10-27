package backend.courses;

import java.util.ArrayList;

import backend.enums.AcademicSemester;
import backend.enums.School;
import backend.interfaces.ICourse;
import backend.others.CopyCreator;

/**
 * Class that represents a Course that was already Coursed.
 */
public class Coursed {

	private String name;
	private String initials;
	private int section;
	private int credits;
	private String details;
	private School school;
	private AcademicSemester semester;
	private int year;
	private ArrayList<ICourse> courses;
	private ArrayList<Evaluation> evaluations;
	private boolean approved;
	private double grade;
	
	/**
	 * Creates a new Coursed instance from a coursed Course.
	 * @param course The Course that was coursed.
	 * @param approved Whether the Course was approved or not.
	 * @param grade The grade obtained in the Course.
	 * @param semester The semester in which the Course was taken.
	 * @param year The year in which this Course was taken.
	 */
	public Coursed(Course course, boolean approved, double grade, AcademicSemester semester, int year) {
		this.name = course.getName();
		this.initials = course.getInitials();
		this.section = course.getSection();
		this.credits = course.getCredits();
		this.details = course.getDetails();
		this.school = course.getSchool();
		this.semester = semester;
		this.year = year;
		this.courses = CopyCreator.copyICourses(course.getCourses());
		this.evaluations = CopyCreator.copyEvaluations(course.getEvaluations());
		this.approved = approved;
		this.grade = grade;
	}

	// TODO Comment. This init is for loading from database
	public Coursed(String name, String initials, int section, int credits, String details, School school, AcademicSemester semester, int year, boolean approved, double grade) {
		this.name = name;
		this.initials = initials;
		this.section = section;
		this.credits = credits;
		this.details = details;
		this.school = school;
		this.semester = semester;
		this.year = year;
		this.approved = approved;
		this.grade = grade;
		this.courses = new ArrayList<ICourse>();
	}

	/**
	 * @return The name of the coursed course.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The initials of the coursed course.
	 */
	public String getInitials() {
		return initials;
	}
	
	/**
	 * @return The section of this Course.
	 */
	public int getSection() {
		return section;
	}

	/**
	 * @return The credits that the coursed course is worth.
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * @return The details of the coursed course.
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @return The school to which the coursed course belongs.
	 */
	public School getSchool() {
		return school;
	}

	/**
	 * @return The semester in which the course was coursed.
	 */
	public AcademicSemester getSemester() {
		return semester;
	}
	
	public int getYear() {
		return year;
	}

	/**
	 * @return The courses of the coursed course.
	 */
	public ArrayList<ICourse> getCourses() {
		return courses;
	}
	
	public void addICourse(ICourse icourse) {
		courses.add(icourse);
	}

	/**
	 * @return The evaluations that took place in the course.
	 */
	public ArrayList<Evaluation> getEvaluations() {
		return evaluations;
	}

	/**
	 * @return Whether the course was approved or not.
	 */
	public boolean isApproved() {
		return approved;
	}

	/**
	 * @return The grade obtained in the course.
	 */
	public double getGrade() {
		return grade;
	}
	
	/**
	 * Modifies the grade obtained in the Course.
	 * @param grade The new grade obtained in the Course.
	 */
	public void setGrade(int grade) {
		this.grade = grade;
	}
}
