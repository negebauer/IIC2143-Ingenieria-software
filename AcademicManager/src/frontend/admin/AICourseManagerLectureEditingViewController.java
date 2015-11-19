package frontend.admin;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Classroom;
import backend.courses.Lecture;
import backend.courses.Schedule;
import backend.interfaces.ICourse;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
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

public class AICourseManagerLectureEditingViewController extends MViewController {

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

	static URL view = Object.class.getResource("/frontend/admin/AICourseManageLectureEditingView.fxml");
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
		
		if (Manager.INSTANCE.currentEditignICourse instanceof Lecture & Manager.INSTANCE.currentEditignICourse != null) {
			isCreating = false;
			Lecture selectedLecture = (Lecture) Manager.INSTANCE.currentEditignICourse;

			Manager.INSTANCE.currentEditingSchedule = selectedLecture.getSchedule();
			
			ArrayList<String> professors = new ArrayList<String>();
			for (Professor professor : selectedLecture.getProfessors()) {
				professors.add(professor.getName() + " " + professor.getLastnameFather() + " " + professor.getLastnameMother() + "-" + professor.getRut());
			}
			listAssistantsOrProfessors.setItems(FXCollections.observableArrayList(professors));
			
			chBxClassrooms.getSelectionModel().select(selectedLecture.getClassroom().getInitials());
			
		} else {
			isCreating = true;
			Manager.INSTANCE.currentEditignICourse = new Lecture(null, null, new Schedule());
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
			} else {
				// TODO Uncomment when function is finished
				//ViewUtilities.showAlert("The class is not in the classes of the course");
			}
		} else {
			// TODO Uncomment when function is finished
			//ViewUtilities.showAlert("The ICourse is null");
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
			
			for (Professor professor : Manager.INSTANCE.professors) {
				if (rut.equals(professor.getRut())) {
					if (!((Lecture)Manager.INSTANCE.currentEditignICourse).getProfessors().contains(professor)) {
						((Lecture)Manager.INSTANCE.currentEditignICourse).addProfessor(professor);
						updatedElements.add(valueSelected);
						listAssistantsOrProfessors.setItems(updatedElements);
						break;
					} else {
						// TODO Uncomment when function is finished
						//ViewUtilities.showAlert("The professor is already in that course");
						break;
					}
				}
			}
		} else {
			// TODO Uncomment when function is finished
			//ViewUtilities.showAlert("Select a professor to add");
		}
	}

	public void btnRemoveAssistantOrProfessor_Pressed() {
		if (!listAssistantsOrProfessors.getSelectionModel().isEmpty()) {
			ObservableList<String> updatedElements = listAssistantsOrProfessors.getItems();
			
			String valueSelected = listAssistantsOrProfessors.getSelectionModel().getSelectedItem();
			String rut = valueSelected.split("-")[1];
			
			for (Professor professor : Manager.INSTANCE.professors) {
				if (rut.equals(professor.getRut())) {
					if (((Lecture)Manager.INSTANCE.currentEditignICourse).getProfessors().contains(professor)) {
						((Lecture)Manager.INSTANCE.currentEditignICourse).removeProfessor(professor);
						updatedElements.remove(valueSelected);
						listAssistantsOrProfessors.setItems(updatedElements);
						break;
					} else {
						// TODO Uncomment when function is finished
						//ViewUtilities.showAlert("The professor is not contained in the class");
						break;
					}
				}
			}
		} else {
			// TODO Uncomment when function is finished
			//ViewUtilities.showAlert("Select a professor to remove");
		}
	}

	public void btnSaveCourse_Pressed() {
		Schedule schedule = Manager.INSTANCE.currentEditingSchedule;
		Classroom classroom = null;
		
		if (!chBxClassrooms.getSelectionModel().isEmpty()) {
			String classroomString = chBxClassrooms.getSelectionModel().getSelectedItem();
			for (Classroom classroomLocal : Manager.INSTANCE.classrooms) {
				if (classroomLocal.getInitials().equals(classroomString)) {
					classroom = classroomLocal;
					break;
				}
			}
			
			Manager.INSTANCE.currentEditignICourse.setClassroom(classroom);
			Manager.INSTANCE.currentEditignICourse.setSchedule(schedule);
			
			if (isCreating) {
				Manager.INSTANCE.currentEditignCourse.addCourse(Manager.INSTANCE.currentEditignICourse);
			}
			
			Manager.INSTANCE.currentEditignICourse = null;
			Manager.INSTANCE.currentEditingSchedule = null;
			super.btnBack_Pressed();
		} else {
			// TODO Uncomment when function is finished
			//ViewUtilities.showAlert("Select a classroom first");
		}		
	}

}
