package System.Manager;

import java.util.ArrayList;

import System.Courses.Course;
import System.Users.Professor;
import Tools.Interfaces.ICourse;
import Tools.Interfaces.IProfessors;
import Tools.Others.Messages;
import Tools.Others.Messages.Message;

/**
 * Manages the Course database
 */
public class CourseManager {

	public static CreateCourseResponse courseCanBeAdded(ArrayList<Course> courses, Course newCourse) {
		String classRoomClash = "";
		String professorClash = "";
		boolean success = false;
		String response = "NoResponse";
		
		for (Course course : courses) {
			for (ICourse icourse : course.getCourses()) {
				for (ICourse newICourse : newCourse.getCourses()) {
					if (icourse.getSchedule().scheduleClash(newICourse.getSchedule())) {
						if (icourse.getClassroom() == newICourse.getClassroom()) {
							classRoomClash = classRoomClash + "\n" + course.getInitials();
						}
						if (icourse instanceof IProfessors && newICourse instanceof IProfessors) {
							IProfessors iprofessors = (IProfessors) icourse;
							IProfessors newIProfessors = (IProfessors) newICourse;
							for (Professor professor : newIProfessors.getProfessors()) {
								if (iprofessors.getProfessors().contains(professor)) {
									professorClash = professorClash + "\n" + course.getInitials() + " " + professor.getName() + " " + professor.getLastnameFather() + " " + professor.getLastnameMother();
								}
							}
						}
					}
				}
			}
		}
		if (classRoomClash != "" && professorClash != "") {
			response = Messages.getMessage(Message.COURSE_WASNT_CREATED_PROFESSOR_AND_CLASSROOM_CLASH.index());
		} else if (classRoomClash != "") {
			response = Messages.getMessage(Message.COURSE_WASNT_CREATED_CLASSROOM_CLASH.index());
		} else if (professorClash != "") {
			response = Messages.getMessage(Message.COURSE_WASNT_CREATED_PROFESSOR_CLASH.index());
		} else {
			success = true;
			response = Messages.getMessage(Message.COURSE_WAS_CREATED.index());
		}
		return new CourseManager().new CreateCourseResponse(success, response);
	}
	
	/**
	 * Class used as a data container for answering create course call.
	 */
	public class CreateCourseResponse {
		public boolean success;
		public String response;
		
		/**
		 * Creates a new instance of CreateCourseResponse.
		 * @param success Whether the course can be created or no.
		 * @param response A String containing a User friendly response specifying the result of the createCourse call.
		 */
		public CreateCourseResponse(boolean success, String response) {
			this.success = success;
			this.response = response;
		}
	}
}
