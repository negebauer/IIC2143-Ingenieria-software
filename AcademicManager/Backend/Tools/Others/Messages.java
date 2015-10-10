package Tools.Others;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Messages {

	public enum SupportedLanguage {
		SPANISH,
		ENGLISH;
		
		public static SupportedLanguage defaultLanguage() {
			return SupportedLanguage.ENGLISH;
		}
	}
	
	public enum Message {
		COURSE_WASNT_ADDED_TO_SEMESTER_REPEATED 						(1),
		COURSE_WASNT_ADDED_TO_SEMESTER_NOT_COURSED_IN_SEMESTER 			(2),
		COURSE_WAS_ADDED_TO_SEMESTER 									(3);
		
		private final int messageIndex;
		Message(int messageIndex) {
	        this.messageIndex = messageIndex;
		}
		
		public int index() {
			return this.messageIndex;
		}
	}
	
	// TODO Get this value from a configuration file.
	public static String LANGUAGE = SupportedLanguage.defaultLanguage().toString();
	
	public static String getMessage(Integer index) {
		return LANGUAGE_MESSAGES.get(LANGUAGE).get(index);
	}
	
	public static final Map<String, Map<Integer, String>> LANGUAGE_MESSAGES = createMapLanguageMessages();
	
	private static Map<String, Map<Integer, String>> createMapLanguageMessages() {
		Map<String, Map<Integer, String>> result = new HashMap<String, Map<Integer, String>>();
        result.put("ENGLISH", ENGLISH);
        result.put("SPANISH", SPANISH);
        return Collections.unmodifiableMap(result);
    }
	
	public static final Map<Integer, String> ENGLISH = createMapEnglish();
	public static final Map<Integer, String> SPANISH = createMapSpanish();
	
    private static Map<Integer, String> createMapEnglish() {
        Map<Integer, String> result = new HashMap<Integer, String>();
        result.put(1, "Course is already registered in this semester");
        result.put(2, "The course isn't dictated in this semester");
        result.put(3, "The course was correctly added to the semester");
        return Collections.unmodifiableMap(result);
    }
    
    private static Map<Integer, String> createMapSpanish() {
        Map<Integer, String> result = new HashMap<Integer, String>();
        result.put(1, "Este curso ya esta ingresado en el semestre");
        result.put(2, "Este curso no se dicta en este semestre");
        result.put(3, "El curso fue agregado correctamente al semestre");
        return Collections.unmodifiableMap(result);
    }
    
}
