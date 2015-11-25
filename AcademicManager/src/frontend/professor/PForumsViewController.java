package frontend.professor;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Course;
import backend.courses.ForumComment;
import backend.courses.ForumPost;
import backend.manager.Manager;
import backend.users.Professor;
import frontend.main.MViewController;
import frontend.others.Parser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * When creating a new ViewController this file allows to reduce the amount of
 * writing.
 */
public class PForumsViewController extends MViewController {

	@FXML
	ComboBox<String> cmBxCourses;
	@FXML
	ComboBox<String> cmBxForumEntry;
	@FXML
	TextArea txAForumEntryComments;
	@FXML
	TextField txFForumComment;
	@FXML
	Button btnPostComment;
	@FXML
	TextField txFNewForumEntry;
	@FXML
	Button btnPostNewForumEntry;
	
	Professor user = (Professor) Manager.INSTANCE.currentUser;
	public static URL view = Object.class.getResource("/frontend/professor/PForumsView.fxml");
	ArrayList<Course> coursesToShow;
	Course currentCourse;
	ForumPost currentPost;
	
	@Override
	public void setUp() {
		super.setUp();
		
		coursesToShow = myCourses();
		cmBxCourses.setItems(Parser.generateParsedCourses(coursesToShow));
		
		cmBxCourses.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue != null) {
					currentCourse = Parser.getCourseForParsed(newValue, coursesToShow);
					showForumPosts(currentCourse);
				}
			}
		});
		cmBxForumEntry.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue != null) {
					currentPost = Parser.getForumPostForParsed(newValue, currentCourse.getForum().posts);
					showForumPostsComments(currentPost);
				}
			}
		});
	}
	
	public void showForumPosts(Course course) {
		cmBxForumEntry.setItems(Parser.generateParsedForumPosts(course.getForum().posts));
	}
	
	public void showForumPostsComments(ForumPost post) {
		String comments = "";
		for (ForumComment comment : post.comments) {
			comments += (Parser.getParsedForumComment(comment)) + "\n\n";
		}
		txAForumEntryComments.setText(comments);
	}
	
	public void btnPostComment_Pressed() {
		ForumComment comment = new ForumComment(user, txFForumComment.getText());
		currentPost.comments.add(comment);
		showForumPostsComments(currentPost);
		clearText();
		
	}
	
	public void btnPostNewForumEntry_Pressed() {
		ForumPost post = new ForumPost(user, txFNewForumEntry.getText());
		currentCourse.getForum().addPost(post);
		showForumPosts(currentCourse);
		clearText();
	}
	
	public void clearText() {
		txFForumComment.setText("");
		txFNewForumEntry.setText("");
	}
	
	ArrayList<Course> myCourses() {
		ArrayList<Course> myCourses = new ArrayList<Course>();
		for (Course course : Manager.INSTANCE.courses) {
			if (course.getLecture() != null && course.getLecture().getProfessors().contains(user)) {
				myCourses.add(course);
			} else if (course.getLaboratory() != null && course.getLaboratory().getProfessors().contains(user)) {
				myCourses.add(course);
			}
		}
		return myCourses;
	}
}
