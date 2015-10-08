package Tools.Others;

import java.util.ArrayList;

import System.Courses.Schedule.Tuple;
import Tools.Enums.Day;
import Tools.Enums.Module;

public final class Tools {

	public static String getSchedule(ArrayList<Tuple> tuples) {
		
		String schedule = "";
		
		for(int i = 0; i < tuples.size(); i++) {
			
			Tuple t = tuples.get(i);
			schedule += getDay(t.getDay()) + getModule(t.getModule()) + " ";
		}					
		return schedule;
	}
	
	public static String getDay(Day d) {
		
		if(d == Day.Monday) return "L";
		else if(d == Day.Tuesday) return "M";
		else if(d == Day.Wednesday) return "V";
		else if(d == Day.Thursday) return "J";
		else if(d == Day.Friday) return "V";
		else if(d == Day.Saturday) return "S";
		else return "D";		
	}
	
	public static String getModule(Module m) {
		
		if(m == Module._1) return "1";
		else if(m == Module._2) return "2";
		else if(m == Module._3) return "3";
		else if(m == Module._4) return "4";
		else if(m == Module._5) return "5";
		else if(m == Module._6) return "6";
		else if(m == Module._7) return "7";
		else return "8";
	}	
}
