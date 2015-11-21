package frontend.others;

import java.util.ArrayList;

import backend.courses.Course;
import backend.users.Student;

public class Parser {

	static public Course getCourseForParsed(String parsed, ArrayList<Course> coursesToShow) {
		String initials = parsed.split("-")[0];
		int section = Integer.valueOf(parsed.split("-")[1].split(":")[0]);
		for (Course course : coursesToShow) {
			if (course.getInitials().equals(initials) && course.getSection() == section) {
				return course;
			}
		}
		return null;
	}
	
	static public String getParsedCourse(Course course) {
		return course.getInitials() + "-"
				+ course.getSection() + ": "
				+ course.getName();
	}
	
	static public Student getStudentForParsed(String parsed, ArrayList<Student> studentsToShow) {
		String rut = parsed.split(" - ")[0];
		for (Student student : studentsToShow) {
			if (student.getRut().equals(rut)) {
				return student;
			}
		}
		return null;
	}
	
	static public String getParsedStudent(Student student) {
		return student.getRut() + " - "
				+ student.getName() + " "
				+ student.getLastnameFather() + " "
				+ student.getLastnameMother() + " ";
	}
}
