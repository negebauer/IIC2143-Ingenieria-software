package frontend.main;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Course;
import backend.manager.CourseSearcher;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import frontend.others.ViewUtilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MCourseSearcherSelectorViewController extends MViewController {

	@FXML
	Label labelSearchCourse;
	@FXML
	Label labelSelectCoure;
	@FXML
	Button btnAddCourse;
	@FXML
	Button btnRemoveCourse;
	@FXML
	Button btnSearchCourse;
	@FXML
	Button btnDetails;
	@FXML
	TextField txBxCourseToSearch;
	@FXML
	ChoiceBox<String> chBxSelectedCourse;
	@FXML
	ListView<String> listSelectedCourses; 
	
	ArrayList<Course> coursesToShow = Manager.INSTANCE.courses;
	public static URL view = Object.class.getResource("/frontend/main/MCourseSearcherSelectorView.fxml");
	
	@Override
	public void setUp() {
		super.setUp();
		
		labelSearchCourse.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_SEARCH_COURSE));
		ArrayList<String> coursesStrings = new ArrayList<String>();
		for (Course course : coursesToShow) {
			coursesStrings.add(getParsedCourse(course.getInitials(), course.getSection(), course.getName()));
		}
		chBxSelectedCourse.setItems(FXCollections.observableArrayList(coursesStrings));
	}
	
	public void btnSearchCourse_Pressed() {
		String searchParam = txBxCourseToSearch.getText();
		coursesToShow = CourseSearcher.searchCourses(searchParam);
		setUp();
	}
	
	public void btnAddCourse_Pressed() {
		String rawCourseInfo = chBxSelectedCourse.getSelectionModel().getSelectedItem();
		String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
		String initials = parsed[0];
		int section = Integer.valueOf(parsed[1]);
		String name = parsed[1];
		for (Course course : coursesToShow) {
			if (course.getInitials().equals(initials) && course.getSection() == section && course.getName().equals(name)) {
				ObservableList<String> currentCourses = listSelectedCourses.getItems();
				currentCourses.add(getParsedCourse(initials, section, name));
				listSelectedCourses.setItems(FXCollections.observableArrayList(currentCourses));
			}
		}
	}
	
	public void btnRemoveCourse_Pressed() {
		String rawCourseInfo = chBxSelectedCourse.getSelectionModel().getSelectedItem();
		String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
		String initials = parsed[0];
		int section = Integer.valueOf(parsed[1]);
		String name = parsed[1];
		ObservableList<String> currentCourses = listSelectedCourses.getItems();
		for (String courseInfo : listSelectedCourses.getItems()) {
			String[] courseParsed = getParsedInitialsSectionName(courseInfo);
			String courseInitials = courseParsed[0];
			int courseSection = Integer.valueOf(courseParsed[1]);
			String courseName = courseParsed[2];
			if (initials.equals(courseInitials) && section == courseSection && name.equals(courseName)) {
				currentCourses.remove(courseInfo);
			}
		}
		listSelectedCourses.setItems(FXCollections.observableArrayList(currentCourses));
	}
	
	public void btnDetails_Pressed() {
		String rawCourseInfo = chBxSelectedCourse.getSelectionModel().getSelectedItem();
		String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
		String initials = parsed[0];
		int section = Integer.valueOf(parsed[1]);
		String name = parsed[1];
		for (Course course : coursesToShow) {
			if (course.getInitials().equals(initials) && course.getSection() == section && course.getName().equals(name)) {
				Manager.INSTANCE.courseDetailsToShow = course.getDetails();
				ViewUtilities.openNewView(MCourseDetailsViewController.view);
				return;
			}
		}
	}
	
	public String[] getParsedInitialsSectionName(String raw) {
		String[] split1 = raw.split(" - ");
		String[] split2 = split1[0].split("-");
		String initials = split2[0];
		int section = Integer.valueOf(split2[1]);
		String name = split1[1];
		String complete = initials + "&" + section + "&" + name;
		return complete.split("&");
		
	}
	
	public String getParsedCourse(String initials, int section, String name) {
		String parsed = initials + "-" + section + " - " + name;
		return parsed;
	}
	
	public void hideCourseSearcher() {
		labelSearchCourse.setVisible(false);
		
	}
	
	public void hideCourseSelector() {
		labelSelectCoure.setVisible(false);
		btnSearchCourse.setVisible(false);
		
	}
	
	public void hideSelectionCourses() {
		btnAddCourse.setVisible(false);
		btnRemoveCourse.setVisible(false);
	}
	
	public void showCourseSearcher() {
		labelSearchCourse.setVisible(true);
		
	}
	
	public void showCourseSelector() {
		labelSelectCoure.setVisible(true);
		btnAddCourse.setVisible(true);
		btnRemoveCourse.setVisible(true);
		btnSearchCourse.setVisible(true);
		
	}
	
	public void showSelectionCourses() {
		btnAddCourse.setVisible(true);
		btnRemoveCourse.setVisible(true);
	}
	
}
