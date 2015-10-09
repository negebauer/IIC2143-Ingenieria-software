package System.Courses;

import java.util.ArrayList;
import Tools.Enums.AcademicSemester;

public class Semester {
	
	private AcademicSemester semester;
	private ArrayList<Course> courses;
	private int year;
	
	public void AgregarCurso(Course course) {
		
		if (course.getSemester() == AcademicSemester.BOTH || course.getSemester() == this.semester){
			courses.add(course);
		}
		
		else {
			// error, no se puede tomar un curso en un semestre diferente
		}
		
	}
	
	//getters and setters
	public AcademicSemester getSemester() {
		return semester;
	}
	
	public void setSemester(AcademicSemester semester) {
		this.semester = semester;
	}
	
	public ArrayList<Course> getCursos() {
		return courses;
	}
	
	public void setCursos(ArrayList<Course> courses) {
		this.courses = courses;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
}
