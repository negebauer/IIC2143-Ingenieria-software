package backend.enums;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import backend.others.Messages;

public enum School {
	ADMINISTRATIVESCIENCES,
	AESTHETICS,
	AGRICULTURE,
	ARCHITECTURE,
	ARTS,
	ASTROPHYSICS,
	BIOLOGICALSCIENCES,
	CHEMISTRY,
	CIVILCONSTRUCTION,
	COLLEGE,
	COMMUNICATIONS,
	DESIGN,
	DRAMA,
	ECONOMICS,
	EDUCATION,
	ENGINEERING,
	FORESTRY,
	GEOGRAPHY,
	HISTORY,
	LAW,
	LETTERS,
	MATHEMATICS,
	MEDICINE,
	MUSIC,
	NURSING,
	PHILOSOFY,
	PHYSICS,
	POLITICALSCIENCES,
	PSICOLOGIA,
	SOCIALOGY,
	SOCIALWORK,
	THEOLOGY,
	URBANSTUDIES,
	UNKNOWN;
	
	public static School defaultSchool() {
		return School.UNKNOWN;
	}
	
	public static final Map<School, String> SCHOOL_MESSAGE_ENGLISH = createMapSchoolMessageEnglish();
	public static final Map<School, String> SCHOOL_MESSAGE_SPANISH = createMapSchoolMessageSpanish();
	
	public static String getSchoolMessage(School school) {
		switch (Messages.LANGUAGE()) {
		case ENGLISH:
			return SCHOOL_MESSAGE_ENGLISH.get(school);
		case SPANISH:
			return SCHOOL_MESSAGE_SPANISH.get(school);
		default:
			return Messages.ERROR_MESSAGE;
		}
	}
	
	private static Map<School, String> createMapSchoolMessageEnglish() {
        Map<School, String> result = new HashMap<School, String>();
        result.put(School.ADMINISTRATIVESCIENCES,							"Administrative Services");
        result.put(School.AESTHETICS,										"Aesthetics");
        result.put(School.AGRICULTURE,										"Agriculture");
        result.put(School.ARCHITECTURE,										"Architecture");
        result.put(School.ARTS,												"Arts");
        result.put(School.ASTROPHYSICS,										"AstroPhysics");
        result.put(School.BIOLOGICALSCIENCES,								"Biological Sciences");
        result.put(School.CHEMISTRY,										"Chemistry");
        result.put(School.CIVILCONSTRUCTION,								"Civil Construction");
        result.put(School.COLLEGE,											"College");
        result.put(School.COMMUNICATIONS,									"Communications");
        result.put(School.DESIGN,											"Design");
        result.put(School.DRAMA,											"Drama");
        result.put(School.ECONOMICS,										"Economics");
        result.put(School.EDUCATION,										"Education");
        result.put(School.ENGINEERING,										"Engineering");
        result.put(School.FORESTRY,											"Forestry");
        result.put(School.GEOGRAPHY,										"Geography");
        result.put(School.HISTORY,											"History");
        result.put(School.LAW,												"Law");
        result.put(School.LETTERS,											"Letters");
        result.put(School.MATHEMATICS,										"Mathematics");
        result.put(School.MEDICINE,											"Medicine");
        result.put(School.MUSIC,											"Music");
        result.put(School.NURSING,											"Nursing");
        result.put(School.PHILOSOFY,										"Philosofy");
        result.put(School.PHYSICS,											"Physics");
        result.put(School.POLITICALSCIENCES,								"Political Sciences");
        result.put(School.PSICOLOGIA,										"Psicology");
        result.put(School.SOCIALOGY,										"Sociology");
        result.put(School.SOCIALWORK,										"Social Work");
        result.put(School.THEOLOGY,											"Theology");
        result.put(School.URBANSTUDIES,										"Urban Studies");
        result.put(School.UNKNOWN,											"Unknown");
        return Collections.unmodifiableMap(result);
	}
	
	private static Map<School, String> createMapSchoolMessageSpanish() {
		Map<School, String> result = new HashMap<School, String>();
        result.put(School.ADMINISTRATIVESCIENCES,							"Ciencias Administrativas");
        result.put(School.AESTHETICS,										"Estetica");
        result.put(School.AGRICULTURE,										"Agricultura");
        result.put(School.ARCHITECTURE,										"Arquitectura");
        result.put(School.ARTS,												"Arte");
        result.put(School.ASTROPHYSICS,										"Astrofisica");
        result.put(School.BIOLOGICALSCIENCES,								"Ciencias Biologicas");
        result.put(School.CHEMISTRY,										"Quimica");
        result.put(School.CIVILCONSTRUCTION,								"Construccion Civil");
        result.put(School.COLLEGE,											"College");
        result.put(School.COMMUNICATIONS,									"Comunicaciones");
        result.put(School.DESIGN,											"Diseno");
        result.put(School.DRAMA,											"Drama");
        result.put(School.ECONOMICS,										"Economia");
        result.put(School.EDUCATION,										"Educacion");
        result.put(School.ENGINEERING,										"Ingenieria");
        result.put(School.FORESTRY,											"Forestal");
        result.put(School.GEOGRAPHY,										"Geografia");
        result.put(School.HISTORY,											"Historia");
        result.put(School.LAW,												"Leyes");
        result.put(School.LETTERS,											"Letras");
        result.put(School.MATHEMATICS,										"Matematicas");
        result.put(School.MEDICINE,											"Medicina");
        result.put(School.MUSIC,											"Musica");
        result.put(School.NURSING,											"Enfermeria");
        result.put(School.PHILOSOFY,										"Filosofia");
        result.put(School.PHYSICS,											"Fisica");
        result.put(School.POLITICALSCIENCES,								"Ciencias Politicas");
        result.put(School.PSICOLOGIA,										"Psicologia");
        result.put(School.SOCIALOGY,										"Sociologia");
        result.put(School.SOCIALWORK,										"Trabajo Social");
        result.put(School.THEOLOGY,											"Teologia");
        result.put(School.URBANSTUDIES,										"Estudios Urbanos");
        result.put(School.UNKNOWN,											"Desconocido");
        return Collections.unmodifiableMap(result);
	}
	
	public static School getSchool(String schoolString) {
		for (School school : School.values()) {
			if (getSchoolMessage(school) == schoolString) {
				return school;
			}
		}
		return School.defaultSchool();
	}
}
