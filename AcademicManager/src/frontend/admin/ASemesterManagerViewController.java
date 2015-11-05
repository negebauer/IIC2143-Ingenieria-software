package frontend.admin;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Course;
import backend.courses.Coursed;
import backend.courses.CoursedSemester;
import backend.courses.Semester;
import backend.courses.StudyProgram;
import backend.enums.AcademicSemester;
import backend.manager.Manager;
import backend.users.*;
import frontend.main.MCourseSearcherSelectorViewController;
import frontend.others.ViewUtilities;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
	
	public static URL view = Object.class.getResource("/frontend/admin/ASemesterManagerView.fxml");
		
	
	public void setUp() {
		super.setUp();
		super.hideCourseSelector();
		super.hideCourseSearcher();

		btnAddCourse.setVisible(false);
		btnRemoveCourse.setVisible(false);
		btnAddSemester.setVisible(false);
		listCoursesInSemester.setVisible(false);
		chBxSemesterType.setVisible(false);
		labelSelectSemester.setVisible(false);
		btnSaveSemester.setVisible(false);
		
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
	}

	public void btnBackToStudyProgramManager_Pressed() {
		ViewUtilities.openView(AStudyProgramManagerViewController.view, view);
	}

	public void btnEditSemester_Pressed() {
		changeToEditionMode();
		btnSaveSemester.setVisible(true);
		int indexChoosed = Integer.parseInt(chBxSemesters.getSelectionModel().getSelectedItem());
		Semester semesterSelected = Manager.INSTANCE.currentEditingStudyProgram.getSemesters().get(indexChoosed);
		
		ArrayList<String> semesterCourses = new ArrayList<String>();
		for (Course course : semesterSelected.getCourses()) {
			semesterCourses.add(course.getInitials());
		}
		listCoursesInSemester.setItems(FXCollections.observableArrayList(semesterCourses));
		
		chBxSemesterType.getSelectionModel().select(semesterSelected.getSemester().toString());
		
	}

	public void btnAddCourse_Pressed() {
		
		
	}

	public void btnRemoveCourse_Pressed() {
		
		
	}

	public void btnAddSemester_Pressed() {
		// create new semester with the data of the fields
		
		ViewUtilities.openView(view, view);
	}

	public void btnSaveSemester_Pressed() {
		// set data from the fields to the semester selected
		
		
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
		
		
		super.showCourseSearcher();
		super.showCourseSelector();
		
		ArrayList<String> academicSemesters = new ArrayList<String>();
		for (AcademicSemester academicSemester : AcademicSemester.values()) {
			academicSemesters.add(academicSemester.toString());
		}
		
		chBxSemesterType.setItems(FXCollections.observableArrayList(academicSemesters));
	}
	
	
}
