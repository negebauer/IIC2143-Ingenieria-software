package System.Courses;

import java.util.ArrayList;

public class Schedule {

	public enum Day {
		MONDAY ("L"),
		TUESDAY ("M"),
		WEDNESDAY ("W"),
		THURSDAY ("J"),
		FRIDAY ("V"),
		SATURDAY ("S"),
		SUNDAY ("D");
		
		private final String day;
		Day(String day) {
	        this.day = day;
		}
		
		public String getDay() {
			return this.day;
		}
	}
	
	public enum Module {
		_1 ("1"), //08:30-09:50
		_2 ("2"), //10:00-11:20
		_3 ("3"), //11:30-12:50
		_4 ("4"), //14:00-15:20
		_5 ("5"), //15:30-16:50
		_6 ("6"), //17:00-18:20
		_7 ("7"), //18:00-19:20
		_8 ("8");  //20:00-21:20
		
		private final String module;
		Module(String module) {
	        this.module = module;
		}
		
		public String getModule() {
			return this.module;
		}
	}
	
	private ArrayList<DayModuleTuple> modules = new ArrayList<DayModuleTuple>();
	
	public Schedule() {
		
	}
	
	public Schedule(DayModuleTuple module) {
		this.modules.add(module);
	}
	
	public Schedule(ArrayList<DayModuleTuple> modules) {
		this.modules = modules;
	}
	
	public ArrayList<DayModuleTuple> getModules() {
		return modules;
	}
	public void addModule(DayModuleTuple module) {
		this.modules.add(module);
	}
	public void removeModule(DayModuleTuple index) {
		this.modules.remove(index);
	}

	public String getSchedule() {
		String schedule = "";
		
		for(int i = 0; i < modules.size(); i++) {
			DayModuleTuple t = modules.get(i);
			schedule += t.getDay().getDay() + t.getModule().getModule() + " ";
		}					
		return schedule;
	}
	
	public class DayModuleTuple {

		private Day day;
		private Module module;
		
		public DayModuleTuple(Day day, Module module) {
			this.day = day;
			this.module = module;
		}

		public Day getDay() {
			return day;
		}
		public void setDay(Day day) {
			this.day = day;
		}

		public Module getModule() {
			return module;
		}
		public void setModule(Module module) {
			this.module = module;
		}
	}
}
