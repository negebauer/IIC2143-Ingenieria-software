package frontend.admin;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Semester;
import backend.courses.StudyProgram;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import frontend.main.MViewController;
import frontend.others.ViewUtilities;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class ASemesterManagerMainViewController extends MViewController {

	@FXML
	Label labelSemesterEditorWelcomeMessage;
	@FXML
	Button btnCreateSemester;
	@FXML
	Button btnEditSemester;
	@FXML
	ChoiceBox<String> chBxSemesters;
	@FXML
	Button btnRemoveLastSemester;

	public static URL view = Object.class.getResource("/frontend/admin/ASemesterManagerMainView.fxml");

	@Override
	public void setUp() {
		super.setUp();

		labelSemesterEditorWelcomeMessage.setText(Messages.getUILabel(UILabel.SEMESTER_MANGER_WELCOME_MESSAGE));
		btnCreateSemester.setText(Messages.getUILabel(UILabel.CREATE_SEMESTER));
		btnEditSemester.setText(Messages.getUILabel(UILabel.EDIT_SEMESTER));
		btnRemoveLastSemester.setText(Messages.getUILabel(UILabel.REMOVE_LAST_SEMESTER));

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
			
			if (Manager.INSTANCE.currentSemester != null) {
				String i = currentProgram.getSemesters().indexOf(Manager.INSTANCE.currentSemester) + 1 + "";
				chBxSemesters.getSelectionModel().select(i);
			}
		}
	}

	public void btnCreateSemester_Pressed() {
		Manager.INSTANCE.currentSemester = null;
		ViewUtilities.openView(ASemesterManagerEditingViewController.view, view);
	}

	public void btnRemoveLastSemester_Pressed() {
		int indexChoosed = Manager.INSTANCE.currentEditingStudyProgram.getSemesters().size() - 1;
		Semester semester = Manager.INSTANCE.currentEditingStudyProgram.getSemesters().get(indexChoosed);
		if (semester.getCourses().size() == 0) {
			Manager.INSTANCE.currentEditingStudyProgram.getSemesters().remove(semester);
			int size = Manager.INSTANCE.currentEditingStudyProgram.getSemesters().size();
			if (size > 0) {
				ArrayList<String> semesters = new ArrayList<String>();
				for (int i = 1; i <= size; i++) {
					semesters.add(i + "");
				}
				chBxSemesters.setItems(FXCollections.observableArrayList(semesters));
			}
		} else {
			 ViewUtilities.showAlert(Messages.getUILabel(UILabel.ERROR_REMOVING_SEMESTER));
		}
	}

	public void btnEditSemester_Pressed() {
		if (!chBxSemesters.getSelectionModel().isEmpty()) {
			int indexChoosed = Integer.parseInt(chBxSemesters.getSelectionModel().getSelectedItem()) - 1;
			Manager.INSTANCE.currentSemester = Manager.INSTANCE.currentEditingStudyProgram.getSemesters()
					.get(indexChoosed);
			ViewUtilities.openView(ASemesterManagerEditingViewController.view, view);
		} else {
			 ViewUtilities.showAlert(Messages.getUILabel(UILabel.ERROR_SELECTION)
			 + "(" + Messages.getUILabel(UILabel.EDIT_SEMESTER) + ")");
		}
	}
}
