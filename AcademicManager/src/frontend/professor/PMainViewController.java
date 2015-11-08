package frontend.professor;

import java.net.URL;

import backend.others.Messages;
import backend.others.Messages.UILabel;
import frontend.main.MViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PMainViewController extends MViewController  {

	@FXML
	Label labelWelcomeMessage;
	@FXML
	Label labelWhatDoNext;
	@FXML
	Button btnStudyProgramManager;
	@FXML
	Button btnCourseManager;
	
	static URL view = Object.class.getResource("/frontend/professor/PMainView.fxml");
	
	public void setUp() {
		super.setUp();
		labelWelcomeMessage.setText(Messages.getUILabel(UILabel.WELCOME_MESSAGE_ADMIN));
		labelWhatDoNext.setText(Messages.getUILabel(UILabel.WHAT_TO_DO_NEXT_QUESTION));
		btnStudyProgramManager.setText(Messages.getUILabel(UILabel.STUDY_PROGRAM_MANAGER));
		btnCourseManager.setText(Messages.getUILabel(UILabel.COURSE_MANAGER));
	}
}
