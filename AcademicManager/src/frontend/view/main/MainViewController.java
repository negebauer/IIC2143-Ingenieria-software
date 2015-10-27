package frontend.view.main;

import java.net.URL;

import backend.others.Const;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainViewController implements IController {

	@FXML
	Button btnEditSemester;
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
		btnEditSemester.setText(Messages.getUILabel(UILabel.STUDENT_EDIT_SEMESTER));
		btnCurricularAdvance.setText(Messages.getUILabel(UILabel.STUDENT_SEE_CURRICULAR_ADVANCE));
		btnCreateNewSemester.setText(Messages.getUILabel(UILabel.STUDENT_CREATE_NEW_SEMESTER));
		btnShowSchedule.setText(Messages.getUILabel(UILabel.STUDENT_SEE_SCHEDULE));
		btnCloseSession.setText(Messages.getUILabel(UILabel.LOGOUT));
		labelWhatDoNext.setText(Messages.getUILabel(UILabel.WHAT_TO_DO_NEXT_QUESTION));
		labelWelcomeMessage.setText(Messages.getUILabel(UILabel.WELCOME_MESSAGE));		
	}

	public void btnCreateNewSemester_Pressed() {

		URL location = getClass().getResource(Const.SEMESTER_ADMIN);
		ViewUtilities.openView(location, "Crear Semestre");
	}

	public void btnCurricularAdvance_Pressed() {

		URL location = getClass().getResource(Const.CURRICULAR_ADVANCE);
		ViewUtilities.openView(location, "Avance Curricular");
	}

	public void btnEditSemester_Pressed() {

		URL location = getClass().getResource(Const.SEMESTER_ADMIN);
		ViewUtilities.openView(location, "Editar Semestre");
	}

	public void btnShowSchedule_Pressed() {

		URL location = getClass().getResource(Const.SHOW_SCHEDULE);
		ViewUtilities.openView(location, "Mostrar Horario");
	}

	public void btnCloseSession_Pressed() {

		URL location = getClass().getResource(Const.LOG_IN);
		ViewUtilities.openView(location, "Iniciar Sesion");
	}

}
