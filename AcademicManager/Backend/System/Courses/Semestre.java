package System.Courses;

import java.util.ArrayList;
import Tools.Enums.Semester;

public class Semestre {
	
	private Semester semester;
	private ArrayList<Course> cursos;
	private int year;
	
	public void AgregarCurso(Course Curso) {
		
		if (Curso.getSemester() == Semester.BOTH || Curso.getSemester() == this.semester){
			cursos.add(Curso);
		}
		
		else {
			// error, no se puede tomar un curso en un semestre diferente
		}
		
	}
	
	//getters and setters
	public Semester getSemester() {
		return semester;
	}
	
	public void setSemester(Semester semester) {
		this.semester = semester;
	}
	
	public ArrayList<Course> getCursos() {
		return cursos;
	}
	
	public void setCursos(ArrayList<Course> courses) {
		cursos = courses;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
}
