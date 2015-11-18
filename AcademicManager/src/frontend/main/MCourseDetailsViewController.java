package frontend.main;

import java.net.URL;

import backend.manager.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class MCourseDetailsViewController extends MViewController {

	@FXML
	TextArea txADetails;

	public static URL view = Object.class.getResource("/frontend/main/MCourseDetailsView.fxml");

	@Override
	public void setUp() {
		super.setUp();
		hideBack();
		hideReload();
		hideLanguage();
		hideLogout();

		txADetails.setText(Manager.INSTANCE.courseDetailsToShow);
	}
}
