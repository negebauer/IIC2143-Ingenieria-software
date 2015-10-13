package System.Users;

import java.util.ArrayList;

import System.Courses.Curriculum;
import System.Courses.StudyProgram;
import Tools.Enums.School;

/**
 * Class that represents a student.
 */
public class Student extends User {

	private int id;
	private int yearEntrance;
	private int yearGraduation;	
	private boolean regularStudent;
	private Curriculum curriculum;
	
	/**
	 * Creates an instance of Student.
	 * Supports default values for every parameter (except studyPrograms), therefore null is a valid value for every parameter.
	 * @param id The unique identifier of the student.
	 * @param yearEntrance The year in which the student entered the university.
	 * @param studyPrograms The study programs that the student is currently coursing.
	 * @param rut The unique role identifier of the student.
	 * @param name The name of the student.
	 * @param lastnameFather The last name of the father of the student.
	 * @param lastnameMother The last name of the mother of the student.
	 * @param address The address of the student.
	 * @param gender The gender of the student.
	 * @param phone The cell phone number of the student.
	 * @param birthdayString The birthday of the student in the format dd.MM.yyyy. 
	 */
	public Student(int id, int yearEntrance, ArrayList<StudyProgram> studyPrograms, String rut, String name, String lastnameFather, String lastnameMother, String address, Gender gender, int phone, String birthdayString) {
		super(rut, name, lastnameFather, lastnameMother, address, gender, Access.USER, phone, birthdayString);
		this.id = id > 0 ? id : 0;
		this.yearEntrance = yearEntrance > 0 ? yearEntrance : 0;
		this.curriculum = new Curriculum(studyPrograms);
	}

	/**
	 * @return The unique identifier of the student.
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return The entrance year of the student.
	 */
	public int getYearEntrance() {
		return yearEntrance;
	}
	
	/**
	 * @return The graduation year of the student.
	 */
	public int getYearGraduation() {
		return yearGraduation;
	}

	/**
	 * Modifies the year in which the student graduated.
	 * @param yearGraduation The new graduation year of the student.
	 */
	public void setYearGraduation(int yearGraduation) {
		this.yearGraduation = yearGraduation;
	}

	/**
	 * @return Whether the student is a regular one or not (university concept).
	 */
	public boolean isRegularStudent() {
		return regularStudent;
	}

	/**
	 * Modifies whether the student is a regular one or not (university concept).
	 * @param regularStudent The new status of the student.
	 */
	public void setRegularStudent(boolean regularStudent) {
		this.regularStudent = regularStudent;
	}

	/**
	 * @return The student's curriculum.
	 */
	public Curriculum getCurriculum() {
		return curriculum;
	}

	/**
	 * Modifies the student's curriculum.
	 * @param curriculum The new curriculum of the student.
	 */
	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	/**
	 * @return The schools to which the student belongs (defined by his study programs).
	 */
	public ArrayList<School> getSchools() {
		ArrayList<School> schools = new ArrayList<School>();
		for (StudyProgram studyProgram : curriculum.getStudyPrograms()) {
			schools.add(studyProgram.getSchool());
		}
		return schools;
	}
}