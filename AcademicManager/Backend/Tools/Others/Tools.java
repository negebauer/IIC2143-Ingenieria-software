package Tools.Others;

import java.util.ArrayList;

import System.Courses.Schedule.Tuple;

public final class Tools {

	public static String getSchedule(ArrayList<Tuple> tuples) {
		String schedule = "";
		
		for(int i = 0; i < tuples.size(); i++) {
			Tuple t = tuples.get(i);
			schedule += t.getDay().getDay() + t.getModule().getModule() + " ";
		}					
		return schedule;
	}
	
}
