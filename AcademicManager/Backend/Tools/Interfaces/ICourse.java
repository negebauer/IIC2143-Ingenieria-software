package Tools.Interfaces;

import System.Courses.Classroom;
import System.Courses.Schedule;

/**
 * Protocol used to define a class as a Course which has a Classroom and a Schedule.
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