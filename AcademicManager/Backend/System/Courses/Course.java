package System.Courses;

import java.util.ArrayList;

import System.Users.Professor;
import Tools.Enums.School;
import Tools.Enums.Semester;
import Tools.Interfaces.ICourse;
import Tools.Others.Const;

public class Course implements ICourse {

	private String name;
	private String initials;
	private String destails;
	
	private int credits;
	private boolean assistantship;
	private boolean laboratory;

	private School school;
	private Semester semester;

	private ArrayList<Professor> professors;
	private Classroom classroom;
	private Schedule schedule;	
	
	private int size;

	//Constructors
	public Course(String name, String initials, School school) {

		initCourse(name, initials, Const.DEFAULT_CREDITS, school);
	}
	public Course(String name, String initials, int credits, School school) {
		
		initCourse(name, initials, credits, school);
	}
	public void initCourse(String nombre, String sigla, int creditos, School school) {
		
		this.name = nombre;
		this.initials = sigla;
		this.credits = creditos;
		this.school = school;
		this.assistantship = false;
		this.laboratory = false;
		this.professors = new ArrayList<Professor>();
		this.size = Const.DEFAULT_SIZE;
		this.semester = Const.DEFAULT_SEMESTER;
	}
		
	//Professors
	public void addProfessor(Professor professor) {
		this.professors.add(professor);
	}
	public void removeProfessor(Professor professor) {
		this.professors.remove(professor);
	}
	public ArrayList<Professor> getProfessors() {
		return this.professors;
	}
	public Professor getProfessor(int index) {		
		return this.professors.get(index);
	}
	
	//Getters and Setters		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getInitials() {
		return initials;
	}
	public void setInitials(String initials) {
		this.initials = initials;
	}
	
	public String getDestails() {
		return destails;
	}
	public void setDestails(String destails) {
		this.destails = destails;
	}
		
	public int getCredits() {
		
		return credits;
	}
	public void setCredits(int credits) {
		
		this.credits = credits;
	}
	
	public boolean isAssistantship() {
		return assistantship;
	}
	public void isAssistantship(boolean assistantship) {
		this.assistantship = assistantship;
	}
	
	public boolean isLaboratory() {
		return laboratory;
	}
	public void setLaboratory(boolean laboratory) {
		this.laboratory = laboratory;
	}	
		
	public School getSchool() {
		return school;
	}	
	public void setSchool(School school) {
		this.school = school;
	}
	
	public Semester getSemester() {		
		return this.semester;
	}	
	public void setSemester(Semester semester) {	
		this.semester = semester;
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public Classroom getClassroom() {
		return classroom;
	}
	@Override
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	@Override
	public Schedule getSchedule() {
		return schedule;
	}
	@Override
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
}
