package frontend.student;

import java.net.URL;

import backend.others.Messages;
import backend.others.Messages.UILabel;
import frontend.admin.ASemesterEditorCreatorController;
import frontend.main.MViewController;
import frontend.main.MViewUtilities;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SMainViewController extends MViewController {

	@FXML
	Button btnEditSemester;
	@FXML
	Button btnCurricularAdvance;
	@FXML
	Button btnShowSchedule;
	@FXML
	Label labelWhatDoNext;
	@FXML
	Label labelWelcomeMessage;
	
	public static URL view = Object.class.getResource("/frontend/student/SMainView.fxml");

	public void setUp() {
		super.setUp();
		btnEditSemester.setText(Messages.getUILabel(UILabel.STUDENT_EDIT_SEMESTER));
		btnCurricularAdvance.setText(Messages.getUILabel(UILabel.STUDENT_SEE_CURRICULAR_ADVANCE));
		btnShowSchedule.setText(Messages.getUILabel(UILabel.STUDENT_SEE_SCHEDULE));
		labelWhatDoNext.setText(Messages.getUILabel(UILabel.WHAT_TO_DO_NEXT_QUESTION));
		labelWelcomeMessage.setText(Messages.getUILabel(UILabel.WELCOME_MESSAGE));		
	}

	public void btnCurricularAdvance_Pressed() {
		MViewUtilities.openView(SCurricularAdvanceController.view, view);
	}

	public void btnEditSemester_Pressed() {
		MViewUtilities.openView(ASemesterEditorCreatorController.view, view);
	}

	public void btnShowSchedule_Pressed() {
		MViewUtilities.openView(SShowScheduleController.view, view);
	}

}
