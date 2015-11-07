package frontend.admin;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Assistantship;
import backend.courses.Laboratory;
import backend.courses.Lecture;
import backend.interfaces.ICourse;
import backend.manager.Manager;
import frontend.main.MViewController;
import frontend.others.ViewUtilities;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AICourseManagerViewController extends MViewController {
	
	@FXML
	ChoiceBox<String> chBxICourses;
	@FXML
	Button btnEditICourse;
	@FXML
	Button btnCreateNewICourse;
	@FXML
	Label labelWelcomeMessage;
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
	@FXML
	Button btnSaveCourse;
	
	static URL view = Object.class.getResource("/frontend/admin/AICourseManagerView.fxml");
	ICourse currentEditingICourse = null;
	
	public void setUp() {
		super.setUp();
		showMainView();
		hideEditView();
		
		//TODO set messages for all components.
		
		chBxICourses.setItems(FXCollections.observableArrayList("Assistantship", "Lecture", "Laboratory"));
		
	}

	public void btnEditICourse_Pressed() {
		hideMainView();
		showEditView();
		
		String selectedICourse = chBxICourses.getSelectionModel().getSelectedItem();
		if (selectedICourse == "Assistantship") {
			
		} else if (selectedICourse == "Lecture") {
			
		} else if (selectedICourse == "Laboratory") {
			
		}
	}
	
	public void btnCreateNewICourse_Pressed() {
		hideMainView();
		showEditView();
	}
	
	public void btnAddAssistantOrProfessor_Pressed() {
		
	}
	
	public void btnRemoveAssistantOrProfessor_Pressed() {
		
	}
	
	public void btnSaveCourse_Pressed() {
		// save iCourse
		
		ViewUtilities.openView(view, ACourseManagerViewController.view);
	}
	
	public void showMainView() {
		chBxICourses.setVisible(true);
		btnEditICourse.setVisible(true);
		btnCreateNewICourse.setVisible(true);
		labelWelcomeMessage.setVisible(true);
	}

	public void hideMainView() {
		chBxICourses.setVisible(false);
		btnEditICourse.setVisible(false);
		btnCreateNewICourse.setVisible(false);
		labelWelcomeMessage.setVisible(false);
	}

	public void showEditView() {
		listAssistantsOrProfessors.setVisible(true);
		chBxAssistantsOrProfessors.setVisible(true);
		btnAddAssistantOrProfessor.setVisible(true);
		btnRemoveAssistantOrProfessor.setVisible(true);
		chBxClassrooms.setVisible(true);
		labelPickAssistantsOrProfessors.setVisible(true);
		labelClasroomSelection.setVisible(true);
		labelPickShedule.setVisible(true);
		chBxDays.setVisible(true);
		chckBxModule1.setVisible(true);
		chckBxModule2.setVisible(true);
		chckBxModule4.setVisible(true);
		chckBxModule3.setVisible(true);
		chckBxModule7.setVisible(true);
		chckBxModule8.setVisible(true);
		chckBxModule6.setVisible(true);
		chckBxModule5.setVisible(true);
		labelClassTypeChoose.setVisible(true);
		chBxClassesTypes.setVisible(true);
		btnSaveCourse.setVisible(true);
	}

	public void hideEditView() {
		listAssistantsOrProfessors.setVisible(false);
		chBxAssistantsOrProfessors.setVisible(false);
		btnAddAssistantOrProfessor.setVisible(false);
		btnRemoveAssistantOrProfessor.setVisible(false);
		chBxClassrooms.setVisible(false);
		labelPickAssistantsOrProfessors.setVisible(false);
		labelClasroomSelection.setVisible(false);
		labelPickShedule.setVisible(false);
		chBxDays.setVisible(false);
		chckBxModule1.setVisible(false);
		chckBxModule2.setVisible(false);
		chckBxModule4.setVisible(false);
		chckBxModule3.setVisible(false);
		chckBxModule7.setVisible(false);
		chckBxModule8.setVisible(false);
		chckBxModule6.setVisible(false);
		chckBxModule5.setVisible(false);
		labelClassTypeChoose.setVisible(false);
		chBxClassesTypes.setVisible(false);
		btnSaveCourse.setVisible(true);
	}

}
