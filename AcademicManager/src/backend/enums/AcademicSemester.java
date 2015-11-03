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
}
