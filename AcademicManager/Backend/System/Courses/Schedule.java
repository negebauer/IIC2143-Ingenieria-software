package System.Courses;

import java.util.ArrayList;

import Tools.Enums.Day;
import Tools.Enums.Module;

public class Schedule {

	private ArrayList<Tuple> modules = new ArrayList<Tuple>();
	
	public Schedule() {
		
	}
	public Schedule(Tuple module) {
		
		this.modules.add(module);
	}
	public Schedule(ArrayList<Tuple> modules) {
		
		this.modules = modules;
	}
	
	public ArrayList<Tuple> getModules() {
		return modules;
	}
	public void addModule(Tuple module) {
		this.modules.add(module);
	}
	public void removeModule(Tuple index) {
		this.modules.remove(index);
	}

	public class Tuple {

		private Day day;
		private Module module;
		
		public Tuple(Day day, Module module) {
			
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
