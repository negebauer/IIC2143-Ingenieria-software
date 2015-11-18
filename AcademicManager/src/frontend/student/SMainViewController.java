package frontend.student;

import java.net.URL;

import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import backend.users.Student;
import frontend.main.MViewController;
import frontend.others.ViewUtilities;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
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
	@FXML
	Button btnAcademicHistory;
	@FXML
	Label labelUserInformation;

	public static URL view = Object.class.getResource("/frontend/student/SMainView.fxml");

	@Override
	public void setUp() {
		super.setUp();
		btnEditSemester.setText(Messages.getUILabel(UILabel.STUDENT_EDIT_SEMESTER));
		btnCurricularAdvance.setText(Messages.getUILabel(UILabel.STUDENT_SEE_CURRICULAR_ADVANCE));
		btnShowSchedule.setText(Messages.getUILabel(UILabel.STUDENT_SEE_SCHEDULE));
		labelWhatDoNext.setText(Messages.getUILabel(UILabel.WHAT_TO_DO_NEXT_QUESTION));
		labelWelcomeMessage.setText(Messages.getUILabel(UILabel.WELCOME_MESSAGE));

		btnEditSemester.setCursor(Cursor.HAND);
		btnCurricularAdvance.setCursor(Cursor.HAND);
		btnShowSchedule.setCursor(Cursor.HAND);
		btnCoursedSemesters.setCursor(Cursor.HAND);
		btnAcademicHistory.setCursor(Cursor.HAND);
		
		Student student = (Student)Manager.INSTANCE.currentUser;	
		labelUserInformation.setText(student.getStudentInformation());
		
		// TODO: Create UILabel
		// btnCoursedSemesters.setText(Messages.getUILabel(UILabel.STUDENT_SHOW_COURSED_SEMESTERS));
		// btnAcademicHistory.setText(Messages.getUILabel(UILabel.STUDENT_SHOW_ACADEMIC_HISTORY));
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
		ViewUtilities.openView(SCoursedSemestersViewController.view, view);
	}

	public void btnAcademicHistory_Pressed() {
		ViewUtilities.openView(SAcademicHistoryViewController.view, view);
	}

}
