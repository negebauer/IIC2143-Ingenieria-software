package frontend.others;

import java.util.ArrayList;
import backend.courses.Course;
import backend.courses.ForumComment;
import backend.courses.ForumPost;
import backend.others.Utilities;
import backend.users.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
	
	static public ObservableList<String> generateParsedCourses(ArrayList<Course> courses) {
		ArrayList<String> parsedCourses = new ArrayList<String>();
		for (Course course : courses) {
			parsedCourses.add(Parser.getParsedCourse(course));
		}
		return FXCollections.observableArrayList(parsedCourses);
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
	
	static public ObservableList<String> generateParsedStudents(ArrayList<Student> students) {
		ArrayList<String> parsedStudents = new ArrayList<String>();
		for (Student student : students) {
			parsedStudents.add(Parser.getParsedStudent(student));
		}
		return FXCollections.observableArrayList(parsedStudents);
	}
	
	static public ForumPost getForumPostForParsed(String parsed, ArrayList<ForumPost> forumPosts) {
		String dateString = parsed.split(" ")[0] + " " + parsed.split(" ")[1];
		String rutCreator = parsed.split(" ")[2];
		String title = parsed.split(": ")[1];
		for (ForumPost post : forumPosts) {
			if (post.creator.getRut().equals(rutCreator) && Utilities.getStringFromDate(post.createdAt).equals(dateString) && post.title.equals(title)) {
				return post;
			}
		}
		return null;
	}
	
	static public String getParsedForumPost(ForumPost forumPost) {
		return Utilities.getStringFromDate(forumPost.createdAt) + " "
				+ forumPost.creator.getRut() + " "
				+ forumPost.creator.getName() + " "
				+ forumPost.creator.getLastnameFather() + ": "
				+ forumPost.title;
	}
	
	static public ObservableList<String> generateParsedForumPosts(ArrayList<ForumPost> posts) {
		ArrayList<String> parsedForumPosts = new ArrayList<String>();
		for (ForumPost post : posts) {
			parsedForumPosts.add(Parser.getParsedForumPost(post));
		}
		return FXCollections.observableArrayList(parsedForumPosts);
	}
	
	static public String getParsedForumComment(ForumComment forumComment) {
		return Utilities.getStringFromDate(forumComment.createdAt) + " "
				+ forumComment.creator.getRut() + " "
				+ forumComment.creator.getName() + " "
				+ forumComment.creator.getLastnameFather() + ": \n\t"
				+ forumComment.comment;
	}
}
