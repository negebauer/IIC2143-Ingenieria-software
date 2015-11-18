package backend.courses;

import java.util.ArrayList;

import backend.others.Messages;

public class Schedule {

	public enum Day {
		MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

		public String getDayString() {
			return Messages.getDay(this);
		}

		public int getInt() {
			switch (this) {
			case MONDAY:
				return 1;
			case TUESDAY:
				return 2;
			case WEDNESDAY:
				return 3;
			case THURSDAY:
				return 4;
			case FRIDAY:
				return 5;
			case SATURDAY:
				return 6;
			case SUNDAY:
				return 7;
			default:
				return 9;
			}
		}
	}

	public enum Module {
		_1("1"), _2("2"), _3("3"), _4("4"), _5("5"), _6("6"), _7("7"), _8("8");

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

		public int getInt() {
			switch (this) {
			case _1:
				return 1;
			case _2:
				return 2;
			case _3:
				return 3;
			case _4:
				return 4;
			case _5:
				return 5;
			case _6:
				return 6;
			case _7:
				return 7;
			case _8:
				return 8;
			default:
				return 9;
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
	 * 
	 * @param module
	 *            The module of the (physical) class in this Schedule.
	 */
	public Schedule(DayModuleTuple module) {
		this.modules.add(module);
	}

	/**
	 * Creates a new instance of Schedule with various (physical) classes
	 * modules.
	 * 
	 * @param modules
	 *            The modules of the (physical) classes in this Schedule.
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
	 * 
	 * @param module
	 *            The module to be added.
	 */
	public void addModule(DayModuleTuple module) {
		this.modules.add(module);
	}

	/**
	 * Removes a (physical) class module to this Schedule.
	 * 
	 * @param module
	 *            The module to be removed.
	 */
	public void removeModule(DayModuleTuple module) {
		this.modules.remove(module);
	}

	/**
	 * @return The schedule in a readable format.
	 */
	public String getSchedule(String initials) {
		// ArrayList<String> schedule = getScheduleWithBasicStuff();
		// System.out.println("Fase 2 ok");
		// for (DayModuleTuple module : getModules()) {
		// int dayInt = module.day.getInt();
		// int moduleInt = module.module.getInt();
		// schedule.set(dayInt + moduleInt, initials);
		// }
		// System.out.println("Fase 3 ok");
		// String formattedSchedule = "";
		// for (int dayInt : new Range(8)) {
		// for (int moduleInt : new Range(9)) {
		// if (schedule.size() < dayInt + moduleInt || schedule.get(dayInt +
		// moduleInt) == null) {
		// formattedSchedule += "\t\t";
		// } else {
		// formattedSchedule += schedule.get(dayInt + moduleInt);
		// }
		// }
		// formattedSchedule += "\n";
		// }
		// return formattedSchedule;
		String schedule = "";
		for (DayModuleTuple module : getModules()) {
			schedule += Messages.getDay(module.day) + ": " + module.module.getTimeRangeString() + "\n\t\t";
		}
		return schedule;
	}

	/**
	 * Tried something more beautifull but didn't have time.
	 */
	// private ArrayList<String> getScheduleWithBasicStuff() {
	// ArrayList<String> schedule = new ArrayList<String>();
	// for (int dayInt : new Range(8)) {
	// for (int moduleInt : new Range(9)) {
	// schedule.add("");
	// }
	// }
	// System.out.println("Fase 1 ok");
	// // schedule.set(dia + modulo * 8, texto);
	// schedule.set(0 + 1 * 8, Module._1.getTimeRangeString());
	// schedule.set(0 + 2 * 8, Module._2.getTimeRangeString());
	// schedule.set(0 + 3 * 8, Module._3.getTimeRangeString());
	// schedule.set(0 + 4 * 8, Module._4.getTimeRangeString());
	// schedule.set(0 + 5 * 8, Module._5.getTimeRangeString());
	// schedule.set(0 + 6 * 8, Module._6.getTimeRangeString());
	// schedule.set(0 + 7 * 8, Module._7.getTimeRangeString());
	// schedule.set(0 + 8 * 8, Module._8.getTimeRangeString());
	// schedule.set(1 + 0, Messages.getDay(Day.MONDAY));
	// schedule.set(2 + 0, Messages.getDay(Day.TUESDAY));
	// schedule.set(3 + 0, Messages.getDay(Day.WEDNESDAY));
	// schedule.set(4 + 0, Messages.getDay(Day.THURSDAY));
	// schedule.set(5 + 0, Messages.getDay(Day.FRIDAY));
	// schedule.set(6 + 0, Messages.getDay(Day.SATURDAY));
	// schedule.set(7 + 0, Messages.getDay(Day.SUNDAY));
	// return schedule;
	// }

	/**
	 * Checks whether a schedule have any same module that this schedule.
	 * 
	 * @param schedule
	 *            The schedule that wants to be checked.
	 */
	public boolean scheduleClash(Schedule schedule) {

		for (DayModuleTuple dayModuleTupleA : this.getModules()) {
			for (DayModuleTuple dayModuleTupleB : schedule.getModules()) {
				if (dayModuleTupleA.day == dayModuleTupleB.day && dayModuleTupleA.module == dayModuleTupleB.module) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Class that represents a Tuple containing a Day and a Module in which a
	 * (physical) class takes place.
	 */
	public class DayModuleTuple {

		public Day day;
		public Module module;

		/**
		 * Creates a new instance of DayModuleTuple.
		 * 
		 * @param day
		 *            The day in which the (physical) class takes place.
		 * @param module
		 *            The module in which the (physical) class takes place.
		 */
		public DayModuleTuple(Day day, Module module) {
			this.day = day;
			this.module = module;
		}
	}
}
