package frontend.admin;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Evaluation;
import backend.courses.Evaluation.CourseEvaluation;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import frontend.main.MViewController;
import frontend.others.ViewUtilities;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class AEvaluationManagerMainViewController extends MViewController {

	@FXML
	Button btnRemoveEvaluation;
	@FXML
	Label labelEvaluationsEditor;
	@FXML
	ChoiceBox<String> chBxEvaluations;
	@FXML
	Button btnEditEvaluation;
	@FXML
	Button btnCreateEvaluation;

	static URL view = Object.class.getResource("/frontend/admin/AEvaluationManagerMainView.fxml");

	@Override
	public void setUp() {
		super.setUp();
		btnRemoveEvaluation.setText(Messages.getUILabel(UILabel.REMOVE_EVALUATION));
		labelEvaluationsEditor.setText(Messages.getUILabel(UILabel.EVALUATION_MANAGER_WELCOME_MESSAGE));
		btnEditEvaluation.setText(Messages.getUILabel(UILabel.EDIT_EVALUATION));
		btnCreateEvaluation.setText(Messages.getUILabel(UILabel.CREATE_EVALUATION));

		ArrayList<String> evaluations = new ArrayList<String>();
		for (Evaluation evaluation : Manager.INSTANCE.currentEditignCourse.getEvaluations()) {
			String evaluationType = CourseEvaluation.getCourseEvaluationMessage(evaluation.getCourseEvaluation());
			String evaluationDate = evaluation.getDate().toString();
			String evaluationClassroom = evaluation.getClassroom().getInitials();
			evaluations.add(evaluationType + "-" + evaluationDate + "-" + evaluationClassroom);
		}
		chBxEvaluations.setItems(FXCollections.observableArrayList(evaluations));

		Evaluation evaluation = Manager.INSTANCE.currentEditingEvaluation;
		if (evaluation != null) {
			String evaluationType = CourseEvaluation.getCourseEvaluationMessage(evaluation.getCourseEvaluation());
			String evaluationDate = evaluation.getDate().toString();
			String evaluationClassroom = evaluation.getClassroom().getInitials();
			chBxEvaluations.getSelectionModel().select(evaluationType + "-" + evaluationDate + "-" + evaluationClassroom);
		}
	}

	public void btnRemoveEvaluation_Pressed() {
		if (!chBxEvaluations.getSelectionModel().isEmpty()) {
			String selectedEvaluationRawString = chBxEvaluations.getSelectionModel().getSelectedItem();
			for (Evaluation evaluation : Manager.INSTANCE.currentEditignCourse.getEvaluations()) {
				String evaluationType = CourseEvaluation.getCourseEvaluationMessage(evaluation.getCourseEvaluation());
				String evaluationDate = evaluation.getDate().toString();
				String evaluationClassroom = evaluation.getClassroom().getInitials();
				if (evaluationType + "-" + evaluationDate + "-" + evaluationClassroom == selectedEvaluationRawString) {
					Manager.INSTANCE.currentEditignCourse.removeEvaluation(evaluation);
					break;
				}
			}
		}
	}

	public void btnEditEvaluation_Pressed() {
		if (!chBxEvaluations.getSelectionModel().isEmpty()) {
			String selectedEvaluationRawString = chBxEvaluations.getSelectionModel().getSelectedItem();
			for (Evaluation evaluation : Manager.INSTANCE.currentEditignCourse.getEvaluations()) {
				String evaluationType = CourseEvaluation.getCourseEvaluationMessage(evaluation.getCourseEvaluation());
				String evaluationDate = evaluation.getDate().toString();
				String evaluationClassroom = evaluation.getClassroom().getInitials();
				if (evaluationType + "-" + evaluationDate + "-" + evaluationClassroom == selectedEvaluationRawString) {
					Manager.INSTANCE.currentEditingEvaluation = evaluation;
					break;
				}
			}
		}

		ViewUtilities.openView(AEvaluationManagerEditingViewController.view, view);
	}

	public void btnCreateEvaluation_Pressed() {
		Manager.INSTANCE.currentEditingEvaluation = null;
		ViewUtilities.openView(AEvaluationManagerEditingViewController.view, view);
	}
}
