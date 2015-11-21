package frontend.student;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Course;
import backend.courses.Coursed;
import backend.courses.Semester;
import backend.courses.Semester.AddOrRemoveCourseResponse;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import backend.users.Student;
import frontend.main.MCourseSearcherSelectorViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/* TODO Connect combobox
 * TODO Connect courses list
 * TODO Implement all buttons
 * TODO Button and combobox to show a course details
 */
public class SCurrentSemesterViewController extends MCourseSearcherSelectorViewController {

	@FXML
	Label labelMainMessage;
	@FXML
	Label labelCurrentSemester;
	@FXML
	Label labelCurrentCourses;
	@FXML
	Label labelModification;
	@FXML
	Label labelModificationResult;
	@FXML
	Label labelCurrentCoursesNames;
	@FXML
	Button btnCreateNewSemester;
	@FXML
	ListView<String> listSelectedCourses;
	@FXML
	Button btnAddCourse;
	@FXML
	Button btnRemoveCourse;
	@FXML
	Button btnEditSemester;

	String responseToAddOrRemoveCourse = "";
	Boolean firstLoad = true;
	Student user = (Student) Manager.INSTANCE.currentUser;
	public static URL view = Object.class.getResource("/frontend/student/SCurrentSemesterView.fxml");

	@Override
	public void setUp() {
		super.setUp();

		labelMainMessage.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_EDITOR_MAIN));
		labelCurrentCourses.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_CURRENT_COURSES));
		btnCreateNewSemester.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_CREATE_NEW));
		labelModification.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_MODIFICATION_MAIN));
		btnCreateNewSemester.setCursor(Cursor.HAND);
		btnAddCourse.setCursor(Cursor.HAND);
		btnRemoveCourse.setCursor(Cursor.HAND);
		btnEditSemester.setCursor(Cursor.HAND);

		if (responseToAddOrRemoveCourse == "") {
			labelModificationResult.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_MODIFICATION_SUCCESS)
					+ "\n" + responseToAddOrRemoveCourse);
		} else {
			labelModificationResult.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_MODIFICATION_FAILED)
					+ "\n" + responseToAddOrRemoveCourse);
		}

		Semester currentSemester = user.getCurriculum().getCurrentSemester();
		if (currentSemester == null || currentSemester.getCourses().size() == 0) {
			labelCurrentSemester.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_NO_CURRENT_SEMESTER));
		} else {
			String semesterInfo = currentSemester.getYear() + "-" + currentSemester.getSemester().getSemesterNumber();
			labelCurrentSemester.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_INFO) + semesterInfo);
		}

		if (firstLoad) {
			showInfoOfSemesterOrEditor();
			firstLoad = false;
		}
	}

	public void showInfoOfSemesterOrEditor() {
		Semester currentSemester = user.getCurriculum().getCurrentSemester();
		if (currentSemester == null || currentSemester.getCourses().size() == 0) {
			user.getCurriculum()
					.setCurrentSemester(new Semester(Manager.INSTANCE.currentSemester.getSemester(), Manager.getYear(),
							user.getCurriculum().getMaxSemesterCredits(), user.getCurriculum().getCoursedCourses(),
							null));
			labelCurrentSemester.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_NO_CURRENT_SEMESTER));
			hideSemesterInfo();
			showSemesterCreation();
		} else {
			String semesterInfo = currentSemester.getYear() + "-" + currentSemester.getSemester().getSemesterNumber();
			labelCurrentSemester.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_INFO) + semesterInfo);
			String names = "";
			for (Course course : currentSemester.getCourses()) {
				String courseName = course.getInitials() + "-" + course.getSection() + ": " + course.getName();
				names = names == "" ? courseName : names + courseName;
				names += "\n";
			}
			labelCurrentCoursesNames.setText(names);
			showSemesterInfo();
			hideSemesterCreation();
		}
	}

	public void btnCreateNewSemester_Pressed() {
		showInfoOfSemesterOrEditor();
	}

	private void hideSemesterInfo() {
		labelCurrentCourses.setVisible(false);
		labelCurrentCoursesNames.setVisible(false);
		btnEditSemester.setVisible(false);
	}

	private void hideSemesterCreation() {
		hideCourseSearcher();
		hideCourseSelector();
		labelModification.setVisible(false);
		labelModificationResult.setVisible(false);
		btnCreateNewSemester.setVisible(false);
		listSelectedCourses.setVisible(false);
		btnAddCourse.setVisible(false);
		btnRemoveCourse.setVisible(false);
	}

	private void showSemesterInfo() {
		labelCurrentCourses.setVisible(true);
		labelCurrentCoursesNames.setVisible(true);
		btnEditSemester.setVisible(true);
	}

	private void showSemesterCreation() {
		showCourseSearcher();
		showCourseSelector();
		labelModification.setVisible(true);
		labelModificationResult.setVisible(true);
		btnCreateNewSemester.setVisible(true);
		listSelectedCourses.setVisible(true);
		btnAddCourse.setVisible(true);
		btnRemoveCourse.setVisible(true);
	}

	public void btnAddCourse_Pressed() {
		String rawCourseInfo = chBxSelectedCourse.getSelectionModel().getSelectedItem();
		String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
		String initials = parsed[0];
		int section = Integer.valueOf(parsed[1]);
		String name = parsed[2];
		for (Course course : coursesToShow) {
			if (course.getInitials().equals(initials) && course.getSection() == section
					&& course.getName().equals(name)) {
				AddOrRemoveCourseResponse response = user.getCurriculum().getCurrentSemester().addCourse(course);
				if (response.success) {
					ObservableList<String> currentCourses = listSelectedCourses.getItems();
					currentCourses.add(getParsedCourse(initials, section, name));
					listSelectedCourses.setItems(FXCollections.observableArrayList(currentCourses));
					responseToAddOrRemoveCourse = "";
					labelModificationResult
							.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_MODIFICATION_SUCCESS) + "\n"
									+ responseToAddOrRemoveCourse);
				} else {
					responseToAddOrRemoveCourse = response.response;
					labelModificationResult
							.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_MODIFICATION_FAILED) + "\n"
									+ responseToAddOrRemoveCourse);
				}
			}
		}
	}

	public void btnRemoveCourse_Pressed() {
		String rawCourseInfo = chBxSelectedCourse.getSelectionModel().getSelectedItem();
		String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
		String initials = parsed[0];
		int section = Integer.valueOf(parsed[1]);
		String name = parsed[2];
		for (Course course : coursesToShow) {
			if (course.getInitials().equals(initials) && course.getSection() == section
					&& course.getName().equals(name)) {
				AddOrRemoveCourseResponse response = user.getCurriculum().getCurrentSemester().removeCourse(course);
				if (response.success) {
					ObservableList<String> currentCourses = listSelectedCourses.getItems();
					currentCourses.remove(getParsedCourse(initials, section, name));
					listSelectedCourses.setItems(FXCollections.observableArrayList(currentCourses));
					responseToAddOrRemoveCourse = "";
					labelModificationResult
							.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_MODIFICATION_SUCCESS) + "\n"
									+ responseToAddOrRemoveCourse);
				} else {
					responseToAddOrRemoveCourse = response.response;
					labelModificationResult
							.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_MODIFICATION_FAILED) + "\n"
									+ responseToAddOrRemoveCourse);
				}
			}
		}
	}

	public void btnEditSemester_Pressed() {
		ArrayList<Course> courses = user.getCurriculum().getCurrentSemester().getCourses();
		user.getCurriculum().setCurrentSemester(null);
		showInfoOfSemesterOrEditor();
		user.getCurriculum().getCurrentSemester().setCourses(courses);
		ArrayList<String> currentCourses = new ArrayList<String>();
		for (Course course : courses) {
			String parsedCourse = getParsedCourse(course.getInitials(), course.getSection(), course.getName());
			currentCourses.add(parsedCourse);
		}
		listSelectedCourses.setItems(FXCollections.observableArrayList(currentCourses));
	}
}
