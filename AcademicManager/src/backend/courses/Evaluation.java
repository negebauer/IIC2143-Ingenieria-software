package backend.courses;

import java.util.Date;

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
