package frontend.main;

import java.net.URL;

import backend.manager.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class MAlertViewController extends MViewController {
	
	@FXML
	TextArea txAAlert;
	
	public static URL view = Object.class.getResource("/frontend/main/MAlertView.fxml");
	
	@Override
	public void setUp() {
		super.setUp();
		hideBack();
		hideReload();
		hideLanguage();
		hideLogout();

		txAAlert.setText(Manager.INSTANCE.alertMessage);
	}
}
