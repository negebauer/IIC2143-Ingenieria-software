package backend.others;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import backend.courses.Schedule.Day;

public class Messages {

	public enum SupportedLanguage {
		ENGLISH,
		SPANISH;
		
		public static SupportedLanguage defaultLanguage() {
			return SupportedLanguage.ENGLISH;
		}
	}
	
	public enum Message {
		COURSE_WASNT_ADDED_TO_SEMESTER_REPEATED,
		COURSE_WASNT_ADDED_TO_SEMESTER_NOT_COURSED_IN_SEMESTER,
		COURSE_WASNT_ADDED_TO_SEMESTER_MAX_CREDITS_EXCEEDED,
		COURSE_WASNT_ADDED_TO_SEMESTER_REQUIREMENTS,
		COURSE_WASNT_ADDED_TO_SEMESTER_COREQUIREMENTS,
		COURSES_WERE_ADDED_TO_SEMESTER,
		COURSE_WAS_ADDED_TO_SEMESTER,
		COURSE_WASNT_ADDED_TO_SEMESTER_EVALUATION_CLASH,
		COURSE_WASNT_ADDED_TO_SEMESTER_SCHEDULE_CLASH,
		COURSE_WAS_REMOVED_OF_SEMESTER,
		COURSE_WASNT_REMOVED_OF_SEMESTER_NOT_IN_SEMESTER,
		REQUIREMENT_WAS_ADDED_TO_REQUIREMENTS,
		REQUIREMENT_WASNT_ADDED_TO_REQUIREMENTS_REPEATED,
		REQUIREMENT_WASNT_ADDED_TO_REQUIREMENTS_SAME_COURSE,
		REQUIREMENT_WAS_REMOVED_OF_REQUIREMENTS,
		REQUIREMENT_WASNT_REMOVED_OF_REQUIREMENTS_NOT_IN_REQUIREMENTS,
		COREQUIREMENT_WAS_ADDED_TO_COREQUIREMENTS,
		COREQUIREMENT_WASNT_ADDED_TO_COREQUIREMENTS_REPEATED,
		COREQUIREMENT_WASNT_ADDED_TO_COREQUIREMENTS_SAME_COURSE,
		COREQUIREMENT_WAS_REMOVED_OF_COREQUIREMENTS,
		COREQUIREMENT_WASNT_REMOVED_OF_COREQUIREMENTS_NOT_IN_COREQUIREMENTS,
		COURSE_WASNT_CREATED_EVALUATION_CLASH,
		COURSE_WASNT_CREATED_PROFESSOR_CLASH,
		COURSE_WASNT_CREATED_CLASSROOM_CLASH,
		COURSE_WAS_CREATED,
		COURSE_WASNT_DELETED_REQUIRED_FOR_COURSE,
		COURSE_WAS_DELETED
		
	}
	
	// TODO Get this value from a configuration file.
	public static SupportedLanguage LANGUAGE = SupportedLanguage.defaultLanguage();
	
	/**
	 * Returns the message to be displayed in the language specified in the configuration file.
	 * @param message The message to be returned (obtained from enum Message).
	 * @return A string containing the requested message in the current language.
	 */
	public static String getMessage(Message message) {
		return getMessage(message, null);
	}
	
	/**
	 * Returns the message to be displayed in the language specified in the configuration file.
	 * @param message The message to be returned (obtained from enum Message).
	 * @param additionalInfo Optional additional info to be displayed.
	 * @return A string containing the requested message in the current language + "\n" + the additionalInfo string.
	 */
	public static String getMessage(Message message, String additionalInfo) {
		additionalInfo = "\n" + additionalInfo != null ? additionalInfo : "";
		switch (LANGUAGE) {
		case ENGLISH:
			return ENGLISH.get(message) + additionalInfo;
		case SPANISH:
			return SPANISH.get(message) + additionalInfo;
		default:
			return "ERROR GETTING MESSAGE (404)";
		}
	}
	
	public static final HashMap<Message, String> ENGLISH = createMapEnglish();
	public static final HashMap<Message, String> SPANISH = createMapSpanish();
	
    private static HashMap<Message, String> createMapEnglish() {
    	HashMap<Message, String> result = new HashMap<Message, String>();
        result.put(Message.COURSE_WASNT_ADDED_TO_SEMESTER_REPEATED,								"Course is already registered in this semester");
        result.put(Message.COURSE_WASNT_ADDED_TO_SEMESTER_NOT_COURSED_IN_SEMESTER,				"The course isn't dictated in this semester");
        result.put(Message.COURSE_WASNT_ADDED_TO_SEMESTER_MAX_CREDITS_EXCEEDED,					"The maximum of credits of this semester is exceeded");
        result.put(Message.COURSE_WASNT_ADDED_TO_SEMESTER_REQUIREMENTS,							"You do not have approved all requirements:");
        result.put(Message.COURSE_WASNT_ADDED_TO_SEMESTER_COREQUIREMENTS,						"You must take this course(s) too this semester:");
        result.put(Message.COURSES_WERE_ADDED_TO_SEMESTER,										"The courses were succsessfully added to the semester");
        result.put(Message.COURSE_WAS_ADDED_TO_SEMESTER, 										"The course was correctly added to the semester");
        result.put(Message.COURSE_WASNT_ADDED_TO_SEMESTER_EVALUATION_CLASH, 					"The course wasn't added due to an evaluation date conflict with another course's evaluation date");
        result.put(Message.COURSE_WASNT_ADDED_TO_SEMESTER_SCHEDULE_CLASH, 						"The course wasn't added due to a conflict between this course's schedule and another course's schedule");
        result.put(Message.COURSE_WAS_REMOVED_OF_SEMESTER, 										"The course was correctly removed of the semester");
        result.put(Message.COURSE_WASNT_REMOVED_OF_SEMESTER_NOT_IN_SEMESTER,					"The course is not in the semester");
        result.put(Message.REQUIREMENT_WAS_ADDED_TO_REQUIREMENTS, 								"The course was succsessfully added as a requirement");
        result.put(Message.REQUIREMENT_WASNT_ADDED_TO_REQUIREMENTS_REPEATED, 					"This course is already a requirement");
        result.put(Message.REQUIREMENT_WASNT_ADDED_TO_REQUIREMENTS_SAME_COURSE, 				"You can't add a course as a requirement of itself");
        result.put(Message.REQUIREMENT_WAS_REMOVED_OF_REQUIREMENTS, 							"The course was succsessfully removed from requirements");
        result.put(Message.REQUIREMENT_WASNT_REMOVED_OF_REQUIREMENTS_NOT_IN_REQUIREMENTS,		"This is not a requirement of the course");
        result.put(Message.COREQUIREMENT_WAS_ADDED_TO_COREQUIREMENTS, 							"The course was successfully added as a coRequirement");
        result.put(Message.COREQUIREMENT_WASNT_ADDED_TO_COREQUIREMENTS_REPEATED, 				"This course is already as a coRequirement");
        result.put(Message.COREQUIREMENT_WASNT_ADDED_TO_COREQUIREMENTS_SAME_COURSE, 			"You can't add a course as a coRequirement of itself");
        result.put(Message.COREQUIREMENT_WAS_REMOVED_OF_COREQUIREMENTS, 						"This course was removed from the coRequirements successfully");
        result.put(Message.COREQUIREMENT_WASNT_REMOVED_OF_COREQUIREMENTS_NOT_IN_COREQUIREMENTS,	"This course is not registered as a coRequirement");
        result.put(Message.COURSE_WASNT_CREATED_EVALUATION_CLASH, 								"Course wasn't created due to a clash with the evaluations classroom of the course");
        result.put(Message.COURSE_WASNT_CREATED_PROFESSOR_CLASH, 								"Course wasn't created due to a clash with the professors of the course");
        result.put(Message.COURSE_WASNT_CREATED_CLASSROOM_CLASH,								"Course wasn't created due to a clash with the course classrooms of the course");
        result.put(Message.COURSE_WAS_CREATED, 													"Course was successfully created");
        result.put(Message.COURSE_WASNT_DELETED_REQUIRED_FOR_COURSE, 							"The course couldn't be deleted because it's a prerequisite for another coures");
        result.put(Message.COURSE_WAS_DELETED, 													"The course was successfully deleted");
        return result;
    }
    
    private static HashMap<Message, String> createMapSpanish() {
    	HashMap<Message, String> result = new HashMap<Message, String>();
        result.put(Message.COURSE_WASNT_ADDED_TO_SEMESTER_REPEATED,								"Este curso ya esta ingresado en el semestre");
        result.put(Message.COURSE_WASNT_ADDED_TO_SEMESTER_NOT_COURSED_IN_SEMESTER,				"Este curso no se dicta en este semestre");
        result.put(Message.COURSE_WASNT_ADDED_TO_SEMESTER_MAX_CREDITS_EXCEEDED,					"El maximo de creditos de este semestre sobrepasa el limite");
        result.put(Message.COURSE_WASNT_ADDED_TO_SEMESTER_REQUIREMENTS,							"No cumple con los requisitos, falta(n):");
        result.put(Message.COURSE_WASNT_ADDED_TO_SEMESTER_COREQUIREMENTS,						"Para poder tomar el curso debes tomar tambien el(los) siguiente(s) curso(s):");
        result.put(Message.COURSES_WERE_ADDED_TO_SEMESTER,										"Los cursos fueron agregados al semestre satisfactoriamente");
        result.put(Message.COURSE_WAS_ADDED_TO_SEMESTER,										"El curso fue agregado correctamente al semestre");
        result.put(Message.COURSE_WASNT_ADDED_TO_SEMESTER_EVALUATION_CLASH,						"No se pudo agregar el curso debido a un tope de una evaluacion con la evaluacion de otro curso");
        result.put(Message.COURSE_WASNT_ADDED_TO_SEMESTER_SCHEDULE_CLASH,						"No se pudo agregar el curso debido a un tope de horario con otro curso");
        result.put(Message.COURSE_WAS_REMOVED_OF_SEMESTER,										"El curso fue removido correctamente del semestre");
        result.put(Message.COURSE_WASNT_REMOVED_OF_SEMESTER_NOT_IN_SEMESTER,					"Este curso no se encuentra en el semestre");
        result.put(Message.REQUIREMENT_WAS_ADDED_TO_REQUIREMENTS,								"El curso fue agregado como requisito exitosamente");
        result.put(Message.REQUIREMENT_WASNT_ADDED_TO_REQUIREMENTS_REPEATED,					"Este curso ya esta ingresado como requisito");
        result.put(Message.REQUIREMENT_WASNT_ADDED_TO_REQUIREMENTS_SAME_COURSE,					"No se puede agregar un curso como requisito de el mismo");
        result.put(Message.REQUIREMENT_WAS_REMOVED_OF_REQUIREMENTS,								"El curso fue removido exitosamente de los requisitos");
        result.put(Message.REQUIREMENT_WASNT_REMOVED_OF_REQUIREMENTS_NOT_IN_REQUIREMENTS,		"Este curso no es un requisito");
        result.put(Message.COREQUIREMENT_WAS_ADDED_TO_COREQUIREMENTS,							"El curso fue agregado como coRequisito satisfactoriamente");
        result.put(Message.COREQUIREMENT_WASNT_ADDED_TO_COREQUIREMENTS_REPEATED,				"Este curso ya esta dentro de los coRequisitos");
        result.put(Message.COREQUIREMENT_WASNT_ADDED_TO_COREQUIREMENTS_SAME_COURSE,				"No puedes agregar un curso como coRequisito de el mismo");
        result.put(Message.COREQUIREMENT_WAS_REMOVED_OF_COREQUIREMENTS,							"El curso fue removido de los coRequisitos satisfactoriamente");
        result.put(Message.COREQUIREMENT_WASNT_REMOVED_OF_COREQUIREMENTS_NOT_IN_COREQUIREMENTS,	"Este curso no se encuentra como coRequisito");
        result.put(Message.COURSE_WASNT_CREATED_EVALUATION_CLASH,								"El curso no pudo crearse ya que hay un tope de sala para evaluaciones");
        result.put(Message.COURSE_WASNT_CREATED_PROFESSOR_CLASH,								"El curso no pudo crearse ya que hay un tope de profesores");
        result.put(Message.COURSE_WASNT_CREATED_CLASSROOM_CLASH,								"El curso no pudo crearse ya que hay un tope de salas");
        result.put(Message.COURSE_WAS_CREATED,													"El curso fue creado correctamente");
        result.put(Message.COURSE_WASNT_DELETED_REQUIRED_FOR_COURSE,							"El curso no pudo ser eliminado ya que es requisitos para otros cursos");
        result.put(Message.COURSE_WAS_DELETED,													"El curso fue eliminado correctamente");
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
	
	public enum UILabel {
		STUDENT_CREATE_NEW_SEMESTER,
		STUDENT_EDIT_SEMESTER,
		STUDENT_SEE_CURRICULAR_ADVANCE,
		STUDENT_QUALIFY_SEMESTER,
		STUDENT_SEE_SCHEDULE,
		CHANGE_MODE_STUDENT_TO_ADMIN,
		CHANGE_MODE_ADMIN_TO_STUDENT,
		WELCOME_MESSAGE,
		WELCOME_MESSAGE_ADMIN,
		WHAT_TO_DO_NEXT_QUESTION,
		SIGN_IN,
		REGISTER,
		SIGN_IN_AS_USER,
		DONT_HAVE_ACCOUNT_REGISTER,
		LOG_IN,
		TO_USE_ATM_MUST_REGISTER,
		CONTINUE,
		ADMINISTRATOR,
		STUDENT,
		PROFFESOR,
		MALE,
		FEMALE,
		NAME,
		LAST_NAME_FATHER,
		LAST_NAME_MOTHER,
		ADDRESS,
		CELL_PHONE,
		RUT,
		BIRTHDAY,
		SEX,
		ACCESS,
		DAY,
		MONTH,
		YEAR,
		PICK_CARREER,
		LOGOUT,
		CREATE_NEW_STUDY_PROGRAM,
		EDIT_STUDY_PROGRAM
		
	}
	
	public static String getUILabel(UILabel label) {
		switch (LANGUAGE) {
		case ENGLISH:
			return UI_LABEL_ENGLISH.get(label);
		case SPANISH:
			return UI_LABEL_SPANISH.get(label);
		default:
			return "ERROR GETTING UILabel (404)";
		}
	}
	
	public static final Map<UILabel, String> UI_LABEL_ENGLISH = createMapUILabelEnglish();
	public static final Map<UILabel, String> UI_LABEL_SPANISH = createMapUILabelSpanish();
	
	private static Map<UILabel, String> createMapUILabelEnglish() {
        Map<UILabel, String> result = new HashMap<UILabel, String>();
        result.put(UILabel.STUDENT_CREATE_NEW_SEMESTER,		"Create new semester");
        result.put(UILabel.STUDENT_EDIT_SEMESTER,		"Edit a semester");
        result.put(UILabel.STUDENT_QUALIFY_SEMESTER,		"Qualify a semester");
        result.put(UILabel.STUDENT_SEE_CURRICULAR_ADVANCE,	"See curricular progress");
        result.put(UILabel.STUDENT_SEE_SCHEDULE,			"See my schedule");
        result.put(UILabel.CHANGE_MODE_ADMIN_TO_STUDENT,	"Change to Student mode");
        result.put(UILabel.CHANGE_MODE_STUDENT_TO_ADMIN,	"Change to Admin mode");
        result.put(UILabel.WELCOME_MESSAGE,					"WELCOME TO ACADEMIC TOOL MANAGER PROFESSIONAL!!!");
        result.put(UILabel.WELCOME_MESSAGE_ADMIN,			"WELCOME TO ACADEMIC TOOL MANAGER PROFESSIONAL [ADMIN]");
        result.put(UILabel.WHAT_TO_DO_NEXT_QUESTION,		"What do you want to do next?");
        result.put(UILabel.SIGN_IN, 						"Sign In");
        result.put(UILabel.REGISTER, 						"Register");
        result.put(UILabel.SIGN_IN_AS_USER, 				"Sign In as User");
        result.put(UILabel.DONT_HAVE_ACCOUNT_REGISTER, 		"Don't have an account?, Register!");
        result.put(UILabel.LOG_IN, 							"LOG IN");
        result.put(UILabel.TO_USE_ATM_MUST_REGISTER, 		"To use Academic Tool Manager Professional you must get registered first:");
        result.put(UILabel.CONTINUE, 						"Continue");
        result.put(UILabel.ADMINISTRATOR, 					"Administrator");
        result.put(UILabel.STUDENT, 						"Student");
        result.put(UILabel.PROFFESOR, 						"Proffesor");
        result.put(UILabel.MALE, 							"Male");
        result.put(UILabel.FEMALE, 							"Female");
        result.put(UILabel.NAME, 							"Name");
        result.put(UILabel.LAST_NAME_FATHER, 				"Mother's last name");
        result.put(UILabel.LAST_NAME_MOTHER, 				"Father's last name");
        result.put(UILabel.ADDRESS, 						"Adress");
        result.put(UILabel.CELL_PHONE, 						"Cellphone number");
        result.put(UILabel.RUT, 							"R.U.T.");
        result.put(UILabel.BIRTHDAY, 						"Birth Date");
        result.put(UILabel.SEX, 							"Sex");
        result.put(UILabel.ACCESS, 							"Access type");
        result.put(UILabel.DAY, 							"Day");
        result.put(UILabel.MONTH, 							"Month");
        result.put(UILabel.YEAR, 							"Year");
        result.put(UILabel.PICK_CARREER, 					"Pick Carreer(s)");
        result.put(UILabel.LOGOUT, 							"LogOut");
        result.put(UILabel.CREATE_NEW_STUDY_PROGRAM, 		"Create new Study Program");
        result.put(UILabel.EDIT_STUDY_PROGRAM, 				"Edit an existing Study Program");
        return Collections.unmodifiableMap(result);
    }
	
	private static Map<UILabel, String> createMapUILabelSpanish() {
        Map<UILabel, String> result = new HashMap<UILabel, String>();
        result.put(UILabel.STUDENT_CREATE_NEW_SEMESTER,		"Crear nuevo semestre");
        result.put(UILabel.STUDENT_EDIT_SEMESTER,		"Editar nuevo semestre");
        result.put(UILabel.STUDENT_QUALIFY_SEMESTER,		"Calificar un semestre");
        result.put(UILabel.STUDENT_SEE_CURRICULAR_ADVANCE,	"Ver avance curricular");
        result.put(UILabel.STUDENT_SEE_SCHEDULE,			"Ver mi horario");
        result.put(UILabel.CHANGE_MODE_ADMIN_TO_STUDENT,	"Cambiar a modo Estudiante");
        result.put(UILabel.CHANGE_MODE_STUDENT_TO_ADMIN,	"Cambiar a modo Administrador");
        result.put(UILabel.WELCOME_MESSAGE,					"BIENVENIDO A ACADEMIC TOOL MANAGER PROFESSIONAL!!!");
        result.put(UILabel.WHAT_TO_DO_NEXT_QUESTION,		"�Que deseas hacer ahora?");
        result.put(UILabel.SIGN_IN, 						"Iniciar Sesion");
        result.put(UILabel.REGISTER, 						"Registrarse");
        result.put(UILabel.SIGN_IN_AS_USER, 				"Iniciar como usuario");
        result.put(UILabel.DONT_HAVE_ACCOUNT_REGISTER, 		"�No tienes una cuenta?, Registrate!");
        result.put(UILabel.LOG_IN, 							"INICIO DE SESION");
        result.put(UILabel.TO_USE_ATM_MUST_REGISTER, 		"Para poder usar Academic Tool Manager Professional debes registrarte pimero:");
        result.put(UILabel.CONTINUE, 						"Continuar");
        result.put(UILabel.ADMINISTRATOR, 					"Administrador");
        result.put(UILabel.STUDENT, 						"Alumno");
        result.put(UILabel.PROFFESOR, 						"Profesor");
        result.put(UILabel.MALE, 							"Masculino");
        result.put(UILabel.FEMALE, 							"Femenino");
        result.put(UILabel.NAME, 							"Nombre");
        result.put(UILabel.LAST_NAME_FATHER, 				"Apellido Paterno");
        result.put(UILabel.LAST_NAME_MOTHER, 				"Apellido Materno");
        result.put(UILabel.ADDRESS, 						"Direccion");
        result.put(UILabel.CELL_PHONE, 						"Numero de celular");
        result.put(UILabel.RUT, 							"R.U.T.");
        result.put(UILabel.BIRTHDAY, 						"Fecha de Nacimiento");
        result.put(UILabel.SEX, 							"Sexo");
        result.put(UILabel.ACCESS, 							"Tipo de Acceso");
        result.put(UILabel.DAY, 							"Dia");
        result.put(UILabel.MONTH, 							"Mes");
        result.put(UILabel.YEAR, 							"Ano");
        result.put(UILabel.PICK_CARREER, 					"Elegir Carrera(s)");
        result.put(UILabel.LOGOUT, 							"Cerrar Sesion");
        result.put(UILabel.CREATE_NEW_STUDY_PROGRAM, 		"Crear una nueva Malla");
        result.put(UILabel.EDIT_STUDY_PROGRAM, 				"Editar una Malla existente");
        return Collections.unmodifiableMap(result);
    }
}