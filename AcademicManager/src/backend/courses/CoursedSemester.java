package backend.courses;

import java.util.ArrayList;

import backend.enums.AcademicSemester;

public class CoursedSemester {

	private AcademicSemester semester;
	private int year;
	private int maxCredits;
	private ArrayList<Coursed> coursedCourses;
	
	public CoursedSemester(AcademicSemester semester, int year, int maxCredits) {
		this.semester = semester;
		this.year = year;
		this.setMaxCredits(maxCredits);
		coursedCourses = new ArrayList<Coursed>();
	}

	public AcademicSemester getSemester() {
		return semester;
	}

	public void setSemester(AcademicSemester semester) {
		this.semester = semester;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public void addCoursedCourse(Coursed coursedCourse) {
		this.coursedCourses.add(coursedCourse);
	}

	public ArrayList<Coursed> getCoursedCourses() {
		return coursedCourses;
	}

	public void setCoursedCourses(ArrayList<Coursed> coursedCourses) {
		this.coursedCourses = coursedCourses;
	}

	/**
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * @param maxCredits the maxCredits to set
	 */
	public void setMaxCredits(int maxCredits) {
		this.maxCredits = maxCredits;
	}
}
