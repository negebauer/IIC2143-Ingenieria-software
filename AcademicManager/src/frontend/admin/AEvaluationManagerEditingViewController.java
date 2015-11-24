package frontend.admin;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import backend.courses.Classroom;
import backend.courses.Evaluation;
import backend.courses.Evaluation.CourseEvaluation;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import backend.others.Utilities;
import frontend.main.MViewController;
import frontend.others.ViewUtilities;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AEvaluationManagerEditingViewController extends MViewController {

	@FXML
	ComboBox<String> chBxClassroom;
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

	static URL view = Object.class.getResource("/frontend/admin/AEvaluationManagerEditingView.fxml");
	boolean isCreating = false;

	@Override
	public void setUp() {
		super.setUp();
		labelClassroom.setText(Messages.getUILabel(UILabel.SELECT_CLASSROOM));

		ArrayList<String> evaluationTypes = new ArrayList<String>();
		for (CourseEvaluation evaluationType : CourseEvaluation.values()) {
			evaluationTypes.add(CourseEvaluation.getCourseEvaluationMessage(evaluationType));
		}
		chBxEvaluationType.setItems(FXCollections.observableArrayList(evaluationTypes));

		ArrayList<String> classrooms = new ArrayList<String>();
		for (Classroom clasroom : Manager.INSTANCE.classrooms) {
			classrooms.add(clasroom.getInitials());
		}
		chBxClassroom.setItems(FXCollections.observableArrayList(classrooms));
		ViewUtilities.autoComplete(chBxClassroom);
		
		Evaluation selectedEvaluation = Manager.INSTANCE.currentEditingEvaluation;
		if (selectedEvaluation != null) {
			chBxClassroom.getSelectionModel().select(selectedEvaluation.getClassroom().getInitials());
			chBxEvaluationType.getSelectionModel().select(CourseEvaluation.getCourseEvaluationMessage(selectedEvaluation.getCourseEvaluation()));
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ENGLISH);
			String[] rawDate = dateFormat.format((selectedEvaluation.getDate())).split(" ");
			String day = rawDate[0];
			String[] rawDay = day.split("\\.");
			txBxDay.setText(rawDay[0]);
			txBxMonth.setText(rawDay[1]);
			txBxYear.setText(rawDay[2]);
			txBxHour.setText(rawDate[1]);

			isCreating = false;
		} else {
			isCreating = true;
		}

		
	}

	public void btnSaveEvaluation_Pressed() {
		String day = txBxDay.getText();
		String month = txBxMonth.getText();
		String year = txBxYear.getText();
		String hour = txBxHour.getText();
		String dateString = day + "." + month + "." + year + " " + hour;
		Classroom classroom = null;

		for (Classroom Uclassroom : Manager.INSTANCE.classrooms) {
			if (Uclassroom.getInitials() == chBxClassroom.getSelectionModel().getSelectedItem()) {
				classroom = Uclassroom;
				break;
			}
		}

		CourseEvaluation evaluationType = null;
		if (!chBxEvaluationType.getSelectionModel().isEmpty()
				& chBxEvaluationType.getItems().contains(chBxEvaluationType.getSelectionModel().getSelectedItem())) {
			evaluationType = CourseEvaluation
					.getCourseEvaluation(chBxEvaluationType.getSelectionModel().getSelectedItem());
		}

		Evaluation selectedEvaluation = Manager.INSTANCE.currentEditingEvaluation;

		if (isCreating) {
			selectedEvaluation = new Evaluation(evaluationType, classroom, dateString);
			Manager.INSTANCE.currentEditignCourse.getEvaluations().add(selectedEvaluation);
		} else {
			selectedEvaluation.setClassroom(classroom);
			selectedEvaluation.setDate(Utilities.getDateFromString(dateString));
			selectedEvaluation.setCourseEvaluation(evaluationType);
		}

		Manager.INSTANCE.currentEditingEvaluation = null;
		super.btnBack_Pressed();
	}
}
