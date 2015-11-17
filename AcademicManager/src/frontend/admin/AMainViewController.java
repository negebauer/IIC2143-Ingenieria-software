package frontend.admin;

import java.net.URL;

import backend.others.Messages;
import backend.others.Messages.UILabel;
import frontend.main.MViewController;
import frontend.others.ViewUtilities;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AMainViewController extends MViewController {
	
	@FXML
	Label labelWelcomeMessage;
	@FXML
	Label labelWhatDoNext;
	@FXML
	Button btnStudyProgramManager;
	@FXML
	Button btnCourseManager;
	
	public static URL view = Object.class.getResource("/frontend/admin/AMainView.fxml");
	
	public void setUp() {
		super.setUp();
		labelWelcomeMessage.setText(Messages.getUILabel(UILabel.WELCOME_MESSAGE_ADMIN));
		labelWhatDoNext.setText(Messages.getUILabel(UILabel.WHAT_TO_DO_NEXT_QUESTION));
		btnStudyProgramManager.setText(Messages.getUILabel(UILabel.STUDY_PROGRAM_MANAGER));
		btnCourseManager.setText(Messages.getUILabel(UILabel.COURSE_MANAGER));
	}
	
	public void btnStudyProgramManager_Pressed() {
		ViewUtilities.openView(AStudyProgramManagerMainViewController.view, view);
	}
	
	public void btnCourseManager_Pressed() {
		ViewUtilities.openView(ACourseManagerMainViewController.view, view);
	}

}
