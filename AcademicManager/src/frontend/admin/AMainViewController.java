package frontend.admin;

import java.net.URL;

import backend.others.Messages;
import backend.others.Messages.UILabel;
import frontend.main.MViewController;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AMainViewController extends MViewController {
	
	@FXML
	Label labelWelcomeMessage;
	@FXML
	Label labelWhatDoNext;
	@FXML
	Button btnCreateNewStudyProgram;
	@FXML
	Button btnEditStudyProgram;
	
	public static URL view = Object.class.getResource("/frontend/admin/AMainView.fxml");
	
	public void setUp() {
		super.setUp();
		labelWelcomeMessage.setText(Messages.getUILabel(UILabel.WELCOME_MESSAGE_ADMIN));
		labelWhatDoNext.setText(Messages.getUILabel(UILabel.WHAT_TO_DO_NEXT_QUESTION));
		btnCreateNewStudyProgram.setText(Messages.getUILabel(UILabel.CREATE_NEW_STUDY_PROGRAM));
		btnEditStudyProgram.setText(Messages.getUILabel(UILabel.EDIT_STUDY_PROGRAM));
	}
	
	public void btnCreateNewStudyProgram_Pressed() {
		
	}
	
	public void btnEditStudyProgram_Pressed() {
		
	}

}
