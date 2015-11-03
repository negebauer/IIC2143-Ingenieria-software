package frontend.student;

import java.net.URL;

import backend.others.Messages;
import backend.others.Messages.UILabel;
import frontend.main.MViewController;
import frontend.others.ViewUtilities;
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
	@FXML
	Button btnCoursedSemesters;
	
	
	public static URL view = Object.class.getResource("/frontend/student/SMainView.fxml");

	public void setUp() {
		super.setUp();
		btnEditSemester.setText(Messages.getUILabel(UILabel.STUDENT_EDIT_SEMESTER));
		btnCurricularAdvance.setText(Messages.getUILabel(UILabel.STUDENT_SEE_CURRICULAR_ADVANCE));
		btnShowSchedule.setText(Messages.getUILabel(UILabel.STUDENT_SEE_SCHEDULE));
		labelWhatDoNext.setText(Messages.getUILabel(UILabel.WHAT_TO_DO_NEXT_QUESTION));
		labelWelcomeMessage.setText(Messages.getUILabel(UILabel.WELCOME_MESSAGE));	
		btnCoursedSemesters.setText(Messages.getUILabel(UILabel.COURSED_SEMESTERS));
	}

	public void btnCurricularAdvance_Pressed() {
		ViewUtilities.openView(SCurricularAdvanceController.view, view);
	}

	public void btnEditSemester_Pressed() {
		ViewUtilities.openView(SCurrentSemesterViewController.view, view);
	}

	public void btnShowSchedule_Pressed() {
		ViewUtilities.openView(SShowScheduleController.view, view);
	}
	
	public void btnCoursedSemesters_Pressed() {
		
	}

}
