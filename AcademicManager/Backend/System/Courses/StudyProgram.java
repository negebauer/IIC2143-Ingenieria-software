package System.Courses;

import java.util.ArrayList;

import Tools.Enums.School;

public class StudyProgram {

	private School school;
	private int anoIngreso;
	private ArrayList<Semester> semestres;
	
	/**
	 * Creates an instance of StudyProgram
	 * @param anoIn studies start year
	 * @param s semesters in the Study Program
	 * @param scho school to which this Study Program belongs
	 */
	public StudyProgram(int anoIn, ArrayList<Semester> s, School scho){
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
	 * returns the school to which this study program belongs
	 * @return
	 */
	public School getSchool() {
		return school;
	}
	
	/**
	 * set the school to which this study program belongs
	 * @param school the school to which this study program belong
	 */
	public void setSchool(School school) {
		this.school = school;
	}

	/**
	 * return the semesters that are in this study program
	 * @return
	 */
	public ArrayList<Semester> getSemestres() {
		return semestres;
	}
	
	/**
	 * set the semesters that are in this study program
	 * @param semestres
	 */
	public void setSemestres(ArrayList<Semester> semestres) {
		this.semestres = semestres;
	}
	
	
}
