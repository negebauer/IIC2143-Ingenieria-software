package System.Courses;

import java.util.ArrayList;

import Tools.Enums.School;

public class StudyProgram {

	private School school;
	private int anoIngreso;
	private ArrayList<Semestre> semestres;
	
	/**
	 * Creates an instance of StudyProgram
	 * @param anoIn studies start year
	 * @param s semesters in the Study Program
	 * @param scho school to which this Study Program belongs
	 */
	public StudyProgram(int anoIn, ArrayList<Semestre> s, School scho){
		this.anoIngreso = anoIn;
		this.setSemestres(s);
		this.setSchool(scho);
	}

	//Getters and Setters
	
	/**
	 * returns the studies start year
	 * @return
	 */
	public int getAnoIngreso() {
		return anoIngreso;
	}
	
	/**
	 * set the studies start year
	 * @param anoIngreso studies start year
	 */
	public void setAnoIngreso(int anoIngreso) {
		this.anoIngreso = anoIngreso;
	}
	
	/**
	 * 
	 * @return
	 */
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public ArrayList<Semestre> getSemestres() {
		return semestres;
	}

	public void setSemestres(ArrayList<Semestre> semestres) {
		this.semestres = semestres;
	}
	
	
}
