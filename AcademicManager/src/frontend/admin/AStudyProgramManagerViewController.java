package frontend.admin;

import java.net.URL;

import backend.others.Messages;
import backend.others.Messages.UILabel;
import frontend.main.MViewController;
import frontend.others.ViewUtilities;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class AStudyProgramManagerViewController extends MViewController {
	
	@FXML
	Label labelEditStudyProgram;
	@FXML
	Label labelCreateStudyProgram;
	@FXML
	Button btnCreateStudyProgram;
	@FXML
	Button btnEditStudyProgram;
	@FXML
	ChoiceBox<String> chBxStudyProgramsList;
	
	
	public static URL view = Object.class.getResource("/frontend/main/AStudyProgramManagerView.fxml");
	
	
	public void setUp() {
		super.setUp();
		labelCreateStudyProgram.setText(Messages.getUILabel(UILabel.STUDY_PROGRAM_CREATE_LABEL));
		labelEditStudyProgram.setText(Messages.getUILabel(UILabel.STUDY_PROGRAM_EDIT_LABEL));
		btnCreateStudyProgram.setText(Messages.getUILabel(UILabel.CREATE_NEW_STUDY_PROGRAM));
		btnEditStudyProgram.setText(Messages.getUILabel(UILabel.EDIT_STUDY_PROGRAM));
	}
	
	public void btnCreateStudyProgram_Pressed() {
		ViewUtilities.openView(ASemesterManagerViewController.view, view);
		// pass a new study program to the next view
	}
	
	public void btnEditStudyProgram_Pressed() {
		ViewUtilities.openView(ASemesterManagerViewController.view, view);
		// pass the study program selected to the next view
	}
	
}
