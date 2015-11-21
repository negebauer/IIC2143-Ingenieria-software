package frontend.admin;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Course;
import backend.courses.Coursed;
import backend.courses.Semester;
import backend.courses.Semester.AddOrRemoveCourseResponse;
import backend.enums.AcademicSemester;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import frontend.main.MCourseSearcherSelectorViewController;
import frontend.others.ViewUtilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ASemesterManagerEditingViewController extends MCourseSearcherSelectorViewController {

	@FXML
	Button btnAddCourse;
	@FXML
	Button btnRemoveCourse;
	@FXML
	ListView<String> listCoursesInSemester;
	@FXML
	Button btnSaveSemester;
	@FXML
	Label labelModificationResult;

	public static URL view = Object.class.getResource("/frontend/admin/ASemesterManagerEditingView.fxml");
	public boolean isCreating = false;

	@Override
	public void setUp() {
		super.setUp();

		btnAddCourse.setText(Messages.getUILabel(UILabel.ADD_COURSE));
		btnRemoveCourse.setText(Messages.getUILabel(UILabel.REMOVE_COURSE));
		btnSaveSemester.setText(Messages.getUILabel(UILabel.SAVE_SEMESTER));
		labelModificationResult.setText("");

		if (Manager.INSTANCE.currentSemester != null) {
			isCreating = false;
			ArrayList<String> semesterCourses = new ArrayList<String>();
			for (Course course : Manager.INSTANCE.currentSemester.getCourses()) {
				semesterCourses.add(getParsedCourse(course.getInitials(), course.getSection(), course.getName()));
			}
			listCoursesInSemester.setItems(FXCollections.observableArrayList(semesterCourses));
		} else {
			isCreating = true;
			ArrayList<Coursed> passedCourses = new ArrayList<Coursed>();
			for (Semester semester : Manager.INSTANCE.currentEditingStudyProgram.getSemesters()) {
				for (Course course : semester.getCourses()) {
					passedCourses.add(new Coursed(course, true, 7, AcademicSemester.defaultSemester(), 0));
				}
			}
			Manager.INSTANCE.currentSemester = new Semester(AcademicSemester.defaultSemester(), 0,
					Manager.INSTANCE.currentEditingStudyProgram.getMaxCreditsPerSemester(), passedCourses,
					new ArrayList<Course>());
		}
	}

	public void btnAddCourse_Pressed() {
		if (!chBxSelectedCourse.getSelectionModel().isEmpty()
				& chBxSelectedCourse.getItems().contains(chBxSelectedCourse.getSelectionModel().getSelectedItem())) {
			String rawCourseInfo = chBxSelectedCourse.getSelectionModel().getSelectedItem();
			String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
			String initials = parsed[0];
			int section = Integer.valueOf(parsed[1]);
			String name = parsed[2];
			for (Course course : coursesToShow) {
				if (course.getInitials().equals(initials) && course.getSection() == section
						&& course.getName().equals(name)) {
					AddOrRemoveCourseResponse response = Manager.INSTANCE.currentSemester.addCourse(course);
					if (response.success) {
						ObservableList<String> currentCourses = listCoursesInSemester.getItems();
						currentCourses.add(getParsedCourse(initials, section, name));
						listCoursesInSemester.setItems(FXCollections.observableArrayList(currentCourses));
						labelModificationResult.setText("Success");
					} else {
						labelModificationResult.setText("Not added: " + response.response);
					}
					break;
				}
			}
		} else {
			ViewUtilities.showAlert(Messages.getUILabel(UILabel.ERROR_SELECTION) + "(" + Messages.getUILabel(UILabel.ADD_COURSE) + ")");
		}

	}

	public void btnRemoveCourse_Pressed() {
		if (!listCoursesInSemester.getSelectionModel().isEmpty()) {
			String rawCourseInfo = listCoursesInSemester.getSelectionModel().getSelectedItem();
			String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
			String initials = parsed[0];
			int section = Integer.valueOf(parsed[1]);
			String name = parsed[2];
			for (Course course : coursesToShow) {
				if (course.getInitials().equals(initials) && course.getSection() == section
						&& course.getName().equals(name)) {
					AddOrRemoveCourseResponse response = Manager.INSTANCE.currentSemester.removeCourse(course);
					if (response.success) {
						ObservableList<String> currentCourses = listCoursesInSemester.getItems();
						currentCourses.remove(getParsedCourse(initials, section, name));
						listCoursesInSemester.setItems(FXCollections.observableArrayList(currentCourses));
						labelModificationResult.setText("Success");
					} else {
						labelModificationResult.setText("Not removed: " + response.response);
					}
					break;
				}
			}
		} else {
			ViewUtilities.showAlert(Messages.getUILabel(UILabel.ERROR_SELECTION) + "(" + Messages.getUILabel(UILabel.REMOVE_COURSE) + ")");
		}

	}

	public void btnSaveSemester_Pressed() {
		Semester currentSemester = Manager.INSTANCE.currentSemester;
		ArrayList<Course> courses = new ArrayList<Course>();

		for (String courseString : listCoursesInSemester.getItems()) {
			for (Course course : Manager.INSTANCE.courses) {
				if (course.getInitials() + "-" + course.getSection() + " " + course.getName() == courseString) {
					courses.add(course);
				}
				break;
			}
		}

		currentSemester.setCourses(courses);

		if (isCreating) {
			Manager.INSTANCE.currentEditingStudyProgram.addSemester(currentSemester);
			isCreating = false;
		}

		Manager.INSTANCE.currentSemester = null;
		super.btnBack_Pressed();
	}

}
