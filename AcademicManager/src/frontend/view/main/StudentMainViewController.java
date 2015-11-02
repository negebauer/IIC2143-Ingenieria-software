package frontend.view.main;

import java.net.URL;

import backend.others.Messages;
import backend.others.Messages.UILabel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StudentMainViewController extends UIViewController {

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
	
	static URL view = Object.class.getResource("/frontend/view/main/StudentMainView.fxml");

	public void setUp() {
		super.setUp();
		btnEditSemester.setText(Messages.getUILabel(UILabel.STUDENT_EDIT_SEMESTER));
		btnCurricularAdvance.setText(Messages.getUILabel(UILabel.STUDENT_SEE_CURRICULAR_ADVANCE));
		btnShowSchedule.setText(Messages.getUILabel(UILabel.STUDENT_SEE_SCHEDULE));
		labelWhatDoNext.setText(Messages.getUILabel(UILabel.WHAT_TO_DO_NEXT_QUESTION));
		labelWelcomeMessage.setText(Messages.getUILabel(UILabel.WELCOME_MESSAGE));		
	}

	public void btnCurricularAdvance_Pressed() {
		ViewUtilities.openView(CurricularAdvanceController.view, view);
	}

	public void btnEditSemester_Pressed() {
		ViewUtilities.openView(SemesterEditorCreatorController.view, view);
	}

	public void btnShowSchedule_Pressed() {
		ViewUtilities.openView(ShowScheduleController.view, view);
	}

}
