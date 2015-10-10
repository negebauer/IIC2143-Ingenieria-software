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
		COURSE_WAS_ADDED_TO_SEMESTER 									(2);
		
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
		switch (LANGUAGE) {
		case ENGLISH:
			return ENGLISH.get(index);
		case SPANISH:
			return SPANISH.get(index);
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
        result.put(2, "The course was correctly added to the semester");
        return result;
    }
    
    private static HashMap<Integer, String> createMapSpanish() {
    	HashMap<Integer, String> result = new HashMap<Integer, String>();
        result.put(0, "Este curso ya esta ingresado en el semestre");
        result.put(1, "Este curso no se dicta en este semestre");
        result.put(2, "El curso fue agregado correctamente al semestre");
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
