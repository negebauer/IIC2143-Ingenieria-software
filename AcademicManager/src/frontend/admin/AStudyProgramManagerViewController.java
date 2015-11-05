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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AStudyProgramManagerViewController extends MViewController {
	
	@FXML
	Label labelEditStudyProgram;
	@FXML
	Label labelCreateStudyProgram;
	@FXML
	Button btnCreateStudyProgram;
	@FXML
	Button btnEditStudyProgram;
	@FXML
	ChoiceBox<String> chBxStudyProgramsList;
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
	ChoiceBox<String> chBxSchoolStudyProgram;
	@FXML
	Button btnEditSemesters;
	
	
	
	public static URL view = Object.class.getResource("/frontend/main/AStudyProgramManagerView.fxml");
	
	
	public void setUp() {
		super.setUp();
		labelCreateStudyProgram.setText(Messages.getUILabel(UILabel.STUDY_PROGRAM_CREATE_LABEL));
		labelEditStudyProgram.setText(Messages.getUILabel(UILabel.STUDY_PROGRAM_EDIT_LABEL));
		btnCreateStudyProgram.setText(Messages.getUILabel(UILabel.CREATE_NEW_STUDY_PROGRAM));
		btnEditStudyProgram.setText(Messages.getUILabel(UILabel.EDIT_STUDY_PROGRAM));
		
		labelName.setVisible(false);
		labelYear.setVisible(false);
		labelMaxCreditsPerSemester.setVisible(false);
		labelMaxFailedCredits.setVisible(false);
		labelSchool.setVisible(false);
		txBxNameStudyProgram.setVisible(false);
		txBxYearStudyProgram.setVisible(false);
		txBxMaxCreditsPerSemester.setVisible(false);
		txBxMaxFailedCredits.setVisible(false);
		chBxSchoolStudyProgram.setVisible(false);
		btnEditSemesters.setVisible(false);
		
		
		ArrayList<String> studyProgramNames = new ArrayList<String>();
		
		for (StudyProgram studyProgram : Manager.INSTANCE.studyPrograms) {
			studyProgramNames.add(studyProgram.getName());
		}
		chBxStudyProgramsList.setItems(FXCollections.observableArrayList(studyProgramNames));
		
	}
	
	public void changeToEditMode() {
		labelName.setVisible(true);
		labelYear.setVisible(true);
		labelMaxCreditsPerSemester.setVisible(true);
		labelMaxFailedCredits.setVisible(true);
		labelSchool.setVisible(true);
		txBxNameStudyProgram.setVisible(true);
		txBxYearStudyProgram.setVisible(true);
		txBxMaxCreditsPerSemester.setVisible(true);
		txBxMaxFailedCredits.setVisible(true);
		chBxSchoolStudyProgram.setVisible(true);
		btnEditSemesters.setVisible(true);
	}

	public void btnCreateStudyProgram_Pressed() {
		ViewUtilities.openView(ASemesterManagerViewController.view, view);
		changeToEditMode();
		ArrayList<String> schools = new ArrayList<String>();
		for (School school : School.values()) {
			schools.add(school.toString());
		}
		chBxSchoolStudyProgram.setItems(FXCollections.observableArrayList(schools));
		
	}
	
	public void btnEditStudyProgram_Pressed() {
		ViewUtilities.openView(ASemesterManagerViewController.view, view);
		changeToEditMode();
		String studyProgramSelected = chBxStudyProgramsList.getSelectionModel().getSelectedItem();
		fillFields(Manager.INSTANCE.getStudyProgramForName(studyProgramSelected));
		
	}
	
	public void fillFields(StudyProgram studyProgram) {
		txBxNameStudyProgram.setText(studyProgram.getName());
		txBxYearStudyProgram.setText(studyProgram.getyearProgram() + "");
		txBxMaxCreditsPerSemester.setText(studyProgram.getMaxCreditsPerSemester() + "");
		txBxMaxFailedCredits.setText(studyProgram.getMaxFailedCredits() + "");
		chBxSchoolStudyProgram.getSelectionModel().select(studyProgram.getSchool().toString());
	}
	
}
