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
	
//	TODO Write java doc
	/**
	 * Creates an instance of Student.
	 * Supports default values for every parameter (except studyPrograms), therefore null is a valid value for every parameter.
	 * @param id
	 * @param yearEntrance
	 * @param studyPrograms
	 * @param rut
	 * @param name
	 * @param lastnameFather
	 * @param lastnameMother
	 * @param address
	 * @param gender
	 * @param phone
	 * @param birthdayString
	 */
	public Student(int id, int yearEntrance, ArrayList<StudyProgram> studyPrograms, String rut, String name, String lastnameFather, String lastnameMother, String address, Gender gender, int phone, String birthdayString) {
		super(rut, name, lastnameFather, lastnameMother, address, gender, Access.USER, phone, birthdayString);
		this.id = id > 0 ? id : 0;
		this.yearEntrance = yearEntrance > 0 ? yearEntrance : 0;
		this.curriculum = new Curriculum(studyPrograms);
	}

//	TODO Write java doc for all getters and setters
	public int getId() {
		return id;
	}

	public int getYearEntrance() {
		return yearEntrance;
	}
	
	public int getYearGraduation() {
		return yearGraduation;
	}

	public void setYearGraduation(int yearGraduation) {
		this.yearGraduation = yearGraduation;
	}

	public boolean isRegularStudent() {
		return regularStudent;
	}

	public void setRegularStudent(boolean regularStudent) {
		this.regularStudent = regularStudent;
	}

	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	public ArrayList<School> getSchools() {
		ArrayList<School> schools = new ArrayList<School>();
		for (StudyProgram studyProgram : curriculum.getStudyPrograms()) {
			schools.add(studyProgram.getSchool());
		}
		return schools;
	}
}