package frontend.main;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Course;
import backend.enums.AcademicSemester;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import frontend.others.ViewUtilities;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class MCourseSearcherSelectorViewController extends MViewController {

	@FXML
	Label labelSearchCourse;
	@FXML
	Button btnDetails;
	@FXML
	protected ComboBox<String> chBxSelectedCourse;

	boolean firstLoad = true;
	protected ArrayList<Course> coursesToShow = Manager.INSTANCE.courses;
	public static URL view = Object.class.getResource("/frontend/main/MCourseSearcherSelectorView.fxml");

	@Override
	public void setUp() {
		super.setUp();

		//btnDetails.setCursor(Cursor.HAND);
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
			if (course.getSemester() == semester || course.getSemester() == AcademicSemester.BOTH
					|| semester == AcademicSemester.BOTH) {
				coursesStrings.add(getParsedCourse(course.getInitials(), course.getSection(), course.getName()));
			}
		}
		chBxSelectedCourse.setItems(FXCollections.observableArrayList(coursesStrings));
		ViewUtilities.autoComplete(chBxSelectedCourse);
	}

	public void btnDetails_Pressed() {
		if (!chBxSelectedCourse.getSelectionModel().isEmpty()) {
			String rawCourseInfo = chBxSelectedCourse.getSelectionModel().getSelectedItem();
			String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
			String initials = parsed[0];
			int section = Integer.valueOf(parsed[1]);
			String name = parsed[2];
			for (Course course : coursesToShow) {
				if (course.getInitials().equals(initials) && course.getSection() == section
						&& course.getName().equals(name)) {
					Manager.INSTANCE.courseToShowInfo = course;
					ViewUtilities.openNewView(MCourseDetailsViewController.view);
					return;
				}
			}
		} else {
			ViewUtilities.showAlert(Messages.ERROR_MESSAGE);
		}
		
	}

	public void hideCourseSearcher() {
		labelSearchCourse.setVisible(false);
	}

	public void hideCourseSelector() {
		btnDetails.setVisible(false);
		chBxSelectedCourse.setVisible(false);
	}

	public void showCourseSearcher() {
		labelSearchCourse.setVisible(true);
	}

	public void showCourseSelector() {
		btnDetails.setVisible(true);
		chBxSelectedCourse.setVisible(true);
	}
}
