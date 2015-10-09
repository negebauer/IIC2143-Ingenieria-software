package Tools.Enums;

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
