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
		
		public String getDayString() {
			return this.day;
		}
	}
	
	public enum Module {
		_1 ("1"),
		_2 ("2"),
		_3 ("3"),
		_4 ("4"),
		_5 ("5"),
		_6 ("6"),
		_7 ("7"),
		_8 ("8");
		
		private final String module;
		Module(String module) {
	        this.module = module;
		}
		
		public String getModuleString() {
			return this.module;
		}
		
		public String getTimeRangeString() {
			switch (this) {
			case _1:
				return "08:30-09:50";
			case _2:
				return "10:00-11:20";
			case _3:
				return "11:30-12:50";
			case _4:
				return "14:00-15:20";
			case _5:
				return "15:30-16:50";
			case _6:
				return "17:00-18:20";
			case _7:
				return "18:00-19:20";
			case _8:
				return "20:00-21:20";
			default:
				return "00:00-00:00";
			}
		}
	}
	
	private ArrayList<DayModuleTuple> modules = new ArrayList<DayModuleTuple>();
	
	/**
	 * Creates a new instance of Schedule empty.
	 */
	public Schedule() {
		
	}
	
	/**
	 * Creates a new instance of Schedule with a (physical) class module.
	 * @param module The module of the (physical) class in this Schedule.
	 */
	public Schedule(DayModuleTuple module) {
		this.modules.add(module);
	}
	
	/**
	 * Creates a new instance of Schedule with various (physical) classes modules.
	 * @param modules The modules of the (physical) classes in this Schedule.
	 */
	public Schedule(ArrayList<DayModuleTuple> modules) {
		this.modules = modules;
	}
	
	/**
	 * @return All the (physical) class modules from this Schedule.
	 */
	public ArrayList<DayModuleTuple> getModules() {
		return modules;
	}
	
	/**
	 * Adds a (physical) class module to this Schedule.
	 * @param module The module to be added.
	 */
	public void addModule(DayModuleTuple module) {
		this.modules.add(module);
	}
	
	/**
	 * Removes a (physical) class module to this Schedule.
	 * @param module The module to be removed.
	 */
	public void removeModule(DayModuleTuple module) {
		this.modules.remove(module);
	}

	/**
	 * @return The schedule in a readable format.
	 */
	public String getSchedule() {
		String schedule = "";
		// TODO Better processing to emulate the Banner format ¿?
		for (DayModuleTuple module : getModules()) {
			schedule += module.getDay().getDayString() + ":" + module.getModule().getModuleString() + "\n";
		}
		return schedule;
	}
	
	/**
	 * Class that represents a Tuple containing a Day and a Module in which a (physical) class takes place.
	 */
	public class DayModuleTuple {

		private Day day;
		private Module module;
		
		/**
		 * Creates a new instance of DayModuleTuple.
		 * @param day The day in which the (physical) class takes place.
		 * @param module The module in which the (physical) class takes place.
		 */
		public DayModuleTuple(Day day, Module module) {
			this.day = day;
			this.module = module;
		}

		/**
		 * @return The day in which the (physical) class takes place.
		 */
		public Day getDay() {
			return day;
		}
		
		/**
		 * Modifies the day in which the (physical) class takes place.
		 * @param day The new day in which the (physical) class will take place. 
		 */
		public void setDay(Day day) {
			this.day = day;
		}

		/**
		 * @return The module in which the (physical) class takes place.
		 */
		public Module getModule() {
			return module;
		}
		
		/**
		 * Modifies the module during which the (physical) class takes place.
		 * @param module The new module during which the (physical) class will take place. 
		 */
		public void setModule(Module module) {
			this.module = module;
		}
	}
}
