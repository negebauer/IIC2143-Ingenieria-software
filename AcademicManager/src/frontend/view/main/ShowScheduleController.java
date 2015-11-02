package frontend.view.main;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ShowScheduleController extends UIViewController {
	
	@FXML
	Label labelShowScheduleWelcomeMessage;	
	@FXML
	ChoiceBox<?> chBxSemesters;
	
	static URL view = Object.class.getResource("/frontend/view/main/ShowScheduleController.fxml");
	
	@Override
	public void setUp() {
		super.setUp();
		
	}
}
