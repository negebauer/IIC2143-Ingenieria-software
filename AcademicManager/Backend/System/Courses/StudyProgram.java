package System.Courses;

import java.util.ArrayList;

import Tools.Enums.School;
import Tools.Others.Const;

/**
 * Class that represents a StudyProgram.
 */
public class StudyProgram {

	private School school;
	private int yearEntrance;
	private ArrayList<Semester> semesters;
	private int maxCreditsPerSemester = Const.DEFAULT_CREDITS_PER_SEMESTER;
	
	/**
	 * Creates an instance of StudyProgram
	 * @param yearEntrance Year in which this StudyProgram started.
	 * @param semesters The semesters of the StudyProgram.
	 * @param school The school to which this StudyProgram belongs.
	 */
	public StudyProgram(int yearEntrance, ArrayList<Semester> semesters, School school){
		this.yearEntrance = yearEntrance;
		this.semesters = semesters;
		this.school = school;
	}

	//Getters and Setters
	/**
	 * @return The year in which the StudyProgram started.
	 */
	public int getyearEntrance() {
		return yearEntrance;
	}
	
	/**
	 * @return The school to which this StudyProgram belongs.
	 */
	public School getSchool() {
		return school;
	}
	
	/**
	 * Modifies the school to which this StudyProgram belongs.
	 * @param school The new school to which this StudyProgram will belong.
	 */
	public void setSchool(School school) {
		this.school = school;
	}

	/**
	 * @return The semesters of this StudyProgram.
	 */
	public ArrayList<Semester> getSemesters() {
		return semesters;
	}
	
	/**
	 * Modifies the semesters of this StudyProgram.
	 * @param semestres The new semesters of this StudyProgram.
	 */
	public void setSemesters(ArrayList<Semester> semesters) {
		this.semesters = semesters;
	}
	
	/** 
	 * @return The maximum of credits that one semester can have.
	 */
	public int getMaxCreditsPerSemester() {
		return maxCreditsPerSemester;
	}
	
	/**
	 * Modifies the maximum of credits that one semester can have.
	 * @param maxCreditsPerSemester The new maximum of credits for each semester.
	 */
	public void setMaxCreditsPerSemester(int maxCreditsPerSemester) {
		this.maxCreditsPerSemester = maxCreditsPerSemester;
	}
	
}
