package backend.manager;

import java.util.ArrayList;

import backend.courses.Assistantship;
import backend.courses.Classroom;
import backend.courses.Course;
import backend.courses.Evaluation;
import backend.courses.Laboratory;
import backend.courses.Lecture;
import backend.courses.Schedule;
import backend.interfaces.ICourse;
import backend.interfaces.IProfessors;
import backend.others.Messages;
import backend.others.Messages.Message;
import backend.others.Utilities;
import backend.users.Assistant;
import backend.users.Professor;

/**
 * Class that checks if a course modification can be done.
 */
public class CourseModificationChecker {

	static ArrayList<Course> currentCourses = Manager.INSTANCE.courses;

	/**
	 * Checks if a Course can be created by checking the following situations:
	 * <ul>
	 * <li>If there is an evaluation classroom clash (two evaluations at the
	 * same date in same classroom).
	 * <li>If there is a course classroom clash (two (physical) courses at the
	 * same day:module in same classroom).
	 * <li>If there is a professor clash (two professors assigned at the same
	 * day:module to different courses).
	 * </ul>
	 * 
	 * @param currentCourses
	 *            The courses that already exist.
	 * @param newCourse
	 *            The course that wants to be created.
	 * @return A ModifyCourseResponse detailing if the creation can be done.
	 */
	public static ModifyCourseResponse courseCanBeCreated(Course newCourse) {
		String evaluationClassRoomClash = "";
		String courseClassRoomClash = "";
		String courseProfessorClash = "";
		boolean success = false;
		String response = "NoResponse";

		for (Course course : currentCourses) {
			for (Evaluation evaluation : course.getEvaluations()) {
				for (Evaluation newEvaluation : newCourse.getEvaluations()) {
					if (evaluation.getDate() == newEvaluation.getDate()
							&& evaluation.getClassroom() == newEvaluation.getClassroom()) {
						evaluationClassRoomClash = evaluationClassRoomClash + course.getInitials() + " "
								+ evaluation.getCourseEvaluation() + "\n";
					}
				}
			}
			for (ICourse icourse : course.getCourses()) {
				for (ICourse newICourse : newCourse.getCourses()) {
					if (icourse.getSchedule().scheduleClash(newICourse.getSchedule())) {
						courseClassRoomClash = courseClassRoomClash
								+ getClassroomClash(icourse, newICourse, course.getInitials());
						if (icourse instanceof IProfessors && newICourse instanceof IProfessors) {
							courseProfessorClash = courseProfessorClash + getProfessorClash((IProfessors) icourse,
									(IProfessors) newICourse, course.getInitials());
						}
					}
				}
			}
		}

		if (evaluationClassRoomClash != "" || courseClassRoomClash != "" || courseProfessorClash != "") {
			success = false;
			String messageEvaluationClassRoomClash = evaluationClassRoomClash != null
					? Messages.getMessage(Message.COURSE_WASNT_CREATED_EVALUATION_CLASH, evaluationClassRoomClash) : "";
			String messageCourseClassRoomClash = courseClassRoomClash != null
					? Messages.getMessage(Message.COURSE_WASNT_CREATED_CLASSROOM_CLASH, courseClassRoomClash) : "";
			String messageCourseProfessorClash = courseProfessorClash != null
					? Messages.getMessage(Message.COURSE_WASNT_CREATED_PROFESSOR_CLASH, courseProfessorClash) : "";
			response = messageEvaluationClassRoomClash + "\n" + messageCourseClassRoomClash + "\n"
					+ messageCourseProfessorClash;
			response = Utilities.cleanNewLineCharExcessFromString(response);
		} else {
			success = true;
			response = Messages.getMessage(Message.COURSE_WAS_CREATED);
		}
		return new CourseModificationChecker().new ModifyCourseResponse(success, response, newCourse);
	}

	/**
	 * Checks if a Course can be modified by checking the following situations:
	 * <ul>
	 * <li>If there is an evaluation classroom clash (two evaluations at the
	 * same date in same classroom).
	 * <li>If there is a course classroom clash (two (physical) courses at the
	 * same day:module in same classroom).
	 * <li>If there is a professor clash (two professors assigned at the same
	 * day:module to different courses).
	 * </ul>
	 * 
	 * @param currentCourses
	 *            The courses that already exist.
	 * @param courseToModify
	 *            The course that's going to be modified.
	 * @param name
	 *            The name of the Course.
	 * @param initials
	 *            The initials of the Course.
	 * @param section
	 *            The section of the Course.
	 * @param credits
	 *            How much credits this Course is worth.
	 * @param details
	 *            The details of this Course.
	 * @param school
	 *            The school to which this Course belongs.
	 * @param semester
	 *            The semester in which this Course is dictated.
	 * @param courses
	 *            The courses (physical classes) of this Course.
	 * @param evaluations
	 *            The evaluations of the Course.
	 * @param requisites
	 *            The requisites of this Course.
	 * @param coRequirements
	 *            The coRequisites of this Course.
	 * @return A ModifyCourseResponse detailing if the modification can be done
	 *         containing the modified Course.
	 */
	public static ModifyCourseResponse courseCanBeModified(Course courseToModify, Course modifiedCourse) {
		currentCourses.remove(courseToModify);
		ModifyCourseResponse modifyCourseResponse = courseCanBeCreated(modifiedCourse);
		if (modifyCourseResponse.success) {
			modifyCourseResponse.course = modifiedCourse;
		} else {
			modifyCourseResponse.course = courseToModify;
		}
		return modifyCourseResponse;
	}

	/**
	 * Checks if a Course can be modified by checking the following situations:
	 * <ul>
	 * <li>The course is a requisite of another Course.
	 * <li>The course is a coRequisite of another Course.
	 * </ul>
	 * 
	 * @param currentCourses
	 * @param deleteCourse
	 * @return A ModifyCourseResponse detailing if the deletion can be done.
	 */
	public static ModifyCourseResponse courseCanBeDeleted(Course deleteCourse) {
		String requisites = "";
		String coRequisites = "";
		boolean success = false;
		String response = "NoResponse";

		for (Course course : currentCourses) {
			if (course.getRequirements().contains(deleteCourse)) {
				requisites = requisites + course.getInitials() + "\n";
			} else if (course.getCoRequirements().contains(deleteCourse)) {
				coRequisites = coRequisites + course.getInitials() + "\n";
			}
		}

		if (requisites != "" || coRequisites != "") {
			success = false;
			String messageRequisite = requisites != null
					? Messages.getMessage(Message.COURSE_WASNT_DELETED_REQUIRED_FOR_COURSE, requisites) : "";
			String messageCoRequisite = coRequisites != null
					? Messages.getMessage(Message.COURSE_WASNT_DELETED_REQUIRED_FOR_COURSE, coRequisites) : "";
			response = messageRequisite + "\n" + messageCoRequisite;
			response = Utilities.cleanNewLineCharExcessFromString(response);
		} else {
			success = true;
			response = Messages.getMessage(Message.COURSE_WAS_DELETED);
		}
		return new CourseModificationChecker().new ModifyCourseResponse(success, response, deleteCourse);
	}

	/**
	 * Checks for classroom clash (a classroom being used by two classes at the
	 * same day:module).
	 * 
	 * @param icourse
	 *            The courses to be checked against.
	 * @param newICourse
	 *            The courses that wants to use classrooms.
	 * @param courseInitials
	 *            The initials of the course passed as iCourse.
	 * @return A string detailing the courses that clash in a classroom.
	 */
	public static String getClassroomClash(ICourse icourse, ICourse newICourse, String courseInitials) {
		String classRoomClash = "";
		if (icourse.getClassroom() == newICourse.getClassroom()) {
			classRoomClash = classRoomClash + "\n" + courseInitials;
		}
		return classRoomClash;
	}

	/**
	 * Checks for professor clash (a professor doing two classes at the same
	 * day:module).
	 * 
	 * @param iprofessors
	 *            The courses that contains professors to be checked.
	 * @param newIProfessors
	 *            The new course that contains professors to be checked.
	 * @param courseInitials
	 *            The initials of the course passed as iprofessors.
	 * @return A string detailing the professors that clash.
	 */
	public static String getProfessorClash(IProfessors iprofessors, IProfessors newIProfessors, String courseInitials) {
		String professorClash = "";
		for (Professor professor : newIProfessors.getProfessors()) {
			if (iprofessors.getProfessors().contains(professor)) {
				professorClash = professorClash + "\n" + courseInitials + " " + professor.getName() + " "
						+ professor.getLastnameFather() + " " + professor.getLastnameMother();
			}
		}
		return professorClash;
	}
	
	public static String professorClash(Professor professor, Schedule schedule, ICourse icourse) {
		String clash = "";
		for (Course course : Manager.INSTANCE.courses) {
			for (ICourse iCourse : course.getCourses()) {
				if (icourse != iCourse) {
					if (iCourse instanceof Laboratory) {
					if (((Laboratory)iCourse).getProfessors().contains(professor)) {
						if (iCourse.getSchedule().scheduleClash(schedule)) {
							if (clash != "") {
								clash += ", " + course.getInitials() + "-" + iCourse.toString();	
							} else {
								clash += " " + course.getInitials() + "-" + iCourse.toString();	
							}
						}
					}
					} else if (iCourse instanceof Lecture) {
						if (((Lecture)iCourse).getProfessors().contains(professor)) {
							if (iCourse.getSchedule().scheduleClash(schedule)) {
								if (clash != "") {
									clash += ", " + course.getInitials() + "-" + iCourse.toString();	
								} else {
									clash += " " + course.getInitials() + "-" + iCourse.toString();	
								}
							}
						}
					}
				}
			}
		}
		
		return clash;
	}
	
	public static String assistantClash(Assistant assistant, Schedule schedule, ICourse icourse) {
		String clash = "";
		for (Course course : Manager.INSTANCE.courses) {
			for (ICourse iCourse : course.getCourses()) {
				if (icourse != iCourse) {
					if (iCourse instanceof Assistantship) {
						if (((Assistantship)iCourse).getAssistants().contains(assistant)) {
							if (iCourse.getSchedule().scheduleClash(schedule)) {
								if (clash != "") {
									clash += ", " + course.getInitials() + "-" + iCourse.toString();	
								} else {
									clash += " " + course.getInitials() + "-" + iCourse.toString();	
								}
							}
						}
					}
				}		
			}
		}
		
		return clash;
	}
	
	public static String classroomClash(Classroom classroom, Schedule schedule, ICourse icourse) {
		String clashes = "";
		for (Course course : Manager.INSTANCE.courses) {
			for (ICourse iCourse : course.getCourses()) {
				if (icourse != iCourse) {
					if (iCourse.getSchedule().scheduleClash(schedule)) {
						if (iCourse.getClassroom() == classroom) {
							if (clashes != "") {
							clashes += ", " + course.getInitials() + "-" + iCourse.toString();	
							} else {
								clashes += " " + course.getInitials() + "-" + iCourse.toString();	
							}
						}
						
					}
				}
			}
		}
		return clashes; 
	}
	
	/**
	 * Class used as a data container for answering create course call. Contains
	 * the Course that is trying to be created, deleted or modified.
	 */
	public class ModifyCourseResponse {
		public boolean success;
		public String response;
		public Course course;

		/**
		 * Creates a new instance of CreateCourseResponse.
		 * 
		 * @param success
		 *            Whether the course can be created or no.
		 * @param response
		 *            A String containing a User friendly response specifying
		 *            the result of the createCourse call.
		 * @param course
		 *            The course that was modified, created or deleted.
		 */
		public ModifyCourseResponse(boolean success, String response, Course course) {
			this.success = success;
			this.response = response;
		}
	}
}
