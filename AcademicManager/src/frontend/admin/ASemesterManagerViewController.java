package frontend.admin;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Course;
import backend.courses.Semester;
import backend.courses.StudyProgram;
import backend.courses.Semester.AddOrRemoveCourseResponse;
import backend.enums.AcademicSemester;
import backend.manager.Manager;
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
	ChoiceBox<String> chBxSemesterType;
	@FXML
	Label labelSelectSemester;
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
		
		//TODO set messages for all the buttons and labels. 
		
		btnAddCourse.setVisible(false);
		btnRemoveCourse.setVisible(false);
		btnAddSemester.setVisible(false);
		listCoursesInSemester.setVisible(false);
		chBxSemesterType.setVisible(false);
		labelSelectSemester.setVisible(false);
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
		changeToEditionMode();
		btnSaveSemester.setVisible(true);
		int indexChoosed = Integer.parseInt(chBxSemesters.getSelectionModel().getSelectedItem()) - 1;
		currentEditingSemester = Manager.INSTANCE.currentEditingStudyProgram.getSemesters().get(indexChoosed);
		
		ArrayList<String> semesterCourses = new ArrayList<String>();
		for (Course course : currentEditingSemester.getCourses()) {
			semesterCourses.add(course.getInitials());
		}
		listCoursesInSemester.setItems(FXCollections.observableArrayList(semesterCourses));
		
		chBxSemesterType.getSelectionModel().select(currentEditingSemester.getSemester().toString());
		
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
		String semesterSelected = chBxSemesterType.getSelectionModel().getSelectedItem();
		currentEditingSemester.setAcademicSemester(AcademicSemester.valueOf(semesterSelected));
		Manager.INSTANCE.currentEditingStudyProgram.addSemester(currentEditingSemester);
		ViewUtilities.openView(view, view);
	}

	public void btnSaveSemester_Pressed() {
		String semesterSelected = chBxSemesterType.getSelectionModel().getSelectedItem();
		currentEditingSemester.setAcademicSemester(AcademicSemester.valueOf(semesterSelected));
		ViewUtilities.openView(view, view);
	}
	
	public void changeToEditionMode() {
		chBxSemesters.setVisible(false);
		btnCreateSemester.setVisible(false);
		btnEditSemester.setVisible(false);
		btnRemoveCourse.setVisible(true);
		btnAddCourse.setVisible(true);
		chBxSemesterType.setVisible(true);
		labelSelectSemester.setVisible(true);
		listCoursesInSemester.setVisible(true);
		labelSemesterEditorWelcomeMessage.setVisible(false);
		
		
		super.showCourseSearcher();
		super.showCourseSelector();
		
		ArrayList<String> academicSemesters = new ArrayList<String>();
		for (AcademicSemester academicSemester : AcademicSemester.values()) {
			academicSemesters.add(academicSemester.toString());
		}
		
		chBxSemesterType.setItems(FXCollections.observableArrayList(academicSemesters));
	}
	
	
}
