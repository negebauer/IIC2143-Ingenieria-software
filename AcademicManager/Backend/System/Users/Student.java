package System.Users;

import System.Courses.Curriculum;
import System.Courses.StudyProgram;
import System.Users.Users.User;
import Tools.Enums.Access;

public class Student extends User {

	private int id;
	private int entry;
	private int egress;	
	private boolean regularStudent;
	private Curriculum curriculum;
	private StudyProgram program;
	
	public Student(String name, String lastname, String rut, int entry) {
		super(name, lastname, rut, Access.Student);
	}

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

	
}