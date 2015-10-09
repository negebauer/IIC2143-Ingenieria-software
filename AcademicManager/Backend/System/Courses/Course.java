package System.Courses;

import java.util.ArrayList;

import Tools.Enums.School;
import Tools.Enums.AcademicSemester;
import Tools.Interfaces.ICourse;
import Tools.Others.Const;

/**
 * Class that represents a Course that is dictated.
 */
public class Course {

	private String name;
	private String initials;
	private int credits;
	private String details;
	private School school;
	private AcademicSemester semester;
	private ArrayList<ICourse> courses;
	
	/**
	 * Creates an instance of Course.
	 * @param name The name of the Course.
	 * @param initials The initials of the Course.
	 * @param credits How much credits this Course is worth.
	 * @param details The details of this Course.
	 * @param school The school to which this Course belongs.
	 * @param semester The semester in which this Course is dictated.
	 * @param courses The courses (physical classes) of this Course.
	 */
	public void initCourse(String name, String initials, int credits, String details, School school, AcademicSemester semester, ArrayList<ICourse> courses) {
		this.name = name != null ? name : "NameNil";
		this.initials = initials != null ? initials : "InitialsNil";
		this.credits = credits > -1 ? credits : Const.DEFAULT_CREDITS;
		this.details = details != null ? details : "DetailsNil";
		this.school = school != null ? school : null;
		this.semester = semester != null ? semester : AcademicSemester.defaultSemester();
		this.courses = courses != null ? courses : new ArrayList<ICourse>();
	}
	
	//Getters and Setters
	/**
	 * @return The name of this Course.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Modifies this Course's name.
	 * @param name The new name of this Course.
	 */
	public void setName(String name) {
		this.name =  name;
	}
	
	/**
	 * @return The initials of this Course.
	 */
	public String getInitials() {
		return initials;
	}
	
	/**
	 * Modifies this Course's initials.
	 * @param initials The new initials of this Course.
	 */
	public void setInitials(String initials) {
		this.initials = initials;
	}
	
	/**
	 * @return The details of this Course.
	 */
	public String getDetails() {
		return details;
	}
	
	/**
	 * Modifies this Course's details.
	 * @param details The new details of this Course.
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
	 * @param credits The new credits of this Course.
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
	 * @param credits The new school to which this Course will belong.
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
	 * @param credits The new semester in which this Course will be dictated.
	 */
	public void setSemester(AcademicSemester semester) {
		this.semester = semester;
	}

	/**
	 * @return The courses (Professorship, Assistantship, Laboratory, etc.) of this Course.
	 */
	public ArrayList<ICourse> getCourses() {
		return courses;
	}

	/**
	 * Modifies the courses of this Course.
	 * @param credits The new courses of this Course.
	 */
	public void setCourses(ArrayList<ICourse> courses) {
		this.courses = courses;
	}
}
