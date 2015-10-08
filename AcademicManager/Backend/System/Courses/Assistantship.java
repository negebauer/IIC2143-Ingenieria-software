package System.Courses;

import java.util.ArrayList;

import System.Users.Assistant;
import Tools.Interfaces.ICourse;

public class Assistantship implements ICourse {
	
	private ArrayList<Assistant> assistants; 
	private Classroom classroom;
	private Schedule schedule;	
	
	public Assistantship(ArrayList<Assistant> assistants, Classroom classroom, Schedule schedule) {
		
		this.assistants = assistants;
		this.classroom = classroom;
		this.schedule = schedule;
	}
		
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
