package Tools.Enums;

public enum AcademicSemester {
	FIRST,
	SECOND,
	BOTH;
	
	public static AcademicSemester defaultSemester() {
		return AcademicSemester.BOTH;
	}
}
