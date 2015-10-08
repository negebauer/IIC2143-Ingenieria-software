package System.Courses;

import java.util.ArrayList;

import System.Users.Student;
import System.Users.Assistant;
import System.Users.Professor;
import Tools.Enums.School;
import Tools.Enums.Semester;
import Tools.Others.Const;

public class Course {

	private String name;
	private String initials;
	private String destails;
	
	private int credits;
	private boolean assistantship;

	private School school;
	private Semester semester;

	private ArrayList<Professor> professors;
	private int scheduleCathedra;	
	private Classroom classroomCathedra;
	
	private ArrayList<Assistant> assistants;
	private int scheduleAssistant;	
	private Classroom classroomAssistant;
	
	private ArrayList<Student> students;
	private int size;

	//Constructors
	public Course(String name, String initials) {

		initCourse(name, initials, Const.DEFAULT_CREDITS);
	}
	public Course(String name, String initials, int credits, School school) {
		
		initCourse(name, initials, credits);
		this.school = school;
	}
	public void initCourse(String nombre, String sigla, int creditos) {
		
		this.name = nombre;
		this.initials = sigla;
		this.credits = creditos;
		this.assistantship = false;
		this.professors = new ArrayList<Professor>();
		this.assistants = new ArrayList<Assistant>();
		this.students = new ArrayList<Student>();
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
	
	//Assistant
	public void addAssistant(Assistant assistant) {		
		this.assistants.add(assistant);
	}
	public void removeAssistant(Assistant assistant) {
		this.assistants.remove(assistant);
	}
	public ArrayList<Assistant> getAssistants() {
		return this.assistants;
	}
	public Assistant getAssistant(int index) {
		return this.assistants.get(index);
	}
	
	//Students
	public void addStudent(Student student) {
		this.students.add(student);
	}
	public void removeStudent(Student student) {
		this.students.remove(student);
	}
	public ArrayList<Student> getStudents() {
		return this.students;
	}
	public Student getStudent(int index) {
		return this.students.get(index);
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
	
	public boolean getThereAssistantship() {
		return assistantship;
	}
	public void setThereAssistantship(boolean assistantship) {
		this.assistantship = assistantship;
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
	
	public int getScheduleCathedra() {
		return scheduleCathedra;
	}
	public void setScheduleCathedra(int scheduleCathedra) {
		this.scheduleCathedra = scheduleCathedra;
	}
	
	public Classroom getClassroomCathedra() {
		return classroomCathedra;
	}
	public void setClassroomCathedra(Classroom classroomCathedra) {
		this.classroomCathedra = classroomCathedra;
	}
	
	public int getHoraryAssistant() {
		return scheduleAssistant;
	}
	public void setScheduleAssistant(int scheduleAssistant) {
		this.scheduleAssistant = scheduleAssistant;
	}
	
	public Classroom getClassroomAssistant() {
		return classroomAssistant;
	}
	public void setClassroomAssistant(Classroom classroomAssistant) {
		this.classroomAssistant = classroomAssistant;
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}	
}
