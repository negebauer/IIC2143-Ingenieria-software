package Tools.Interfaces;

import System.Courses.Classroom;
import System.Courses.Schedule;

public interface ICourse {
	
	public Classroom getClassroom();
	
	public void setClassroom(Classroom classroom);
	
	public Schedule getSchedule();
	
	public void setSchedule(Schedule schedule);
}