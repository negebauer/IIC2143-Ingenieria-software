package frontend.admin;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Assistantship;
import backend.courses.Classroom;
import backend.courses.Schedule;
import backend.interfaces.ICourse;
import backend.manager.CourseModificationChecker;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import backend.users.Assistant;
import backend.users.Professor;
import frontend.main.MViewController;
import frontend.others.ViewUtilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class AICourseManagerAssistantshipEditingViewController extends MViewController {

	@FXML
	ListView<String> listAssistantsOrProfessors;
	@FXML
	ComboBox<String> chBxAssistantsOrProfessors;
	@FXML
	Button btnAddAssistantOrProfessor;
	@FXML
	Button btnRemoveAssistantOrProfessor;
	@FXML
	ComboBox<String> chBxClassrooms;
	@FXML
	Label labelPickAssistantsOrProfessors;
	@FXML
	Label labelClasroomSelection;
	@FXML
	Button btnSaveCourse;
	@FXML
	Button btnSeeSchedule;
	@FXML
	Button btnRemoveICourse;
	
	
	static URL view = Object.class.getResource("/frontend/admin/AICourseManageAssistantshipEditingView.fxml");
	boolean isCreating = false;
	
	@Override
	public void setUp() {
		super.setUp();

		btnAddAssistantOrProfessor.setText(Messages.getUILabel(UILabel.ADD_PROFESSOR));
		btnRemoveAssistantOrProfessor.setText(Messages.getUILabel(UILabel.REMOVE_PROFESSOR));
		labelPickAssistantsOrProfessors.setText(Messages.getUILabel(UILabel.PICK_PROFESSOR));
		labelClasroomSelection.setText(Messages.getUILabel(UILabel.SELECT_CLASSROOM));
		btnSaveCourse.setText(Messages.getUILabel(UILabel.SAVE_ICOURSE));
		btnSeeSchedule.setText(Messages.getUILabel(UILabel.SEE_SCHEDULE));
		btnRemoveICourse.setText(Messages.getUILabel(UILabel.REMOVE_ICOURSE));
		
		ArrayList<String> classrooms = new ArrayList<String>();
		for (Classroom classroom : Manager.INSTANCE.classrooms) {
			classrooms.add(classroom.getInitials());
		}
		chBxClassrooms.setItems(FXCollections.observableArrayList(classrooms));
		ViewUtilities.autoComplete(chBxClassrooms);
		
		
		if (Manager.INSTANCE.currentEditignICourse != null && Manager.INSTANCE.currentEditignICourse instanceof Assistantship ) {
			isCreating = false;
			
			Assistantship selectedAssistantship = (Assistantship) Manager.INSTANCE.currentEditignICourse;

			Manager.INSTANCE.currentEditingSchedule = selectedAssistantship.getSchedule();
			
			ArrayList<String> professors = new ArrayList<String>();
			for (Assistant assistant : selectedAssistantship.getAssistants()) {
				professors.add(assistant.getName() + " " + assistant.getLastnameFather() + " " + assistant.getLastnameMother() + "-" + assistant.getRut());
			}
			listAssistantsOrProfessors.setItems(FXCollections.observableArrayList(professors));
			
			chBxClassrooms.getSelectionModel().select(selectedAssistantship.getClassroom().getInitials());

		} else {
			isCreating = true;
			Manager.INSTANCE.currentEditignICourse = new Assistantship(null, null, new Schedule());
			Manager.INSTANCE.currentEditingSchedule = Manager.INSTANCE.currentEditignICourse.getSchedule();
		}
		
		ArrayList<String> professors2 = new ArrayList<String>();
		for (Professor professor : Manager.INSTANCE.professors) {
			professors2.add(professor.getName() + " " + professor.getLastnameFather() + " "	+ professor.getLastnameMother() + "-" + professor.getRut());
		}
		chBxAssistantsOrProfessors.setItems(FXCollections.observableArrayList(professors2));
		
		ViewUtilities.autoComplete(chBxAssistantsOrProfessors);
		
	}
	
	public void btnRemoveICourse_Pressed() {
		if (Manager.INSTANCE.currentEditignICourse != null) {
			ArrayList<ICourse> courses = Manager.INSTANCE.currentEditignCourse.getCourses();
			if (courses.contains(Manager.INSTANCE.currentEditignICourse)) {
				courses.remove(Manager.INSTANCE.currentEditignICourse);
				Manager.INSTANCE.currentEditignCourse.setCourses(courses);
				super.btnBack_Pressed();
			} else {
				ViewUtilities.showAlert("Primero debes guardar la clase, para poder removerla despues");
			}
		} else {
			ViewUtilities.showAlert("La clase no existe");
		}
	}
	
	public void btnSeeSchedule_Pressed() {
		ViewUtilities.openNewView(AScheduleViewController.view);
	}

	public void btnAddAssistantOrProfessor_Pressed() {
		if (!chBxAssistantsOrProfessors.getSelectionModel().isEmpty()) {	
			ObservableList<String> updatedElements = listAssistantsOrProfessors.getItems();
			
			String valueSelected = chBxAssistantsOrProfessors.getSelectionModel().getSelectedItem();
			String rut = valueSelected.split("-")[1];
			
			for (Assistant assistant : Manager.INSTANCE.assistants) {
				if (rut == assistant.getRut()) {
					if (!((Assistantship)Manager.INSTANCE.currentEditignICourse).getAssistants().contains(assistant)) {
						((Assistantship)Manager.INSTANCE.currentEditignICourse).addAssistant(assistant);
						updatedElements.add(valueSelected);
						listAssistantsOrProfessors.setItems(updatedElements);
					} else {
						ViewUtilities.showAlert("Este profesor ya se encuentra en la clase");
					}
					break;
				}
			}
		} else {
			ViewUtilities.showAlert("Selecciona un profesor para agregar");
		}
	}

	public void btnRemoveAssistantOrProfessor_Pressed() {
		if (!listAssistantsOrProfessors.getSelectionModel().isEmpty()) {
			ObservableList<String> updatedElements = listAssistantsOrProfessors.getItems();
			
			String valueSelected = listAssistantsOrProfessors.getSelectionModel().getSelectedItem();
			String rut = valueSelected.split("-")[1];
			
			for (Assistant assistant : Manager.INSTANCE.assistants) {
				if (rut == assistant.getRut()) {
					if (((Assistantship)Manager.INSTANCE.currentEditignICourse).getAssistants().contains(assistant)) {
						((Assistantship)Manager.INSTANCE.currentEditignICourse).removeAssistant(assistant);
						updatedElements.remove(valueSelected);
						listAssistantsOrProfessors.setItems(updatedElements);
						break;
					} else {
						ViewUtilities.showAlert("El profesor no esta en la clase");
						break;
					}
				}
			}
		} else {
			ViewUtilities.showAlert("Primero selecciona un profesor para quitar");
		}
	}

	public void btnSaveCourse_Pressed() {
		Schedule schedule = Manager.INSTANCE.currentEditingSchedule;
		Classroom classroom = null;
		
		if (!chBxClassrooms.getSelectionModel().isEmpty()) {
			String classroomString = chBxClassrooms.getSelectionModel().getSelectedItem();
			for (Classroom classroomLocal : Manager.INSTANCE.classrooms) {
				if (classroomLocal.getInitials() == classroomString) {
					classroom = classroomLocal;
					break;
				}
			}
			
			String alertMessage = "";
			
			String clashes = "";
			ICourse iCourse = Manager.INSTANCE.currentEditignICourse;

			for (Assistant assistant : ((Assistantship)iCourse).getAssistants()) {
				String aClash = CourseModificationChecker.assistantClash(assistant, schedule, iCourse);
				if (!aClash.equals("")) {
					clashes += assistant.getName() + " " + assistant.getLastnameFather() + ":" +
						   aClash + "\n";
				}
			}
			
			if (!clashes.equals("")) {
				alertMessage += "No se puede usar este horario, ya que tiene problemas de topes de horarios de ayudantes:\n" + clashes;
			}
			
			clashes = CourseModificationChecker.classroomClash(classroom, schedule, Manager.INSTANCE.currentEditignICourse);
			
			if (clashes != "") {
				alertMessage += "No se puede crear la clase debido a que la sala esta ocupada en ese horario por otro(s) curso(s):\n" + clashes;
			}
			
			if (!alertMessage.equals("")) {
				ViewUtilities.showAlert(alertMessage);
			} else {
				Manager.INSTANCE.currentEditignICourse.setSchedule(schedule);
				Manager.INSTANCE.currentEditignICourse.setClassroom(classroom);
				
				if (isCreating) {
					Manager.INSTANCE.currentEditignCourse.addCourse(Manager.INSTANCE.currentEditignICourse);
				}
				
				Manager.INSTANCE.currentEditignICourse = null;
				Manager.INSTANCE.currentEditingSchedule = null;
				super.btnBack_Pressed();
			}
		} else {
			ViewUtilities.showAlert("Debes seleccionar una sala");
		}		
	}
}
