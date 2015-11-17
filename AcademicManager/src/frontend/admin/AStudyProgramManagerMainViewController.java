package frontend.admin;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.StudyProgram;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import frontend.main.MViewController;
import frontend.others.ViewUtilities;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class AStudyProgramManagerMainViewController extends MViewController {
	
	@FXML
	Label labelEditStudyProgram;
	@FXML
	Label labelCreateStudyProgram;
	@FXML
	Button btnCreateStudyProgram;
	@FXML
	Button btnEditStudyProgram;
	@FXML
	ComboBox<String> chBxStudyProgramsList;
	
	
	public static URL view = Object.class.getResource("/frontend/admin/AStudyProgramManagerMainView.fxml");
	
	public void setUp() {
		super.setUp();
		
		ViewUtilities.autoComplete(chBxStudyProgramsList);
		
		labelCreateStudyProgram.setText(Messages.getUILabel(UILabel.STUDY_PROGRAM_CREATE_LABEL));
		labelEditStudyProgram.setText(Messages.getUILabel(UILabel.STUDY_PROGRAM_EDIT_LABEL));
		btnCreateStudyProgram.setText(Messages.getUILabel(UILabel.CREATE_NEW_STUDY_PROGRAM));
		btnEditStudyProgram.setText(Messages.getUILabel(UILabel.EDIT_STUDY_PROGRAM));

		ArrayList<String> studyProgramNames = new ArrayList<String>();
			
		for (StudyProgram studyProgram : Manager.INSTANCE.studyPrograms) {
			studyProgramNames.add(studyProgram.getName());
		}
			
		chBxStudyProgramsList.setItems(FXCollections.observableArrayList(studyProgramNames));
		
		if (Manager.INSTANCE.currentEditingStudyProgram != null) {
			chBxStudyProgramsList.getSelectionModel().select(Manager.INSTANCE.currentEditingStudyProgram.getName());
		}
		
	}

	public void btnCreateStudyProgram_Pressed() {
		Manager.INSTANCE.currentEditingStudyProgram = null;
		ViewUtilities.openView(AStudyProgramManagerEditingViewController.view, view);
	}
	
	public void btnEditStudyProgram_Pressed() {
		if (!chBxStudyProgramsList.getSelectionModel().isEmpty()) {
			String selected = chBxStudyProgramsList.getSelectionModel().getSelectedItem();
			Manager.INSTANCE.currentEditingStudyProgram = Manager.INSTANCE.getStudyProgramForName(selected);
			ViewUtilities.openView(AStudyProgramManagerEditingViewController.view, view);
		}
		
	}
	
}
