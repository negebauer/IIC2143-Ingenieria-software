package backend.courses;

import java.util.ArrayList;

import backend.enums.AcademicSemester;

public class CoursedSemester {

	private AcademicSemester semester;
	private int year;
	private ArrayList<Coursed> coursedCourses;

	public CoursedSemester(AcademicSemester semester, int year) {
		this.semester = semester;
		this.year = year;
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

	public double getGrade() {
		double total = 0;
		for (Coursed coursed : coursedCourses) {
			total += coursed.getGrade();
		}
		return total / coursedCourses.size();
	}
}
