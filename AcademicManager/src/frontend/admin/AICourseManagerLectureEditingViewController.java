package frontend.admin;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Assistantship;
import backend.courses.Classroom;
import backend.courses.Laboratory;
import backend.courses.Lecture;
import backend.courses.Schedule;
import backend.interfaces.ICourse;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import backend.users.Assistant;
import backend.users.Professor;
import frontend.main.MViewController;
import frontend.others.ViewUtilities;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class AICourseManagerLectureEditingViewController extends MViewController {

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
	Label labelClassTypeChoose;
	@FXML
	ChoiceBox<String> chBxClassesTypes;
	@FXML
	Button btnSaveCourse;
	@FXML
	Button btnSeeSchedule;

	static URL view = Object.class.getResource("/frontend/admin/AICourseManagerLectureEditingView.fxml");
	ICourse currentEditingICourse = null;
	ArrayList<String> assistantsOrProfessors = new ArrayList<String>();
	boolean isLaboratory = false;
	boolean isLecture = false;
	boolean isAssistantship = false;

	String[] classesTypes = new String[] { "Assistantship", "Lecture", "Laboratory" };

	@Override
	public void setUp() {
		super.setUp();
		showMainView();
		hideEditView();

		btnEditICourse.setText(Messages.getUILabel(UILabel.EDIT_ICOURSE));
		btnCreateNewICourse.setText(Messages.getUILabel(UILabel.CREATE_ICOURSE));
		labelWelcomeMessage.setText(Messages.getUILabel(UILabel.ADMIN_ICOURSE_MANAGER_WELCOME_MESSAGE));
		btnAddAssistantOrProfessor.setText(Messages.getUILabel(UILabel.ADD_ASSISTANT_OR_PROFESSOR));
		btnRemoveAssistantOrProfessor.setText(Messages.getUILabel(UILabel.REMOVE_ASSISTANT_OR_PROFESSOR));
		labelPickAssistantsOrProfessors.setText(Messages.getUILabel(UILabel.PICK_ASSISTANT_OR_PROFESSOR));
		labelClasroomSelection.setText(Messages.getUILabel(UILabel.SELECT_CLASSROOM));
		labelClassTypeChoose.setText(Messages.getUILabel(UILabel.SELECT_CLASS_TYPE));
		btnSaveCourse.setText(Messages.getUILabel(UILabel.SAVE_ICOURSE));
		btnSeeSchedule.setText(Messages.getUILabel(UILabel.SEE_SCHEDULE));

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

		String selectedICourse = chBxICourses.getSelectionModel().getSelectedItem();
		if (selectedICourse == "Assistantship") {
			Assistantship selectedAssistanship = Manager.INSTANCE.currentEditignCourse.getAssistantship();
			currentEditingICourse = selectedAssistanship;
			// Manager.INSTANCE.currentEditignCourse.getCourses().remove(selectedAssistanship);
			this.isAssistantship = true;
			this.isLecture = false;
			this.isLaboratory = false;
			ArrayList<String> assistants = new ArrayList<String>();
			for (Assistant assistant : selectedAssistanship.getAssistants()) {
				assistants.add(assistant.getName() + " " + assistant.getLastnameFather() + " "
						+ assistant.getLastnameMother());
			}
			listAssistantsOrProfessors.setItems(FXCollections.observableArrayList(assistants));
			assistantsOrProfessors = assistants;
			chBxClassesTypes.getSelectionModel().select("Assistantship");
			Manager.INSTANCE.currentEditingSchedule = selectedAssistanship.getSchedule();

		} else if (selectedICourse == "Lecture") {
			Lecture selectedLecture = Manager.INSTANCE.currentEditignCourse.getLecture();
			currentEditingICourse = selectedLecture;
			// Manager.INSTANCE.currentEditignCourse.getCourses().remove(selectedLecture);
			this.isLecture = true;
			this.isAssistantship = false;
			this.isLaboratory = false;
			ArrayList<String> professors = new ArrayList<String>();
			for (Professor professor : selectedLecture.getProfessors()) {
				professors.add(professor.getName() + " " + professor.getLastnameFather() + " "
						+ professor.getLastnameMother());
			}
			listAssistantsOrProfessors.setItems(FXCollections.observableArrayList(professors));
			assistantsOrProfessors = professors;
			chBxClassesTypes.getSelectionModel().select("Lecture");
			Manager.INSTANCE.currentEditingSchedule = selectedLecture.getSchedule();

		} else if (selectedICourse == "Laboratory") {
			Laboratory selectedLaboratory = Manager.INSTANCE.currentEditignCourse.getLaboratory();
			currentEditingICourse = selectedLaboratory;
			// Manager.INSTANCE.currentEditignCourse.getCourses().remove(selectedLaboratory);
			this.isLaboratory = true;
			this.isAssistantship = false;
			this.isLecture = false;
			ArrayList<String> professors = new ArrayList<String>();
			for (Professor professor : selectedLaboratory.getProfessors()) {
				professors.add(professor.getName() + " " + professor.getLastnameFather() + " "
						+ professor.getLastnameMother());
			}
			listAssistantsOrProfessors.setItems(FXCollections.observableArrayList(professors));
			assistantsOrProfessors = professors;
			chBxClassesTypes.getSelectionModel().select("Laboratory");
			Manager.INSTANCE.currentEditingSchedule = selectedLaboratory.getSchedule();

		}

		hideMainView();
		showEditView();
	}

	public void btnCreateNewICourse_Pressed() {
		hideMainView();
		showEditView();
		Manager.INSTANCE.currentEditingSchedule = new Schedule();
		currentEditingICourse = null;
	}

	public void btnAddAssistantOrProfessor_Pressed() {
		String valueSelected = chBxAssistantsOrProfessors.getSelectionModel().getSelectedItem();
		assistantsOrProfessors.add(valueSelected);
		listAssistantsOrProfessors.setItems(FXCollections.observableArrayList(assistantsOrProfessors));
	}

	public void btnRemoveAssistantOrProfessor_Pressed() {
		String valueSelected = chBxAssistantsOrProfessors.getSelectionModel().getSelectedItem();
		assistantsOrProfessors.remove(valueSelected);
		listAssistantsOrProfessors.setItems(FXCollections.observableArrayList(assistantsOrProfessors));
	}

	public void btnSaveCourse_Pressed() {
		Schedule schedule = Manager.INSTANCE.currentEditingSchedule;
		String classroomString = chBxClassrooms.getSelectionModel().getSelectedItem();
		Classroom classroom = null;
		for (Classroom classroomLocal : Manager.INSTANCE.classrooms) {
			if (classroomLocal.getInitials() == classroomString) {
				classroom = classroomLocal;
				break;
			}
		}

		ObservableList<String> AssistantsOrProfessorsStrings = chBxAssistantsOrProfessors.getItems();

		ICourse savedCourse = null;

		if (isLecture) {
			ArrayList<Professor> professors = new ArrayList<Professor>();
			for (Professor prof : Manager.INSTANCE.professors) {
				if (AssistantsOrProfessorsStrings
						.contains(prof.getName() + " " + prof.getLastnameFather() + " " + prof.getLastnameMother())) {
					professors.add(prof);
				}
			}

			savedCourse = new Lecture(professors, classroom, schedule);

		} else if (isLaboratory) {
			ArrayList<Professor> professors = new ArrayList<Professor>();
			for (Professor prof : Manager.INSTANCE.professors) {
				if (AssistantsOrProfessorsStrings
						.contains(prof.getName() + " " + prof.getLastnameFather() + " " + prof.getLastnameMother())) {
					professors.add(prof);
				}
			}
			savedCourse = new Laboratory(professors, classroom, schedule);

		} else if (isAssistantship) {
			ArrayList<Assistant> assistants = new ArrayList<Assistant>();
			for (Assistant assist : Manager.INSTANCE.assistants) {
				if (AssistantsOrProfessorsStrings.contains(
						assist.getName() + " " + assist.getLastnameFather() + " " + assist.getLastnameMother())) {
					assistants.add(assist);
				}
			}

			savedCourse = new Assistantship(assistants, classroom, schedule);
		}

		if (savedCourse != null) {
			// Manager.INSTANCE.currentEditignCourse.addCourse(savedCourse);
		}

		// ViewUtilities.openView(view, ACourseManagerViewController.view);
		hideEditView();
		showMainView();

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

		chBxClassesTypes.setItems(FXCollections.observableArrayList(classesTypes));

		chBxClassesTypes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String value, String newValue) {
				if (newValue == "Assistantship") {
					ArrayList<String> assistants = new ArrayList<String>();
					for (Assistant assistant : Manager.INSTANCE.assistants) {
						assistants.add(assistant.getName() + " " + assistant.getLastnameFather() + " "
								+ assistant.getLastnameMother());
					}
					chBxAssistantsOrProfessors.setItems(FXCollections.observableArrayList(assistants));
				} else {
					ArrayList<String> professors = new ArrayList<String>();
					for (Professor professor : Manager.INSTANCE.professors) {
						professors.add(professor.getName() + " " + professor.getLastnameFather() + " "
								+ professor.getLastnameMother());
					}
					chBxAssistantsOrProfessors.setItems(FXCollections.observableArrayList(professors));
				}
			}
		});

		ArrayList<String> classrooms = new ArrayList<String>();
		for (Classroom classroom : Manager.INSTANCE.classrooms) {
			classrooms.add(classroom.getInitials());
		}
		chBxClassrooms.setItems(FXCollections.observableArrayList(classrooms));
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
