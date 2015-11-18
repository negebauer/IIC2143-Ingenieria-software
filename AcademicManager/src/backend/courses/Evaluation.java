package backend.courses;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import backend.others.Messages;
import backend.others.Utilities;

/**
 * Class that represents an Evaluation of a Course.
 */
public class Evaluation {
	
	public enum CourseEvaluation {
		INTERROGATION ("I"),
		EXAM ("E"),
		CONTROL ("C");
		
		private final String evaluation;
		CourseEvaluation(String evaluation) {
	        this.evaluation = evaluation;
		}
		
		public String getEvaluation() {
			return this.evaluation;
		}
		
		public static final Map<CourseEvaluation, String> ACADEMIC_SEMESTER_MESSAGE_ENGLISH = createMapCourseEvaluationMessageEnglish();
		public static final Map<CourseEvaluation, String> ACADEMIC_SEMESTER_SPANISH = createMapCourseEvaluationMessageSpanish();
		
		public static String getCourseEvaluationMessage(CourseEvaluation courseEvaluation) {
			switch (Messages.LANGUAGE()) {
			case ENGLISH:
				return ACADEMIC_SEMESTER_MESSAGE_ENGLISH.get(courseEvaluation);
			case SPANISH:
				return ACADEMIC_SEMESTER_SPANISH.get(courseEvaluation);
			default:
				return Messages.ERROR_MESSAGE;
			}
		}
		
		private static Map<CourseEvaluation, String> createMapCourseEvaluationMessageEnglish() {
	        Map<CourseEvaluation, String> result = new HashMap<CourseEvaluation, String>();
	        result.put(CourseEvaluation.INTERROGATION,								"Interrogation");
	        result.put(CourseEvaluation.EXAM,										"Exam");
	        result.put(CourseEvaluation.CONTROL,									"Control");
	        return Collections.unmodifiableMap(result);
		}
		
		private static Map<CourseEvaluation, String> createMapCourseEvaluationMessageSpanish() {
			Map<CourseEvaluation, String> result = new HashMap<CourseEvaluation, String>();
	        result.put(CourseEvaluation.INTERROGATION,								"Interrogacion");
	        result.put(CourseEvaluation.EXAM,										"Examen");
	        result.put(CourseEvaluation.CONTROL,									"Control");
	        return Collections.unmodifiableMap(result);
		}
		
		public static CourseEvaluation getCourseEvaluation(String evaluationString) {
			for (CourseEvaluation courseEvaluation : CourseEvaluation.values()) {
				if (getCourseEvaluationMessage(courseEvaluation) == evaluationString) {
					return courseEvaluation;
				}
			}
			return null;
		}
	}
	
	private CourseEvaluation courseEvaluation;
	private Classroom classroom;
	private Date date;

	/**
	 * 
	 * @param courseEvaluation
	 * @param classroom
	 * @param dateString
	 */
	public Evaluation(CourseEvaluation courseEvaluation, Classroom classroom, String dateString) {
		this.courseEvaluation = courseEvaluation;
		this.classroom = classroom;
		this.date = Utilities.getDateFromString(dateString);
	}

	/**
	 * @return The type of course evaluation of this Evaluation.
	 */
	public CourseEvaluation getCourseEvaluation() {
		return courseEvaluation;
	}
	
	public void setCourseEvaluation(CourseEvaluation courseEvaluation) {
		this.courseEvaluation = courseEvaluation;
	}

	/**
	 * @return The classroom where the Evaluation will take place.
	 */
	public Classroom getClassroom() {
		return classroom;
	}

	/**
	 * Modifies the classroom in which the Evaluation will take place.
	 * @param classroom The new classroom for the Evaluation.
	 */
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	/**
	 * @return The date in which the Evaluation will take place.
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Modifies the date in which the Evaluation will take place.
	 * @param classroom The new date for the Evaluation.
	 */
	public void setDate(Date date) {
		this.date = date;
	}
}
