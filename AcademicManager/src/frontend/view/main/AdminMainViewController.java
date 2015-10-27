package frontend.view.main;

import backend.others.Messages;
import backend.others.Messages.UILabel;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AdminMainViewController implements IController {
	
	@FXML
	Label labelWelcomeMessage;
	@FXML
	Label labelWhatDoNext;
	@FXML
	Button btnCreateNewStudyProgram;
	@FXML
	Button btnEditStudyProgram;
	@FXML
	Button btnLogOut;
	
	public void setUp() {
		labelWelcomeMessage.setText(Messages.getUILabel(UILabel.WELCOME_MESSAGE_ADMIN));
		labelWhatDoNext.setText(Messages.getUILabel(UILabel.WHAT_TO_DO_NEXT_QUESTION));
		btnCreateNewStudyProgram.setText(Messages.getUILabel(UILabel.CREATE_NEW_STUDY_PROGRAM));
		btnEditStudyProgram.setText(Messages.getUILabel(UILabel.EDIT_STUDY_PROGRAM));
		btnLogOut.setText(Messages.getUILabel(UILabel.LOGOUT));
	}
	
	public void btnCreateNewStudyProgram_Pressed() {
		
	}
	
	public void btnEditStudyProgram_Pressed() {
		
	}
	
	public void btnLogOut_Pressed() {
		
	}
}
