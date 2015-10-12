package Tools.Others;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import System.Courses.Schedule.Day;

public class Messages {

	public enum SupportedLanguage {
		ENGLISH,
		SPANISH;
		
		public static SupportedLanguage defaultLanguage() {
			return SupportedLanguage.ENGLISH;
		}
	}
	
	public enum Message {
		COURSE_WASNT_ADDED_TO_SEMESTER_REPEATED 						(0),
		COURSE_WASNT_ADDED_TO_SEMESTER_NOT_COURSED_IN_SEMESTER 			(1),
		COURSE_WASNT_ADDED_TO_SEMESTER_MAX_CREDITS_EXCEEDED				(2),
		COURSE_WASNT_ADDED_TO_SEMESTER_REQUIREMENTS						(3),
		COURSES_WERE_ADDED_TO_SEMESTER									(4),
		COURSE_WAS_ADDED_TO_SEMESTER 									(5),
		COURSE_WASNT_ADDED_TO_SEMESTER_EVALUATION_CLASH					(6),
		COURSE_WASNT_ADDED_TO_SEMESTER_SCHEDULE_CLASH					(7),
		COURSE_WAS_REMOVED_OF_SEMESTER									(8),
		COURSE_WASNT_REMOVED_OF_SEMESTER_NOT_IN_SEMESTER				(9),
		REQUIREMENT_WAS_ADDED_TO_REQUIREMENTS							(10),
		REQUIREMENT_WASNT_ADDED_TO_REQUIREMENTS_REPEATED				(11),
		REQUIREMENT_WASNT_ADDED_TO_REQUIREMENTS_SAME_COURSE				(12),
		REQUIREMENT_WAS_REMOVED_OF_REQUIREMENTS							(13),
		REQUIREMENT_WASNT_REMOVED_OF_REQUIREMENTS_NOT_IN_REQUIREMENTS	(14);
		
		private final int messageIndex;
		Message(int messageIndex) {
	        this.messageIndex = messageIndex;
		}
		
		public int index() {
			return this.messageIndex;
		}
	}
	
	// TODO Get this value from a configuration file.
	public static SupportedLanguage LANGUAGE = SupportedLanguage.defaultLanguage();
	
	public static String getMessage(Integer index) {
		return getMessage(index, null);
	}
	
	public static String getMessage(Integer index, String additionalInfo) {
		additionalInfo = "\n" + additionalInfo != null ? additionalInfo : "";
		switch (LANGUAGE) {
		case ENGLISH:
			return ENGLISH.get(index) + additionalInfo;
		case SPANISH:
			return SPANISH.get(index) + additionalInfo;
		default:
			return "ERROR GETTING MESSAGE (404)";
		}
	}
	
	public static final HashMap<Integer, String> ENGLISH = createMapEnglish();
	public static final HashMap<Integer, String> SPANISH = createMapSpanish();
	
    private static HashMap<Integer, String> createMapEnglish() {
    	HashMap<Integer, String> result = new HashMap<Integer, String>();
        result.put(0, "Course is already registered in this semester");
        result.put(1, "The course isn't dictated in this semester");
        result.put(2, "The maximum of credits of this semester is exceeded");
        result.put(3, "You do not have approved all requirements:");
        result.put(4, "The courses were succsessfully added to the semester");
        result.put(5, "The course was correctly added to the semester");
        result.put(6, "The course wasn't added due to an evaluation date conflict with another course's evaluation date");
        result.put(7, "The course wasn't added due to a conflict between this course's schedule and another course's schedule");
        result.put(8, "The course was correctly removed of the semester");
        result.put(9, "The course is not in the semester");
        result.put(10, "The course was succsessfully added as a requirement");
        result.put(11, "This course is already a requirement");
        result.put(12, "You can't add a course as a requirement of itself");
        result.put(13, "The course was succsessfully removed from requirements");
        result.put(14, "This is not a requirement of the course");
        return result;
    }
    
    private static HashMap<Integer, String> createMapSpanish() {
    	HashMap<Integer, String> result = new HashMap<Integer, String>();
        result.put(0, "Este curso ya esta ingresado en el semestre");
        result.put(1, "Este curso no se dicta en este semestre");
        result.put(2, "El maximo de creditos de este semestre sobrepasa el limite");
        result.put(3, "No cumple con los requisitos, falta(n):");
        result.put(4, "Los cursos fueron agregados al semestre satisfactoriamente");
        result.put(5, "El curso fue agregado correctamente al semestre");
        result.put(6, "No se pudo agregar el curso debido a un tope de una evaluacion con la evaluacion de otro curso");
        result.put(7, "No se pudo agregar el curso debido a un tope de horario con otro curso");
        result.put(8, "El curso fue removido correctamente del semestre");
        result.put(9, "Este curso no se encuentra en el semestre");
        result.put(10, "El curso fue agregado como requisito exitosamente");
        result.put(11, "Este curso ya esta ingresado como requisito");
        result.put(12, "No se puede agregar un curso como requisito de el mismo");
        result.put(13, "El curso fue removido exitosamente de los requisitos");
        result.put(14, "Este curso no es un requisito");
        return result;
    }
    
    public static String getDay(Day day) {
    	switch (LANGUAGE) {
		case ENGLISH:
			return DAY_ENGLISH.get(day);
		case SPANISH:
			return DAY_SPANISH.get(day);
		default:
			return "ERROR GETTING MESSAGE (404)";
		}
	}
    
    public static final Map<Day, String> DAY_ENGLISH = createMapDayEnglish();
	public static final Map<Day, String> DAY_SPANISH = createMapDaySpanish();
	
	private static Map<Day, String> createMapDayEnglish() {
        Map<Day, String> result = new HashMap<Day, String>();
        result.put(Day.MONDAY,		"M");
        result.put(Day.TUESDAY,		"T");
        result.put(Day.WEDNESDAY,	"W");
        result.put(Day.THURSDAY,	"Th");
        result.put(Day.FRIDAY,		"F");
        result.put(Day.SATURDAY,	"S");
        result.put(Day.SUNDAY,		"Sn");
        return Collections.unmodifiableMap(result);
    }
	
	private static Map<Day, String> createMapDaySpanish() {
        Map<Day, String> result = new HashMap<Day, String>();
        result.put(Day.MONDAY,		"L");
        result.put(Day.TUESDAY,		"M");
        result.put(Day.WEDNESDAY,	"W");
        result.put(Day.THURSDAY,	"J");
        result.put(Day.FRIDAY,		"V");
        result.put(Day.SATURDAY,	"S");
        result.put(Day.SUNDAY,		"D");
        return Collections.unmodifiableMap(result);
    }
}
