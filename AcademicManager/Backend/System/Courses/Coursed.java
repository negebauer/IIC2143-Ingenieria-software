package System.Courses;

import java.util.ArrayList;

import Tools.Enums.AcademicSemester;
import Tools.Enums.School;
import Tools.Interfaces.ICourse;
import Tools.Others.CopyCreator;

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
	 */
	public Coursed(Course course, boolean approved, double grade, AcademicSemester semester) {
		this.name = course.getName();
		this.initials = course.getInitials();
		this.section = course.getSection();
		this.credits = course.getCredits();
		this.details = course.getDetails();
		this.school = course.getSchool();
		this.semester = semester;
		this.courses = CopyCreator.copyICourses(course.getCourses());
		this.evaluations = CopyCreator.copyEvaluations(course.getEvaluations());
		this.approved = approved;
		this.grade = grade;
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

	/**
	 * @return The courses of the coursed course.
	 */
	public ArrayList<ICourse> getCourses() {
		return courses;
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
