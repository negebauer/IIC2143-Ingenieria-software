package frontend.view.main;

import backend.others.Messages;
import backend.others.Messages.UILabel;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainViewController {
	
	@FXML
	Button btnQualifySemester;
	@FXML
	Button btnCurricularAdvance;
	@FXML
	Button btnCreateNewSemester;
	@FXML
	Button btnShowSchedule;
	@FXML
	Label labelWhatDoNext;
	@FXML
	Label labelWelcomeMessage;
	@FXML
	Button btnCloseSession;
	
	public void setUp() {
		btnCreateNewSemester.setText(Messages.getUILabel(UILabel.STUDENT_CREATE_NEW_SEMESTER));
		btnQualifySemester.setText(Messages.getUILabel(UILabel.STUDENT_QUALIFY_SEMESTER));
		btnCurricularAdvance.setText(Messages.getUILabel(UILabel.STUDENT_SEE_CURRICULAR_ADVANCE));
		btnCreateNewSemester.setText(Messages.getUILabel(UILabel.STUDENT_CREATE_NEW_SEMESTER));
		btnShowSchedule.setText(Messages.getUILabel(UILabel.STUDENT_SEE_SCHEDULE));
		btnCloseSession.setText(Messages.getUILabel(UILabel.LOGOUT));
		labelWhatDoNext.setText(Messages.getUILabel(UILabel.WHAT_TO_DO_NEXT_QUESTION));
		labelWelcomeMessage.setText(Messages.getUILabel(UILabel.WELCOME_MESSAGE));
		
		
	}
	
	public void btnCreateNewSemester_Pressed() {
		System.out.println("hola si funciona");
	}
}
