package frontend.view.main;

import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
	Button btnChangeModeToAdmin;
	
	public void setUp() {
		btnCreateNewSemester.setText(Messages.getUILabel(UILabel.STUDENT_CREATE_NEW_SEMESTER));
		btnQualifySemester.setText(Messages.getUILabel(UILabel.STUDENT_QUALIFY_SEMESTER));
		btnCurricularAdvance.setText(Messages.getUILabel(UILabel.STUDENT_SEE_CURRICULAR_ADVANCE));
		btnCreateNewSemester.setText(Messages.getUILabel(UILabel.STUDENT_CREATE_NEW_SEMESTER));
		btnShowSchedule.setText(Messages.getUILabel(UILabel.STUDENT_SEE_SCHEDULE));
		btnChangeModeToAdmin.setText(Messages.getUILabel(UILabel.CHANGE_MODE_STUDENT_TO_ADMIN));
		
	}
	
	public void btnCreateNewSemester_Pressed() {
		System.out.println("hola si funciona");
	}
}
