package System.Courses;

import System.Users.Professor;
import Tools.Interfaces.ICourse;

public class Laboratory implements ICourse {
	
	private Professor professor;
	private Classroom classroom;
	private Schedule schedule;	
	
	public Laboratory(Professor professor, Classroom classroom, Schedule schedule) {
		
		this.setProfessor(professor);
		this.classroom = classroom;
		this.schedule = schedule;
	}
		
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
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
