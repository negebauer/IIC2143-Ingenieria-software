package Tools.Enums;

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