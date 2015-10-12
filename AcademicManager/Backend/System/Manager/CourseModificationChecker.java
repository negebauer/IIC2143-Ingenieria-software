package System.Manager;

import java.util.ArrayList;

import System.Courses.Course;
import System.Courses.Evaluation;
import System.Users.Professor;
import Tools.Interfaces.ICourse;
import Tools.Interfaces.IProfessors;
import Tools.Others.Messages;
import Tools.Others.Messages.Message;

// TODO Write java doc
/**
 * Manages the Course database
 */
public class CourseModificationChecker {

//	TODO Connect to manager when Admin tries to create a new course
	public static ModifyCourseResponse courseCanBeCreated(ArrayList<Course> courses, Course newCourse) {
		String evaluationClassRoomClash = "";
		String courseClassRoomClash = "";
		String courseProfessorClash = "";
		boolean success = false;
		String response = "NoResponse";
		
		for (Course course : courses) {
			for (Evaluation evaluation : course.getEvaluations()) {
				for (Evaluation newEvaluation : newCourse.getEvaluations()) {
					if (evaluation.getDate() == newEvaluation.getDate() && evaluation.getClassroom() == newEvaluation.getClassroom()) {
						evaluationClassRoomClash = evaluationClassRoomClash + course.getInitials() + " " + evaluation.getCourseEvaluation() + "\n";
					}
				}
			}
			for (ICourse icourse : course.getCourses()) {
				for (ICourse newICourse : newCourse.getCourses()) {
					if (icourse.getSchedule().scheduleClash(newICourse.getSchedule())) {
						courseClassRoomClash = courseClassRoomClash + getClassroomClash(icourse, newICourse, course.getInitials());
						if (icourse instanceof IProfessors && newICourse instanceof IProfessors) {
							courseProfessorClash = courseProfessorClash + getProfessorClash((IProfessors) icourse, (IProfessors) newICourse, course.getInitials());
						}
					}
				}
			}
		}
		
		if (evaluationClassRoomClash != "" || courseClassRoomClash != "" || courseProfessorClash != "") {
			success = false;
			String messageEvaluationClassRoomClash = evaluationClassRoomClash != null ? Messages.getMessage(Message.COURSE_WASNT_CREATED_EVALUATION_CLASH.index(), evaluationClassRoomClash) : "";
			String messageCourseClassRoomClash = evaluationClassRoomClash != null ? Messages.getMessage(Message.COURSE_WASNT_CREATED_CLASSROOM_CLASH.index(), courseClassRoomClash) : "";
			String messageCourseProfessorClash = evaluationClassRoomClash != null ? Messages.getMessage(Message.COURSE_WASNT_CREATED_PROFESSOR_CLASH.index(), courseProfessorClash) : "";
			response = messageEvaluationClassRoomClash +"\n" + messageCourseClassRoomClash +"\n" + messageCourseProfessorClash;
			response = cleanNewLineCharExcessFromString(response);
		} else {
			success = true;
			response = Messages.getMessage(Message.COURSE_WAS_CREATED.index());
		}
		return new CourseModificationChecker().new ModifyCourseResponse(success, response);
	}
	
//	TODO Check if a modification to a course can be made	
//	public static ModifyCourseResponse courseCanBeModified(ArrayList<Course> courses, Course modifiedCourse) {
//		String classRoomClash = "";
//		String professorClash = "";
//		boolean success = false;
//		String response = "NoResponse";
//		
//		return new CourseModificationChecker().new CreateCourseResponse(success, response);
//	}
	
	public static ModifyCourseResponse courseCanBeDeleted(ArrayList<Course> courses, Course deleteCourse) {
		String requisites = "";
		boolean success = false;
		String response = "NoResponse";
		
		for (Course course : courses) {
			if (course.getRequirements().contains(deleteCourse)) {
				requisites = requisites + course.getInitials() + "\n";
			}
 		}
		return new CourseModificationChecker().new ModifyCourseResponse(success, response);
	}
	
	public static String getClassroomClash(ICourse icourse, ICourse newICourse, String courseInitials) {
		String classRoomClash = "";
		if (icourse.getClassroom() == newICourse.getClassroom()) {
			classRoomClash = classRoomClash + "\n" + courseInitials;
		}
		return classRoomClash;
	}
	
	public static String getProfessorClash(IProfessors iprofessors, IProfessors newIProfessors, String courseInitials) {
		String professorClash = "";
		for (Professor professor : newIProfessors.getProfessors()) {
			if (iprofessors.getProfessors().contains(professor)) {
				professorClash = professorClash + "\n" + courseInitials + " " + professor.getName() + " " + professor.getLastnameFather() + " " + professor.getLastnameMother();
			}
		}
		return professorClash;
	}
	
	public static String cleanNewLineCharExcessFromString(String stringToClean) {
		ArrayList<String> cleanedString = new ArrayList<String>();
		for (String character : stringToClean.split("\n")) {
			if (cleanedString.size() == 0 && !character.isEmpty()) {
				cleanedString.add(character);
			} else if (cleanedString.size() > 0 && !(cleanedString.get(cleanedString.size() - 1).isEmpty() && character.isEmpty())) {
				cleanedString.add(character);
			}
		}
		return String.join("\n", cleanedString);
	}
	
	/**
	 * Class used as a data container for answering create course call.
	 */
	public class ModifyCourseResponse {
		public boolean success;
		public String response;
		
		/**
		 * Creates a new instance of CreateCourseResponse.
		 * @param success Whether the course can be created or no.
		 * @param response A String containing a User friendly response specifying the result of the createCourse call.
		 */
		public ModifyCourseResponse(boolean success, String response) {
			this.success = success;
			this.response = response;
		}
	}
}
