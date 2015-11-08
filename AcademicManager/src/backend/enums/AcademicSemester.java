package backend.enums;

public enum AcademicSemester {
	FIRST,
	SECOND,
	BOTH;
	
	public static AcademicSemester defaultSemester() {
		return AcademicSemester.BOTH;
	}
	
	public String getSemesterNumber() {
		switch (this) {
		case FIRST:
			return "1";
		case SECOND:
			return "2";
		default:
			return "DATA ERROR";
		}
	}
	
	static public AcademicSemester createWithNumber(String number) {
		if (number.equals("1")) {
			return AcademicSemester.FIRST;
		} else if (number.equals("2")) {
			return AcademicSemester.SECOND;
		} else {
			return AcademicSemester.BOTH;
		}
	}
}
