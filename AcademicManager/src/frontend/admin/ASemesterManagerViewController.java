package frontend.admin;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Course;
import backend.courses.Semester;
import backend.courses.StudyProgram;
import backend.courses.Semester.AddOrRemoveCourseResponse;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import frontend.main.MCourseSearcherSelectorViewController;
import frontend.others.ViewUtilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ASemesterManagerViewController extends MCourseSearcherSelectorViewController {
	
	@FXML
	Label labelSemesterEditorWelcomeMessage;
	@FXML
	Button btnCreateSemester;
	@FXML
	Button btnEditSemester;
	@FXML
	Button btnAddCourse;
	@FXML
	Button btnRemoveCourse;
	@FXML
	Button btnAddSemester;
	@FXML
	ChoiceBox<String> chBxSemesters;
	@FXML
	ListView<String> listCoursesInSemester;
	@FXML
	Button btnSaveSemester;
	@FXML
	Button btnBackToStudyProgramManager;
	@FXML
	Label labelModificationResult;
	
	public static URL view = Object.class.getResource("/frontend/admin/ASemesterManagerView.fxml");
		
	private Semester currentEditingSemester;
	
	public void setUp() {
		super.setUp();
		super.hideCourseSelector();
		super.hideCourseSearcher();
		
		labelSemesterEditorWelcomeMessage.setText(Messages.getUILabel(UILabel.SEMESTER_MANGER_WELCOME_MESSAGE));
		btnCreateSemester.setText(Messages.getUILabel(UILabel.CREATE_SEMESTER));
		btnEditSemester.setText(Messages.getUILabel(UILabel.EDIT_SEMESTER));
		btnAddCourse.setText(Messages.getUILabel(UILabel.ADD_COURSE));
		btnRemoveCourse.setText(Messages.getUILabel(UILabel.REMOVE_COURSE));
		btnAddSemester.setText(Messages.getUILabel(UILabel.ADD_SEMESTER));
		btnSaveSemester.setText(Messages.getUILabel(UILabel.SAVE_SEMESTER));
		btnBackToStudyProgramManager.setText(Messages.getUILabel(UILabel.BACK_TO_PROGRAM_STUDY_SELECTION));
		
		btnAddCourse.setVisible(false);
		btnRemoveCourse.setVisible(false);
		btnAddSemester.setVisible(false);
		listCoursesInSemester.setVisible(false);
		btnSaveSemester.setVisible(false);
		labelModificationResult.setText("");
		
		if (Manager.INSTANCE.currentEditingStudyProgram != null) {
			StudyProgram currentProgram = Manager.INSTANCE.currentEditingStudyProgram;
			int size = currentProgram.getSemesters().size();
			if (size > 0) {
				ArrayList<String> semesters = new ArrayList<String>();
				for (int i = 1; i <= size; i++) {
					semesters.add(i + "");
				}
				chBxSemesters.setItems(FXCollections.observableArrayList(semesters));
			}
		}
		
	}
	
	public void btnCreateSemester_Pressed() {
		changeToEditionMode();
		btnAddSemester.setVisible(true);
		currentEditingSemester = new Semester(null, 0, Manager.INSTANCE.currentEditingStudyProgram.getMaxCreditsPerSemester(), null, new ArrayList<Course>());
	}

	public void btnBackToStudyProgramManager_Pressed() {
		ViewUtilities.openView(AStudyProgramManagerViewController.view, view);
	}

	public void btnEditSemester_Pressed() {
		if (!chBxSemesters.getSelectionModel().isEmpty()) {
			changeToEditionMode();
			btnSaveSemester.setVisible(true);
			int indexChoosed = Integer.parseInt(chBxSemesters.getSelectionModel().getSelectedItem()) - 1;
			currentEditingSemester = Manager.INSTANCE.currentEditingStudyProgram.getSemesters().get(indexChoosed);
		
			ArrayList<String> semesterCourses = new ArrayList<String>();
			for (Course course : currentEditingSemester.getCourses()) {
				semesterCourses.add(course.getInitials());
			}
			listCoursesInSemester.setItems(FXCollections.observableArrayList(semesterCourses));
		}
		
	}

	public void btnAddCourse_Pressed() {
		String rawCourseInfo = chBxSelectedCourse.getSelectionModel().getSelectedItem();
		String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
		String initials = parsed[0];
		int section = Integer.valueOf(parsed[1]);
		String name = parsed[2];
		for (Course course : coursesToShow) {
			if (course.getInitials().equals(initials) && course.getSection() == section && course.getName().equals(name)) {
				AddOrRemoveCourseResponse response = currentEditingSemester.addCourse(course);
				if (response.success) {
					ObservableList<String> currentCourses = listCoursesInSemester.getItems();
					currentCourses.add(getParsedCourse(initials, section, name));
					listCoursesInSemester.setItems(FXCollections.observableArrayList(currentCourses));
					labelModificationResult.setText("Success");
				} else {
					labelModificationResult.setText("Not added: " + response.response);
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
			if (course.getInitials().equals(initials) && course.getSection() == section && course.getName().equals(name)) {
				AddOrRemoveCourseResponse response = currentEditingSemester.removeCourse(course);
				if (response.success) {
					ObservableList<String> currentCourses = listCoursesInSemester.getItems();
					currentCourses.remove(getParsedCourse(initials, section, name));
					listCoursesInSemester.setItems(FXCollections.observableArrayList(currentCourses));
					labelModificationResult.setText("Success");
				} else {
					labelModificationResult.setText("Not removed: " + response.response);
				}
			}
		}
		
	}

	public void btnAddSemester_Pressed() {
		Manager.INSTANCE.currentEditingStudyProgram.addSemester(currentEditingSemester);
		ViewUtilities.openView(view, AStudyProgramManagerViewController.view);
	}

	public void btnSaveSemester_Pressed() {
		ViewUtilities.openView(view, AStudyProgramManagerViewController.view);
	}
	
	public void changeToEditionMode() {
		chBxSemesters.setVisible(false);
		btnCreateSemester.setVisible(false);
		btnEditSemester.setVisible(false);
		btnRemoveCourse.setVisible(true);
		btnAddCourse.setVisible(true);
		listCoursesInSemester.setVisible(true);
		labelSemesterEditorWelcomeMessage.setVisible(false);
		
		
		super.showCourseSearcher();
		super.showCourseSelector();		
	}
	
	
}
