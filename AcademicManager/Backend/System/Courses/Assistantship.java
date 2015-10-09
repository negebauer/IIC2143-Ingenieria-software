package System.Courses;

import java.util.ArrayList;

import System.Users.Assistant;
import Tools.Interfaces.ICourse;

/**
 * Class that manages the assistants of a classroom
 */
public class Assistantship implements ICourse {
	
	private ArrayList<Assistant> assistants; 
	private Classroom classroom;
	private Schedule schedule;	
	
	/**
	 * Creates an instance of Assistantship
	 * @param assistants The assistants of this assistantship
	 * @param classroom The classroom where this assistantship is made
	 * @param schedule The schedule in which this assistantship is made
	 */
	public Assistantship(ArrayList<Assistant> assistants, Classroom classroom, Schedule schedule) {
		
		this.assistants = assistants;
		this.classroom = classroom;
		this.schedule = schedule;
	}
		
	/**
	 * Adds an assistant to this Assistantship
	 * @param assistant The assistant to be added to this Assistantship
	 */
	public void addAssistant(Assistant assistant) {		
		this.assistants.add(assistant);
	}
	
	/**
	 * Removes an assistant from this Assistantship
	 * @param assistant The assistant to be removed from this Assistantship
	 */
	public void removeAssistant(Assistant assistant) {
		this.assistants.remove(assistant);
	}
	
	/**
	 * @return The Array of assistants of this Assistantship
	 */
	public ArrayList<Assistant> getAssistants() {
		return this.assistants;
	}

	/**
	 * Returns the assistant from the assistants list that corresponds to the given index
	 * @param index The index of the assistant to be returned 
	 * @return The requested assistant
	 */
	public Assistant getAssistant(int index) {
		return this.assistants.get(index);
	}

	// ICourse methods
	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	
	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
}
