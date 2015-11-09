package frontend.professor;

import java.net.URL;

import backend.others.Messages;
import backend.others.Messages.UILabel;
import frontend.main.MViewController;
import frontend.others.ViewUtilities;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PMainViewController extends MViewController  {

	@FXML
	Label labelWelcomeMessage;
	@FXML
	Label labelWhatDoNext;
	@FXML
	Button btnSeeMyCourses;
	@FXML
	Button btnQualifyStudents;
	
	public static URL view = Object.class.getResource("/frontend/professor/PMainView.fxml");
	
	public void setUp() {
		super.setUp();
		labelWelcomeMessage.setText(Messages.getUILabel(UILabel.WELCOME_MESSAGE_ADMIN));
		labelWhatDoNext.setText(Messages.getUILabel(UILabel.WHAT_TO_DO_NEXT_QUESTION));
//		TODO: Create UILabels
//		btnSeeMyCourses.setText(Messages.getUILabel(UILabel.PROFESSOR_SEE_MY_COURSES));
//		btnQualifyStudents.setText(Messages.getUILabel(UILabel.PROFESSOR_QUALIFY_STUDENTS));
	}
	
	public void btnSeeMyCourses_Pressed() {
		ViewUtilities.openView(PCoursesViewController.view, view);
	}
	
	public void btnQualifyStudents_Pressed() {
//		TODO: Create view for qualifying students [PROFESSOR]
//		ViewUtilities.openView(PQualifyStudentsViewController.view, view);
	}
}