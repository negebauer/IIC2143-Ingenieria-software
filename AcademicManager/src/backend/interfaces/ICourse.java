package backend.interfaces;

import backend.courses.Classroom;
import backend.courses.Schedule;

/**
 * Protocol used to define a class as an ICourse,
 * which represents an actual (physical) class that
 * is made in a classroom in a certain schedule.
 */
public interface ICourse {
	
	/**
	 * @return This Course's associated Classroom.
	 */
	public Classroom getClassroom();
	
	/**
	 * Modifies this Course's Classroom.
	 * @param classroom This Course's new Classroom.
	 */
	public void setClassroom(Classroom classroom);
	
	/**
	 * @return This Course's associated Schedule.
	 */
	public Schedule getSchedule();
	
	/**
	 * Modifies this Course's Schedule.
	 * @param schedule This Course's new Schedule.
	 */
	public void setSchedule(Schedule schedule);
	
}