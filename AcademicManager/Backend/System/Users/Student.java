package System.Users;

import System.Courses.Curriculum;
import System.Courses.StudyProgram;
import Tools.Enums.School;

public class Student extends User {

	private int id;
	private int entry;
	private int egress;	
	private boolean regularStudent;
	private Curriculum curriculum;
	private StudyProgram program;
	private School school;
	
	//Constructor
	public Student(String name, String lastname, String rut, int entry, School school) {
		super(name, lastname, rut, Access.READONLY);
		
		this.school = school;
	}
	
	public void newCurriculum(StudyProgram program) {
		
		this.curriculum = new Curriculum(program);
		this.program = program;
	}
	
	//Getters and Setters	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getEntry() {
		return entry;
	}
	public void setEntry(int entry) {
		this.entry = entry;
	}

	public int getEgress() {
		return egress;
	}
	public void setEgress(int egress) {
		this.egress = egress;
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

	public StudyProgram getProgram() {
		return program;
	}
	public void setProgram(StudyProgram program) {
		this.program = program;
	}

	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}	
}