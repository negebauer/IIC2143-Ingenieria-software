package frontend.admin;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Assistantship;
import backend.courses.Laboratory;
import backend.courses.Lecture;
import backend.courses.Schedule;
import backend.interfaces.ICourse;
import backend.manager.Manager;
import backend.users.Assistant;
import backend.users.Professor;
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
	@FXML
	Button btnSeeSchedule;
	
	static URL view = Object.class.getResource("/frontend/admin/AICourseManagerView.fxml");
	ICourse currentEditingICourse = null;
	
	public void setUp() {
		super.setUp();
		showMainView();
		hideEditView();
		
		//TODO set messages for all components.
		ArrayList<String> iCourses = new ArrayList<String>();
		for (ICourse iCourse : Manager.INSTANCE.currentEditignCourse.getCourses()) {
			if (iCourse.getClass().equals(Assistantship.class)) {
				iCourses.add("Assistantship");
			} else if (iCourse.getClass().equals(Laboratory.class)) {
				iCourses.add("Laboratory");
			} else if (iCourse.getClass().equals(Lecture.class)) {
				iCourses.add("Lecture");
			}
		}
		chBxICourses.setItems(FXCollections.observableArrayList(iCourses));
		
	}
	
	public void btnSeeSchedule_Pressed() {
		ViewUtilities.openNewView(AScheduleViewController.view);
	}
	
	public void btnEditICourse_Pressed() {
		hideMainView();
		showEditView();
		
		String selectedICourse = chBxICourses.getSelectionModel().getSelectedItem();
		if (selectedICourse == "Assistantship") {
			Assistantship selectedAssistanship = Manager.INSTANCE.currentEditignCourse.getAssistantship();
			ArrayList<String> assistants = new ArrayList<String>();
			for (Assistant assistant : selectedAssistanship.getAssistants()) {
				assistants.add(assistant.getName() + " " + assistant.getLastnameFather() + " " + assistant.getLastnameMother());
			}
			listAssistantsOrProfessors.setItems(FXCollections.observableArrayList(assistants));
			chBxClassesTypes.getSelectionModel().select("Assistantship");
			Manager.INSTANCE.currentEditingSchedule = selectedAssistanship.getSchedule();
			
			
		} else if (selectedICourse == "Lecture") {
			Lecture selectedLecture = Manager.INSTANCE.currentEditignCourse.getLecture();
			ArrayList<String> professors = new ArrayList<String>();
			for (Professor professor : selectedLecture.getProfessors()) {
				professors.add(professor.getName() + " " + professor.getLastnameFather() + " " + professor.getLastnameMother());
			}
			listAssistantsOrProfessors.setItems(FXCollections.observableArrayList(professors));
			chBxClassesTypes.getSelectionModel().select("Lecture");
			Manager.INSTANCE.currentEditingSchedule = selectedLecture.getSchedule();
			
		} else if (selectedICourse == "Laboratory") {
			Laboratory selectedLaboratory = Manager.INSTANCE.currentEditignCourse.getLaboratory();
			ArrayList<String> professors = new ArrayList<String>();
			for (Professor professor : selectedLaboratory.getProfessors()) {
				professors.add(professor.getName() + " " + professor.getLastnameFather() + " " + professor.getLastnameMother());
			}
			listAssistantsOrProfessors.setItems(FXCollections.observableArrayList(professors));
			chBxClassesTypes.getSelectionModel().select("Laboratory");
			Manager.INSTANCE.currentEditingSchedule = selectedLaboratory.getSchedule();
			
		}	
		
	}
	
	public void btnCreateNewICourse_Pressed() {
		hideMainView();
		showEditView();
		Manager.INSTANCE.currentEditingSchedule = new Schedule();
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
		btnSeeSchedule.setVisible(true);
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
		btnSeeSchedule.setVisible(false);
		labelClassTypeChoose.setVisible(false);
		chBxClassesTypes.setVisible(false);
		btnSaveCourse.setVisible(true);
	}

}
