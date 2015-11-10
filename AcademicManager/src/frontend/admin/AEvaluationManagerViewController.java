package frontend.admin;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import backend.courses.Classroom;
import backend.courses.Evaluation;
import backend.courses.Evaluation.CourseEvaluation;
import backend.manager.Manager;
import backend.others.Utilities;
import frontend.main.MViewController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AEvaluationManagerViewController extends MViewController {
	
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
	
	static URL view = Object.class.getResource("/frontend/admin/AEvaluationManagerView.fxml");
	Evaluation selectedEvaluation = null;
	
	public void setUp() {
		super.setUp();
		//TODO set text for the fields, from messages.
		
		showMainView();
		hideEvaluationEditionView();
		
		ArrayList<String> evaluations = new ArrayList<String>();
		for (Evaluation evaluation : Manager.INSTANCE.currentEditignCourse.getEvaluations()) {
			String evaluationType = evaluation.getCourseEvaluation().toString();
			String evaluationDate = evaluation.getDate().toString();
			String evaluationClassroom = evaluation.getClassroom().toString();
			evaluations.add(evaluationType + "-" + evaluationDate + "-" + evaluationClassroom);
		}
		chBxEvaluations.setItems(FXCollections.observableArrayList(evaluations));
		
		ArrayList<String> evaluationTypes = new ArrayList<String>();
		for (CourseEvaluation evaluationType : CourseEvaluation.values()) {
			evaluationTypes.add(evaluationType.toString());
		}
		chBxEvaluationType.setItems(FXCollections.observableArrayList(evaluationTypes));
		
		ArrayList<String> classrooms = new ArrayList<String>();
		for (Classroom clasroom : Manager.INSTANCE.classrooms) {
			classrooms.add(clasroom.getInitials());
		}
		chBxClassroom.setItems(FXCollections.observableArrayList(classrooms));
	}
	
	public void btnEditEvaluation_Pressed() {
		if (!chBxEvaluations.getSelectionModel().isEmpty()) {
			String selectedEvaluationRawString = chBxEvaluations.getSelectionModel().getSelectedItem();
			for (Evaluation evaluation : Manager.INSTANCE.currentEditignCourse.getEvaluations()) {
				String evaluationType = evaluation.getCourseEvaluation().toString();
				String evaluationDate = evaluation.getDate().toString();
				String evaluationClassroom = evaluation.getClassroom().toString();
				if (evaluationType + "-" + evaluationDate + "-" + evaluationClassroom == selectedEvaluationRawString) {
					selectedEvaluation = evaluation;
					//Manager.INSTANCE.currentEditignCourse.getEvaluations().remove(evaluation);
				}
			}
			
			if (selectedEvaluation != null) {
				chBxClassroom.getSelectionModel().select(selectedEvaluation.getClassroom().getInitials());
				chBxEvaluationType.getSelectionModel().select(selectedEvaluation.getCourseEvaluation().toString());
				SimpleDateFormat dateFormat =  new SimpleDateFormat ("dd.MM.yyyy HH:mm", Locale.ENGLISH);
				String[] rawDate = dateFormat.format((selectedEvaluation.getDate())).split(" ");
				String[] rawDay = rawDate[0].split(".");
				txBxDay.setText(rawDay[0]);
				txBxMonth.setText(rawDay[1]);
				txBxYear.setText(rawDay[2]);
				txBxHour.setText(rawDate[1]);
			}
		}
		
	}
	
	public void btnCreateEvaluation_Pressed() {
		hideMainView();
		showEvaluationEditionView();
	}
	
	public void btnSaveEvaluation_Pressed() {
		String day = txBxDay.getText();
		String month = txBxMonth.getText();
		String year = txBxYear.getText();
		String hour = txBxHour.getText();
		String dateString = day + "." + month + "." + year + " " + hour;
		Classroom classroom = null;
		
		for (Classroom Uclassroom : Manager.INSTANCE.classrooms) {
			if (classroom.getInitials() == chBxClassroom.getSelectionModel().getSelectedItem()){
				classroom = Uclassroom;
			}
		}
		
		CourseEvaluation evaluationType = CourseEvaluation.valueOf(chBxEvaluationType.getSelectionModel().getSelectedItem());
		
		selectedEvaluation.setClassroom(classroom);
		selectedEvaluation.setDate(Utilities.getDateFromString(dateString));
		selectedEvaluation.setCourseEvaluation(evaluationType);
		
		//Evaluation evaluation = new Evaluation(evaluationType, classroom, dateString);
		//Manager.INSTANCE.currentEditignCourse.getEvaluations().add(evaluation);
	}
	
	public void showMainView() {
		labelEvaluationsEditor.setVisible(true);
		chBxEvaluations.setVisible(true);
		btnEditEvaluation.setVisible(true);
		btnCreateEvaluation.setVisible(true);
	}

	public void showEvaluationEditionView() {
		chBxClassroom.setVisible(true);
		labelClassroom.setVisible(true);
		txBxDay.setVisible(true);
		txBxMonth.setVisible(true);
		txBxYear.setVisible(true);
		txBxHour.setVisible(true);
		labelDay.setVisible(true);
		labelMonth.setVisible(true);
		labelYear.setVisible(true);
		labelHour.setVisible(true);
		chBxEvaluationType.setVisible(true);
		labelEvaluationType.setVisible(true);
		btnSaveEvaluation.setVisible(true);
	}
	
	public void hideMainView(){
		labelEvaluationsEditor.setVisible(false);
		chBxEvaluations.setVisible(false);
		btnEditEvaluation.setVisible(false);
		btnCreateEvaluation.setVisible(false);
	}

	public void hideEvaluationEditionView() {
		chBxClassroom.setVisible(false);
		labelClassroom.setVisible(false);
		txBxDay.setVisible(false);
		txBxMonth.setVisible(false);
		txBxYear.setVisible(false);
		txBxHour.setVisible(false);
		labelDay.setVisible(false);
		labelMonth.setVisible(false);
		labelYear.setVisible(false);
		labelHour.setVisible(false);
		chBxEvaluationType.setVisible(false);
		labelEvaluationType.setVisible(false);
		btnSaveEvaluation.setVisible(false);
	}
}
