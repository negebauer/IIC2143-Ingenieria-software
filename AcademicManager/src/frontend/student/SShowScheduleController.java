package frontend.student;

import java.net.URL;

import frontend.main.MViewController;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SShowScheduleController extends MViewController {
	
	@FXML
	Label labelShowScheduleWelcomeMessage;	
	@FXML
	ChoiceBox<?> chBxSemesters;
	
	public static URL view = Object.class.getResource("/frontend/student/SShowSchedule.fxml");
	
	public void setUp() {
		super.setUp();
		
	}
}
