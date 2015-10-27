package frontend.view.main;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ICourseEditorCreatorController implements IController {
	
	@FXML
	ChoiceBox<String> chBxICourses;
	@FXML
	Button btnEditICourse;
	@FXML
	Button btnCreateNewICourse;
	@FXML
	ListView<String> listAssistantsOrProfessors;
	@FXML
	ChoiceBox<String> chBxAssistantsOrProfessors;
	@FXML
	Button btnAddAssistantOrProfessor;
	@FXML
	Button btnRemoveAssistantOrProfessor;
	@FXML
	ChoiceBox<String> chBxClassrooms;
	@FXML
	Label labelPickAssistantsOrProfessors;
	@FXML
	Label labelClasroomSelection;
	@FXML
	Label labelPickShedule;
	@FXML
	ChoiceBox<String> chBxDays;
	@FXML
	CheckBox chckBxModule1;
	@FXML
	CheckBox chckBxModule2;
	@FXML
	CheckBox chckBxModule4;
	@FXML
	CheckBox chckBxModule3;
	@FXML
	CheckBox chckBxModule7;
	@FXML
	CheckBox chckBxModule8;
	@FXML
	CheckBox chckBxModule6;
	@FXML
	CheckBox chckBxModule5;
	@FXML
	Label labelClassTypeChoose;
	@FXML
	ChoiceBox<String> chBxClassesTypes;
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}

}
