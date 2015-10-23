package backend.courses;

import java.util.ArrayList;

import backend.enums.School;
import backend.others.Const;

/**
 * Class that represents a StudyProgram.
 */
public class StudyProgram {

	private String name;
	private School school;
	private int yearProgram;
	private ArrayList<Semester> semesters;
	private int maxCreditsPerSemester = Const.DEFAULT_CREDITS_PER_SEMESTER;
	private int maxFailedCredits = Const.DEFAULT_MAX_FAILED_CREDITS;
	
	/**
	 * Creates an instance of StudyProgram
	 * @param yearProgram Year in which this StudyProgram started (example: engineering 2009 and engineering 2013).
	 * @param semesters The semesters of the StudyProgram.
	 * @param school The school to which this StudyProgram belongs.
	 */
	public StudyProgram(String name, int yearProgram, ArrayList<Semester> semesters, School school, int maxCreditsPerSemester, int maxFailedCredits){
		this.name = name;
		this.yearProgram = yearProgram;
		this.semesters = semesters != null ? semesters : new ArrayList<Semester>();
		this.school = school;
		this.maxCreditsPerSemester = maxCreditsPerSemester;
		this.maxFailedCredits = maxFailedCredits; 
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//Getters and Setters
	/**
	 * @return The year in which the StudyProgram started.
	 */
	public int getyearProgram() {
		return yearProgram;
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

	/** 
	 * @return The maximum number of credits that can be failed in this study program.
	 */
	public int getMaxFailedCredits() {
		return maxFailedCredits;
	}

	/**
	 * Modifies the maximum number of credits that can be failed in this study program.
	 * @param maxFailedCredits The new maximum of of credits that can be failed in this study program.
	 */
	public void setMaxFailedCredits(int maxFailedCredits) {
		this.maxFailedCredits = maxFailedCredits;
	}
	
}
