package System.Manager;

import java.util.ArrayList;

import System.Courses.Course;
import System.Courses.Evaluation;
import System.Users.Professor;
import Tools.Enums.AcademicSemester;
import Tools.Enums.School;
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
	public static ModifyCourseResponse courseCanBeCreated(ArrayList<Course> currentCourses, Course newCourse) {
		String evaluationClassRoomClash = "";
		String courseClassRoomClash = "";
		String courseProfessorClash = "";
		boolean success = false;
		String response = "NoResponse";
		
		for (Course course : currentCourses) {
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
		return new CourseModificationChecker().new ModifyCourseResponse(success, response, newCourse);
	}
	
	public static ModifyCourseResponse courseCanBeModified(ArrayList<Course> currentCourses, Course courseToModify, String name, String initials, int section, int credits, String details, School school, AcademicSemester semester, ArrayList<ICourse> courses, ArrayList<Evaluation> evaluations, ArrayList<Course> requirements) {
		Course modifiedCourse = new Course(name, initials, section, credits, details, school, semester, courses, evaluations, requirements);
		currentCourses.remove(courseToModify);
		ModifyCourseResponse modifyCourseResponse = courseCanBeCreated(currentCourses, modifiedCourse);
		if (modifyCourseResponse.success) {
			modifyCourseResponse.course = modifiedCourse;
		} else {
			modifyCourseResponse.course = courseToModify;
		}
		return modifyCourseResponse;
	}
	
	public static ModifyCourseResponse courseCanBeDeleted(ArrayList<Course> currentCourses, Course deleteCourse) {
		String requisites = "";
		boolean success = false;
		String response = "NoResponse";
		
		for (Course course : currentCourses) {
			if (course.getRequirements().contains(deleteCourse)) {
				requisites = requisites + course.getInitials() + "\n";
			}
 		}
		
		if (requisites != "") {
			success = false;
			response = Messages.getMessage(Message.COURSE_WASNT_DELETED_REQUIRED_FOR_COURSE.index(), requisites);
		} else {
			success = true;
			response = Messages.getMessage(Message.COURSE_WAS_DELETED.index());
		}
		return new CourseModificationChecker().new ModifyCourseResponse(success, response, deleteCourse);
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
		public Course course;
		
		/**
		 * Creates a new instance of CreateCourseResponse.
		 * @param success Whether the course can be created or no.
		 * @param response A String containing a User friendly response specifying the result of the createCourse call.
		 * @param course The course that was modified, created or deleted.
		 */
		public ModifyCourseResponse(boolean success, String response, Course course) {
			this.success = success;
			this.response = response;
		}
	}
}
