package frontend.admin;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.StudyProgram;
import backend.enums.School;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import frontend.main.MViewController;
import frontend.others.ViewUtilities;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AStudyProgramManagerEditingViewController extends MViewController {

	@FXML
	Label labelName;
	@FXML
	Label labelYear;
	@FXML
	Label labelMaxCreditsPerSemester;
	@FXML
	Label labelMaxFailedCredits;
	@FXML
	Label labelSchool;
	@FXML
	TextField txBxNameStudyProgram;
	@FXML
	TextField txBxYearStudyProgram;
	@FXML
	TextField txBxMaxCreditsPerSemester;
	@FXML
	TextField txBxMaxFailedCredits;
	@FXML
	ComboBox<String> chBxSchoolStudyProgram;
	@FXML
	Button btnEditSemesters;
	@FXML
	Button btnSaveStudyProgram;

	public static URL view = Object.class.getResource("/frontend/admin/AStudyProgramManagerEditingView.fxml");
	public boolean isCreating = false;

	@Override
	public void setUp() {

		super.setUp();
		labelName.setText(Messages.getUILabel(UILabel.NAME));
		labelYear.setText(Messages.getUILabel(UILabel.YEAR));
		labelMaxCreditsPerSemester.setText(Messages.getUILabel(UILabel.MAXIMUM_OF_CREDITS_PER_SEMESTER));
		labelMaxFailedCredits.setText(Messages.getUILabel(UILabel.MAXIMUM_OF_FAILED_CREDITS));
		labelSchool.setText(Messages.getUILabel(UILabel.SCHOOL));
		btnEditSemesters.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_CREATE_NEW));
		btnEditSemesters.setText(Messages.getUILabel(UILabel.EDIT_SEMESTERS));

		ArrayList<String> schools = new ArrayList<String>();
		for (School school : School.values()) {
			schools.add(School.getSchoolMessage(school));
		}
		chBxSchoolStudyProgram.setItems(FXCollections.observableArrayList(schools));

		StudyProgram studyProgram = Manager.INSTANCE.currentEditingStudyProgram;

		ViewUtilities.autoComplete(chBxSchoolStudyProgram);

		if (studyProgram != null) {
			fillFields(studyProgram);
			isCreating = false;
		} else {
			isCreating = true;
		}
	}

	public void fillFields(StudyProgram studyProgram) {
		txBxNameStudyProgram.setText(studyProgram.getName());
		txBxYearStudyProgram.setText(studyProgram.getyearProgram() + "");
		txBxMaxCreditsPerSemester.setText(studyProgram.getMaxCreditsPerSemester() + "");
		txBxMaxFailedCredits.setText(studyProgram.getMaxFailedCredits() + "");
		chBxSchoolStudyProgram.getSelectionModel().select(School.getSchoolMessage(studyProgram.getSchool()));
	}

	public void btnEditSemesters_Pressed() {
		saveStudyProgram();
		ViewUtilities.openView(ASemesterManagerMainViewController.view, view);
	}

	public void btnSaveStudyProgram_Pressed() {
		saveStudyProgram();
		Manager.INSTANCE.currentEditingStudyProgram = null;
		super.btnBack_Pressed();
	}
	
	public void saveStudyProgram() {
		StudyProgram currentProgram = Manager.INSTANCE.currentEditingStudyProgram;

		String name = txBxNameStudyProgram.getText();
		int year = Integer.parseInt(txBxYearStudyProgram.getText());
		int maxCreditsPerSemester = Integer.parseInt(txBxMaxCreditsPerSemester.getText());
		int maxFailedCredits = Integer.parseInt(txBxMaxFailedCredits.getText());

		School school = null;
		if (!chBxSchoolStudyProgram.getSelectionModel().isEmpty() && chBxSchoolStudyProgram.getItems()
				.contains(chBxSchoolStudyProgram.getSelectionModel().getSelectedItem())) {
			school = School.getSchool(chBxSchoolStudyProgram.getSelectionModel().getSelectedItem());
		} else {
			school = School.defaultSchool();
		}

		if (isCreating) {
			currentProgram = new StudyProgram(name, year, null, school, maxCreditsPerSemester, maxFailedCredits);
			Manager.INSTANCE.currentEditingStudyProgram = currentProgram;
			Manager.INSTANCE.studyPrograms.add(currentProgram);
		} else {
			currentProgram.setName(name);
			currentProgram.setMaxCreditsPerSemester(maxCreditsPerSemester);
			currentProgram.setMaxFailedCredits(maxFailedCredits);
			currentProgram.setSchool(school);
			currentProgram.setYearProgram(year);
		}
	}
}
