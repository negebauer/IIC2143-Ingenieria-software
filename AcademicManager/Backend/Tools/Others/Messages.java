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
		COURSE_WASNT_ADDED_TO_SEMESTER_REPEATED 							(0),
		COURSE_WASNT_ADDED_TO_SEMESTER_NOT_COURSED_IN_SEMESTER 				(1),
		COURSE_WASNT_ADDED_TO_SEMESTER_MAX_CREDITS_EXCEEDED					(2),
		COURSE_WASNT_ADDED_TO_SEMESTER_REQUIREMENTS							(3),
		COURSE_WASNT_ADDED_TO_SEMESTER_COREQUIREMENTS						(4),
		COURSES_WERE_ADDED_TO_SEMESTER										(5),
		COURSE_WAS_ADDED_TO_SEMESTER 										(6),
		COURSE_WASNT_ADDED_TO_SEMESTER_EVALUATION_CLASH						(7),
		COURSE_WASNT_ADDED_TO_SEMESTER_SCHEDULE_CLASH						(8),
		COURSE_WAS_REMOVED_OF_SEMESTER										(9),
		COURSE_WASNT_REMOVED_OF_SEMESTER_NOT_IN_SEMESTER					(10),
		REQUIREMENT_WAS_ADDED_TO_REQUIREMENTS								(11),
		REQUIREMENT_WASNT_ADDED_TO_REQUIREMENTS_REPEATED					(12),
		REQUIREMENT_WASNT_ADDED_TO_REQUIREMENTS_SAME_COURSE					(13),
		REQUIREMENT_WAS_REMOVED_OF_REQUIREMENTS								(14),
		REQUIREMENT_WASNT_REMOVED_OF_REQUIREMENTS_NOT_IN_REQUIREMENTS		(15),
		COREQUIREMENT_WAS_ADDED_TO_COREQUIREMENTS							(16),
		COREQUIREMENT_WASNT_ADDED_TO_COREQUIREMENTS_REPEATED				(17),
		COREQUIREMENT_WASNT_ADDED_TO_COREQUIREMENTS_SAME_COURSE				(18),
		COREQUIREMENT_WAS_REMOVED_OF_COREQUIREMENTS							(19),
		COREQUIREMENT_WASNT_REMOVED_OF_COREQUIREMENTS_NOT_IN_COREQUIREMENTS	(20),
		COURSE_WASNT_CREATED_EVALUATION_CLASH								(21),
		COURSE_WASNT_CREATED_PROFESSOR_CLASH								(22),
		COURSE_WASNT_CREATED_CLASSROOM_CLASH								(23),
		COURSE_WAS_CREATED													(24),
		COURSE_WASNT_DELETED_REQUIRED_FOR_COURSE							(25),
		COURSE_WAS_DELETED													(26);
		
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
	
	/**
	 * Returns the message to be displayed in the language specified in the configuration file.
	 * @param index The index of the message to be returned (obtained from enum Message).
	 * @return A string containing the requested message in the current language.
	 */
	public static String getMessage(Integer index) {
		return getMessage(index, null);
	}
	
	/**
	 * Returns the message to be displayed in the language specified in the configuration file.
	 * @param index The index of the message to be returned (obtained from enum Message).
	 * @param additionalInfo Optional additional info to be displayed.
	 * @return A string containing the requested message in the current language + "\n" + the additionalInfo string.
	 */
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
        result.put(4, "You must take this course(s) too this semester:");
        result.put(5, "The courses were succsessfully added to the semester");
        result.put(6, "The course was correctly added to the semester");
        result.put(7, "The course wasn't added due to an evaluation date conflict with another course's evaluation date");
        result.put(8, "The course wasn't added due to a conflict between this course's schedule and another course's schedule");
        result.put(9, "The course was correctly removed of the semester");
        result.put(10, "The course is not in the semester");
        result.put(11, "The course was succsessfully added as a requirement");
        result.put(12, "This course is already a requirement");
        result.put(13, "You can't add a course as a requirement of itself");
        result.put(14, "The course was succsessfully removed from requirements");
        result.put(15, "This is not a requirement of the course");
        result.put(16, "The course was successfully added as a coRequirement");
        result.put(17, "This course is already as a coRequirement");
        result.put(18, "You can't add a course as a coRequirement of itself");
        result.put(19, "This course was removed from the coRequirements successfully");
        result.put(20, "This course is not registered as a coRequirement");
        result.put(21, "Course wasn't created due to a clash with the evaluations classroom of the course");
        result.put(22, "Course wasn't created due to a clash with the professors of the course");
        result.put(23, "Course wasn't created due to a clash with the course classrooms of the course");
        result.put(24, "Course was successfully created");
        result.put(25, "The course couldn't be deleted because it's a prerequisite for another coures");
        result.put(26, "The course was successfully deleted");
        return result;
    }
    
    private static HashMap<Integer, String> createMapSpanish() {
    	HashMap<Integer, String> result = new HashMap<Integer, String>();
        result.put(0, "Este curso ya esta ingresado en el semestre");
        result.put(1, "Este curso no se dicta en este semestre");
        result.put(2, "El maximo de creditos de este semestre sobrepasa el limite");
        result.put(3, "No cumple con los requisitos, falta(n):");
        result.put(4, "Para poder tomar el curso debes tomar tambien el(los) siguiente(s) curso(s):");
        result.put(5, "Los cursos fueron agregados al semestre satisfactoriamente");
        result.put(6, "El curso fue agregado correctamente al semestre");
        result.put(7, "No se pudo agregar el curso debido a un tope de una evaluacion con la evaluacion de otro curso");
        result.put(8, "No se pudo agregar el curso debido a un tope de horario con otro curso");
        result.put(9, "El curso fue removido correctamente del semestre");
        result.put(10, "Este curso no se encuentra en el semestre");
        result.put(11, "El curso fue agregado como requisito exitosamente");
        result.put(12, "Este curso ya esta ingresado como requisito");
        result.put(13, "No se puede agregar un curso como requisito de el mismo");
        result.put(14, "El curso fue removido exitosamente de los requisitos");
        result.put(15, "Este curso no es un requisito");
        result.put(16, "El curso fue agregado como coRequisito satisfactoriamente");
        result.put(17, "Este curso ya esta dentro de los coRequisitos");
        result.put(18, "No puedes agregar un curso como coRequisito de el mismo");
        result.put(19, "El curso fue removido de los coRequisitos satisfactoriamente");
        result.put(20, "Este curso no se encuentra como coRequisito");
        result.put(21, "El curso no pudo crearse ya que hay un tope de sala para evaluaciones");
        result.put(22, "El curso no pudo crearse ya que hay un tope de profesores");
        result.put(23, "El curso no pudo crearse ya que hay un tope de salas");
        result.put(24, "El curso fue creado correctamente");
        result.put(25, "El curso no pudo ser eliminado ya que es requisitos para otros cursos");
        result.put(26, "El curso fue eliminado correctamente");
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
