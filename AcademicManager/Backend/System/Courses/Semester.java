package System.Courses;

import java.util.ArrayList;
import Tools.Enums.AcademicSemester;

public class Semester {
	
	private AcademicSemester semester;
	private ArrayList<Course> courses;
	private int year;
	
	/**
	 * Add a course to the semester
	 * @param course course that wants to be added to the semester
	 */
	public void AgregarCurso(Course course) {
		
		if ((!courses.contains(course)) && (course.getSemester() == AcademicSemester.BOTH || course.getSemester() == this.semester)){
			courses.add(course);
		}
		
		else {
			// error, you can't add a course of the first semester in the second and backwards.
		}
		
	}
	
	//getters and setters
	
	/**
	 *returns the number of semester of this semester
	 * @return
	 */
	public AcademicSemester getSemester() {
		return semester;
	}
	
	/**
	 * set the number of semester of this semester
	 * @param semester
	 */
	public void setSemester(AcademicSemester semester) {
		this.semester = semester;
	}
	
	/**
	 * returns the courses in the semester
	 * @return
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
	 * returns the year of the semester
	 * @return
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * sets the year of this semester
	 * @param year
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
}
