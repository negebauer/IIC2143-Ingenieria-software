package backend.enums;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import backend.others.Messages;

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

	public static final Map<AcademicSemester, String> ACADEMIC_SEMESTER_MESSAGE_ENGLISH = createMapAcademicSemesterMessageEnglish();
	public static final Map<AcademicSemester, String> ACADEMIC_SEMESTER_SPANISH = createMapAcademicSemesterMessageSpanish();

	public static String getAcademicSemesterMessage(AcademicSemester academicSemester) {
		switch (Messages.LANGUAGE()) {
		case ENGLISH:
			return ACADEMIC_SEMESTER_MESSAGE_ENGLISH.get(academicSemester);
		case SPANISH:
			return ACADEMIC_SEMESTER_SPANISH.get(academicSemester);
		default:
			return Messages.ERROR_MESSAGE;
		}
	}

	private static Map<AcademicSemester, String> createMapAcademicSemesterMessageEnglish() {
		Map<AcademicSemester, String> result = new HashMap<AcademicSemester, String>();
		result.put(AcademicSemester.BOTH, "Both");
		result.put(AcademicSemester.FIRST, "First");
		result.put(AcademicSemester.SECOND, "Second");
		return Collections.unmodifiableMap(result);
	}

	private static Map<AcademicSemester, String> createMapAcademicSemesterMessageSpanish() {
		Map<AcademicSemester, String> result = new HashMap<AcademicSemester, String>();
		result.put(AcademicSemester.BOTH, "Ambos");
		result.put(AcademicSemester.FIRST, "Primero");
		result.put(AcademicSemester.SECOND, "Segundo");
		return Collections.unmodifiableMap(result);
	}

	public static AcademicSemester getAcademicSemester(String academicSemesterString) {
		for (AcademicSemester academicSemester : AcademicSemester.values()) {
			if (getAcademicSemesterMessage(academicSemester) == academicSemesterString) {
				return academicSemester;
			}
		}
		return AcademicSemester.defaultSemester();
	}
}
