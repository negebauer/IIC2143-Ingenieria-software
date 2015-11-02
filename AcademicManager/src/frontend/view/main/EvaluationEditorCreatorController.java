package frontend.view.main;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EvaluationEditorCreatorController extends UIViewController {
	
	@FXML
	Label labelEvaluationsEditor;
	@FXML
	ChoiceBox<String> chBxEvaluations;
	@FXML
	Button btnEditEvaluation;
	@FXML
	Button btnCreateEvaluation;
	@FXML
	ChoiceBox<String> chBxClassroom;
	@FXML
	Label labelClassroom;
	@FXML
	TextField txBxDay;
	@FXML
	TextField txBxMonth;
	@FXML
	TextField txBxYear;
	@FXML
	TextField txBxHour;
	@FXML
	Label labelDay;
	@FXML
	Label labelMonth;
	@FXML
	Label labelYear;
	@FXML
	Label labelHour;
	@FXML
	ChoiceBox<String> chBxEvaluationType;
	@FXML
	Label labelEvaluationType;
	@FXML
	Button btnSaveEvaluation;
	
	static URL view = Object.class.getResource("/frontend/view/main/EvaluationEditorCreator.fxml");
	
	@Override
	public void setUp() {
		super.setUp();
		
	}
	
	
}
