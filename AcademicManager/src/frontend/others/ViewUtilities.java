package frontend.others;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import backend.courses.Assistantship;
import backend.courses.Course;
import backend.courses.CoursedSemester;
import backend.courses.Laboratory;
import backend.courses.Lecture;
import backend.courses.StudyProgram;
import backend.courses.Schedule.DayModuleTuple;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import backend.users.Assistant;
import backend.users.Professor;
import backend.users.Student;
import frontend.main.MAlertViewController;
import frontend.main.MViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public final class ViewUtilities {

	public enum AutoCompleteMode {
		STARTS_WITH, 
		CONTAINING;
	}

	/**
	 * Open a new window view while keeping the view hierarchy. Allows the
	 * operation of the back button.
	 */
	public static void openView(URL location, URL sender) {
		FXMLLoader loader = new FXMLLoader(location);
		Parent root = null;
		try {
			root = (Parent) loader.load();
			((MViewController) loader.getController()).setUp();
			Stage stage = CurrentViewHandler.INSTANCE.primaryStage;
			if (sender != null) {
				CurrentViewHandler.INSTANCE.addNewParentView(sender);
			}
			stage.setScene(new Scene(root));
			stage.getIcons().add(new Image("file:images/icon_colors.png"));
			stage.setResizable(false);
			stage.setTitle("RENNAB");
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open a new window without adding it to the view hierarchy.
	 */
	public static void openView(URL location) {
		openView(location, null);
	}

	/**
	 * Open a new window
	 * @param location
	 */
	public static void openNewView(URL location) {
		FXMLLoader loader = new FXMLLoader(location);
		Parent root = null;
		try {
			root = (Parent) loader.load();
			((MViewController) loader.getController()).setUp();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setResizable(false);
			stage.setTitle("RENNAB");
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Open a view with an alert
	 * @param alertMessage The message to be shown in the alert
	 */
	public static void showAlert(String alertMessage) {
		Manager.INSTANCE.alertMessage = alertMessage;
		FXMLLoader loader = new FXMLLoader(MAlertViewController.view);
		Parent root = null;
		try {
			root = (Parent) loader.load();
			((MViewController) loader.getController()).setUp();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setResizable(false);
			stage.setTitle("ALERT");
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Autocompletar combobox al escribir
	 * @param comboBox
	 * @param mode
	 */
	public static <T> void autoComplete(ComboBox<T> comboBox) {
		ObservableList<T> data = comboBox.getItems();
		AutoCompleteMode mode = AutoCompleteMode.CONTAINING;

		comboBox.setEditable(true);
		comboBox.getEditor().focusedProperty().addListener(observable -> {
			if (comboBox.getSelectionModel().getSelectedIndex() < 0) {
				comboBox.getEditor().setText(null);
			}
		});
		comboBox.addEventHandler(KeyEvent.KEY_PRESSED, t -> comboBox.hide());
		comboBox.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			private boolean moveCaretToPos = false;
			private int caretPos;

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.UP) {
					caretPos = -1;
					moveCaret(comboBox.getEditor().getText().length());
					return;
				} else if (event.getCode() == KeyCode.DOWN) {
					if (!comboBox.isShowing()) {
						comboBox.show();
					}
					caretPos = -1;
					moveCaret(comboBox.getEditor().getText().length());
					return;
				} else if (event.getCode() == KeyCode.BACK_SPACE) {
					moveCaretToPos = true;
					caretPos = comboBox.getEditor().getCaretPosition();
				} else if (event.getCode() == KeyCode.DELETE) {
					moveCaretToPos = true;
					caretPos = comboBox.getEditor().getCaretPosition();
				}

				if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT
						|| event.getCode().equals(KeyCode.SHIFT) || event.getCode().equals(KeyCode.CONTROL)
						|| event.isControlDown() || event.getCode() == KeyCode.HOME || event.getCode() == KeyCode.END
						|| event.getCode() == KeyCode.TAB) {
					return;
				}

				ObservableList<T> list = FXCollections.observableArrayList();
				for (T aData : data) {
					if (mode.equals(AutoCompleteMode.STARTS_WITH) && aData.toString().toLowerCase()
							.startsWith(comboBox.getEditor().getText().toLowerCase())) {
						list.add(aData);
					} else if (mode.equals(AutoCompleteMode.CONTAINING)
							&& aData.toString().toLowerCase().contains(comboBox.getEditor().getText().toLowerCase())) {
						list.add(aData);
					}
				}
				String t = comboBox.getEditor().getText();

				comboBox.setItems(list);
				comboBox.getEditor().setText(t);
				if (!moveCaretToPos) {
					caretPos = -1;
				}
				moveCaret(t.length());
				if (!list.isEmpty()) {
					comboBox.show();
				}
			}

			private void moveCaret(int textLength) {
				if (caretPos == -1) {
					comboBox.getEditor().positionCaret(textLength);
				} else {
					comboBox.getEditor().positionCaret(caretPos);
				}
				moveCaretToPos = false;
			}
		});
	}

	/**
	 * Retorna el valor seleccionado por el combobox
	 * @param comboBox
	 * @return
	 */
	public static <T> T getComboBoxValue(ComboBox<T> comboBox) {
		if (comboBox.getSelectionModel().getSelectedIndex() < 0) {
			return null;
		} else {
			return comboBox.getItems().get(comboBox.getSelectionModel().getSelectedIndex());
		}
	}
	
	/**
	 * Retorna las carreras que realiza el alumno
	 * @param user
	 * @return
	 */
	public static ObservableList<String> getCarreersList(Student user) {
		ArrayList<String> carreers = new ArrayList<String>();
		for (StudyProgram p : user.getCurriculum().getStudyPrograms()) {
			carreers.add(p.getName());
		}
		return FXCollections.observableArrayList(carreers);
	}
	
	/**
	 * Retorna los semestres que ha cursado el alumno
	 * @param user
	 * @return
	 */
	public static ObservableList<String> getSemestersList(Student user) {
		ArrayList<String> semesterInfo = new ArrayList<String>();
		for (CoursedSemester coursedSemester : user.getCurriculum().getCoursedSemesters()) {
			int year = coursedSemester.getYear();
			String semester = coursedSemester.getSemester().getSemesterNumber();
			semesterInfo.add(year + " - " + semester);
		}
		if (user.getCurriculum().getCurrentSemester() != null) {
			int currentYear = user.getCurriculum().getCurrentSemester().getYear();
			String currentSemester = user.getCurriculum().getCurrentSemester().getSemester().getSemesterNumber();
			String currentSemesterInfo = currentYear + " - " + currentSemester;
			Boolean shouldAddCurrentSemesterInfo = true;
			for (String semesterInfoAlreadyShown : semesterInfo) {
				if (semesterInfoAlreadyShown.equals(currentSemesterInfo)) {
					shouldAddCurrentSemesterInfo = false;
					break;
				}
			}
			if (shouldAddCurrentSemesterInfo) {
				semesterInfo.add(currentSemesterInfo);
			}
		}
		return FXCollections.observableArrayList(semesterInfo);
	}
	
	/**
	 * Retorna el semestre alcual del alumno
	 * @param user
	 * @return
	 */
	public static ObservableList<String> getActualSemester(Student user) {
		ArrayList<String> semesterInfo = new ArrayList<String>();
		if (user.getCurriculum().getCurrentSemester() != null) {
			int currentYear = user.getCurriculum().getCurrentSemester().getYear();
			String currentSemester = user.getCurriculum().getCurrentSemester().getSemester().getSemesterNumber();
			semesterInfo.add(currentYear + " - " + currentSemester);
		}
		return FXCollections.observableArrayList(semesterInfo);
	}
	
	/**
	 * Agrega un label al boton y cambia el cursor
	 * @param button
	 * @param label
	 */
	public static void setButtonText(Button button, UILabel label) {
		if (label != null) {
			button.setText(Messages.getUILabel(label));
		}
		button.setCursor(Cursor.HAND);
	}

	/**
	 * Retorna una lista con detalles del curso
	 * @param course
	 * @return
	 */
	public static ArrayList<String> getDetails(Course course) {
		ArrayList<String> details = new ArrayList<String>();
		details.add(course.getInitials());			
		if (course.getLecture() != null) {						
			details.add(Messages.getUILabel(UILabel.LECTURE));
			Lecture lecture = course.getLecture();																
			String teachers = Messages.getUILabel(UILabel.PROFESSOR) + ": ";
			if (lecture.getProfessors().size() > 1) {
				details.add(teachers);
				for(Professor p : lecture.getProfessors()) {
					details.add(p.getName() + " " + p.getLastnameFather());
				}
			}
			else {
				Professor p = lecture.getProfessors().get(0);
				teachers += p.getName() + " " + p.getLastnameFather();
				details.add(teachers);
			}	
			String schedule = "Horario: ";
			for(DayModuleTuple t : lecture.getSchedule().getModules()) {
				schedule += (t.day.getDayString() + " " + t.module.getInt() + " ");
			}
			details.add(schedule);				
			details.add("Sala: " + lecture.getClassroom().getInitials());
			details.add("Cupos: " + lecture.getClassroom().getSize());						
			
		} 
		if (course.getAssistantship() != null) {
			details.add(" ");
			details.add(Messages.getUILabel(UILabel.ASSISTANTSHIP));
			
			Assistantship assistantship = course.getAssistantship();
			String assistants = Messages.getUILabel(UILabel.ASSISTANT) + ": ";
			if (assistantship.getAssistants().size() > 1) {
				details.add(assistants);
				for(Assistant p : assistantship.getAssistants()) {
					details.add(p.getName() + " " + p.getLastnameFather());
				}
			}
			else {
				Assistant p = assistantship.getAssistants().get(0);
				assistants += p.getName() + " " + p.getLastnameFather();
				details.add(assistants);
			}	
			String schedule = "Horario: ";
			for(DayModuleTuple t : assistantship.getSchedule().getModules()) {
				schedule += (t.day.getDayString() + " " + t.module.getInt() + " ");
			}
			details.add(schedule);				
			details.add("Sala: " + assistantship.getClassroom().getInitials());
		} 
		if (course.getLaboratory() != null) {
			details.add(Messages.getUILabel(UILabel.LABORATORY));
			
			Laboratory laboratory = course.getLaboratory();
			String teachers = Messages.getUILabel(UILabel.PROFESSOR) + ": ";
			if (laboratory.getProfessors().size() > 1) {
				details.add(teachers);
				for(Professor p : laboratory.getProfessors()) {
					details.add(p.getName() + " " + p.getLastnameFather());
				}
			}
			else {
				Professor p = laboratory.getProfessors().get(0);
				teachers += p.getName() + " " + p.getLastnameFather();
				details.add(teachers);
			}	
			String schedule = "Horario: ";
			for(DayModuleTuple t : laboratory.getSchedule().getModules()) {
				schedule += (t.day.getDayString() + " " + t.module.getInt() + " ");
			}
			details.add(schedule);				
			details.add("Sala: " + laboratory.getClassroom().getInitials());
			details.add("Cupos: " + laboratory.getClassroom().getSize());
		}		
		return details;
	}
	
	/**
	 * Retorna una lista con detalles del curso seleccionado
	 * @param selection
	 * @return
	 */
	public static ArrayList<String> getDetails(String[] selection) {
		ArrayList<String> details = new ArrayList<String>();
		for (Course course : Manager.INSTANCE.courses) {
			if (Validate.checkCourse(course, selection)) {										
				details = getDetails(course);				
				break;
			}
		}
		return details;
	}
}