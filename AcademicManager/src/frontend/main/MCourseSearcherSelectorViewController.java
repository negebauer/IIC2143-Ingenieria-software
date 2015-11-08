package frontend.main;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Course;
import backend.enums.AcademicSemester;
import backend.manager.CourseSearcher;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import frontend.others.ViewUtilities;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MCourseSearcherSelectorViewController extends MViewController {

	@FXML
	Label labelSearchCourse;
	@FXML
	Label labelSelectCoure;
	@FXML
	Button btnSearchCourse;
	@FXML
	Button btnDetails;
	@FXML
	TextField txBxCourseToSearch;
	@FXML
	protected ChoiceBox<String> chBxSelectedCourse;
	
	Boolean firstLoad = true;
	protected ArrayList<Course> coursesToShow = Manager.INSTANCE.courses;
	public static URL view = Object.class.getResource("/frontend/main/MCourseSearcherSelectorView.fxml");
	
	@Override
	public void setUp() {
		super.setUp();
		
		labelSearchCourse.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_SEARCH_COURSE));
		if (firstLoad) {
			updateCoursesShow();
			firstLoad = false;
		}
		
	}
	
	public void updateCoursesShow() {
		updateCoursesShow(AcademicSemester.BOTH);
	}
	
	public void updateCoursesShow(AcademicSemester semester) {
		ArrayList<String> coursesStrings = new ArrayList<String>();
		for (Course course : coursesToShow) {
			if (course.getSemester() == semester || course.getSemester() == AcademicSemester.BOTH || semester == AcademicSemester.BOTH) {
				coursesStrings.add(getParsedCourse(course.getInitials(), course.getSection(), course.getName()));
			}
		}
		chBxSelectedCourse.setItems(FXCollections.observableArrayList(coursesStrings));
	}
	
	public void btnSearchCourse_Pressed() {
		String searchParam = txBxCourseToSearch.getText();
		coursesToShow = CourseSearcher.searchCourses(searchParam);
		updateCoursesShow();
	}
	
	public void btnDetails_Pressed() {
		String rawCourseInfo = chBxSelectedCourse.getSelectionModel().getSelectedItem();
		String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
		String initials = parsed[0];
		int section = Integer.valueOf(parsed[1]);
		String name = parsed[2];
		for (Course course : coursesToShow) {
			if (course.getInitials().equals(initials) && course.getSection() == section && course.getName().equals(name)) {
				Manager.INSTANCE.courseDetailsToShow = course.getDetails();
				ViewUtilities.openNewView(MCourseDetailsViewController.view);
				return;
			}
		}
	}
		
	public void hideCourseSearcher() {
		labelSearchCourse.setVisible(false);
		
	}
	
	public void hideCourseSelector() {
		labelSelectCoure.setVisible(false);
		btnSearchCourse.setVisible(false);
		txBxCourseToSearch.setVisible(false);
		btnDetails.setVisible(false);
		chBxSelectedCourse.setVisible(false);
		
	}
	
	public void showCourseSearcher() {
		labelSearchCourse.setVisible(true);
		
	}
	
	public void showCourseSelector() {
		labelSelectCoure.setVisible(true);
		btnSearchCourse.setVisible(true);
		txBxCourseToSearch.setVisible(true);
		btnDetails.setVisible(true);
		chBxSelectedCourse.setVisible(true);
		
	}
	
}
