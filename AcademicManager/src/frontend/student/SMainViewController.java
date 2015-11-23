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
	@FXML
	Button btnForums;
	@FXML
	Button btnCourseFinder;
	@FXML
	Button btnStudyProgram;
	@FXML
	Button btnChangeStudyProgram;

	public static URL view = Object.class.getResource("/frontend/student/SMainView.fxml");
	Student user = (Student) Manager.INSTANCE.currentUser;
	
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
		
		labelUserInformation.setText(user.getStudentInformation());
		
		// TODO: [STUDENT] Create UILabel
		// btnCoursedSemesters.setText(Messages.getUILabel(UILabel.STUDENT_SHOW_COURSED_SEMESTERS));
		// btnAcademicHistory.setText(Messages.getUILabel(UILabel.STUDENT_SHOW_ACADEMIC_HISTORY));
		// btnForums.setText(Messages.getUILabel(UILabel.STUDENT_SHOW_FORUMS));
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
	
	public void btnForums_Pressed() {
		ViewUtilities.openView(SForumsViewController.view, view);
	}
	
	public void btnCourseFinder_Pressed() {
		ViewUtilities.openView(SCourseSearcherViewController.view, view);
	}
	
	public void btnStudyProgram_Pressed() {
		ViewUtilities.openView(SStudyProgramViewController.view, view);
	}
	
	public void btnChangeStudyProgram_Pressed() {
		ViewUtilities.openView(SChangeStudyProgramViewController.view, view);
	}

}