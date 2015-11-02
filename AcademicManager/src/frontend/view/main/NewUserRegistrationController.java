package frontend.view.main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

import backend.courses.StudyProgram;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import backend.users.Admin;
import backend.users.Professor;
import backend.users.Student;
import backend.users.User.Gender;

public class NewUserRegistrationController extends UIViewController {
	
	@FXML
	Label labelToUseATMMustRegister;
	@FXML
	TextField txBxName;
	@FXML
	Button btnContinue;
	@FXML
	TextField txBxLastFather;
	@FXML
	TextField txBxLastMother;
	@FXML
	TextField txBxAdress;
	@FXML
	TextField txBxRUT;
	@FXML
	TextField txBxBirthDay;
	@FXML
	TextField txBxCellPhone;
	@FXML
	ChoiceBox<String> chBxAccess;
	@FXML
	ChoiceBox<String> chBxSex;
	@FXML
	Label labelName;
	@FXML
	Label labelLastFather;
	@FXML
	Label labelLastMother;
	@FXML
	Label labelAdress;
	@FXML
	Label labelCellPhone;
	@FXML
	Label labelRUT;
	@FXML
	Label labelBirthday;
	@FXML
	Label labelSex;
	@FXML
	Label labelAccess;
	@FXML
	TextField txBxBirthMonth;
	@FXML
	Label labelBirthDay;
	@FXML
	Label labelBirthMonth;
	@FXML
	Label labelBirthYear;
	@FXML
	TextField txBxBirthYear;
	@FXML
	ChoiceBox<String> chBxCarreers;
	@FXML
	ListView<String> listCarreers;
	@FXML
	Label labelPickCarreer;
	@FXML
	Button btnAddStudyPlan;
	@FXML
	Button btnRemoveStudyPlan;
	
	boolean isStudent;
	boolean isProfessor;
	boolean isAdmin;
	
	static URL view = Object.class.getResource("/frontend/view/main/NewUserRegistration.fxml");
	
	@Override
	public void setUp(){
		super.setUp();
		hideLogout();
		
		labelToUseATMMustRegister.setText(Messages.getUILabel(UILabel.TO_USE_ATM_MUST_REGISTER));
		btnContinue.setText(Messages.getUILabel(UILabel.CONTINUE));
		
		labelName.setText(Messages.getUILabel(UILabel.NAME));
		labelLastFather.setText(Messages.getUILabel(UILabel.LAST_NAME_FATHER));
		labelLastMother.setText(Messages.getUILabel(UILabel.LAST_NAME_MOTHER));
		labelAdress.setText(Messages.getUILabel(UILabel.ADDRESS));
		labelCellPhone.setText(Messages.getUILabel(UILabel.CELL_PHONE));
		labelRUT.setText(Messages.getUILabel(UILabel.RUT));;
		labelBirthday.setText(Messages.getUILabel(UILabel.BIRTHDAY));
		labelSex.setText(Messages.getUILabel(UILabel.SEX));
		labelAccess.setText(Messages.getUILabel(UILabel.ACCESS));
		labelBirthDay.setText(Messages.getUILabel(UILabel.DAY));
		labelBirthMonth.setText(Messages.getUILabel(UILabel.MONTH));
		labelBirthYear.setText(Messages.getUILabel(UILabel.YEAR));
		labelPickCarreer.setText(Messages.getUILabel(UILabel.PICK_CARREER));
		btnAddStudyPlan.setText(Messages.getUILabel(UILabel.ADD));
		btnRemoveStudyPlan.setText(Messages.getUILabel(UILabel.REMOVE));
		
		//TODO listCarreers
		
		String[] accesos = new String[]{
				Messages.getUILabel(UILabel.STUDENT), 
				Messages.getUILabel(UILabel.PROFESSOR), 
				Messages.getUILabel(UILabel.ADMINISTRATOR)
		};
		
		chBxAccess.setItems(FXCollections.observableArrayList(accesos));
		chBxSex.setItems(FXCollections.observableArrayList(Messages.getUILabel(UILabel.MALE), Messages.getUILabel(UILabel.FEMALE)));
		
		chBxAccess.getSelectionModel().selectedIndexProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed (ObservableValue<? extends Number> observableValue, Number value, Number newValue) {
						if (accesos[newValue.intValue()] == Messages.getUILabel(UILabel.STUDENT)){
							showStudentFields();
							isAdmin = false;
							isProfessor = false;
							isStudent = true;
						} else if (accesos[newValue.intValue()] == Messages.getUILabel(UILabel.PROFESSOR)) {
							hideStudentFields();
							isAdmin = false;
							isProfessor = true;
							isStudent = false;
							listCarreers.setItems(FXCollections.observableArrayList());
						} else if (accesos[newValue.intValue()] == Messages.getUILabel(UILabel.ADMINISTRATOR)) {
							hideStudentFields();
							isAdmin = true;
							isProfessor = false;
							isStudent = false;
							listCarreers.setItems(FXCollections.observableArrayList());
						}
			}
		});
		
		
	}
	
	public void btnContinue_Pressed() {
		// TODO Clean text boxes and selectiones BEFORE creating Users.
		Gender gender = chBxSex.getSelectionModel().getSelectedItem().equals(Messages.getUILabel(UILabel.MALE)) ? Gender.MALE : Gender.FEMALE;
		
		if (isAdmin) {
			Manager.INSTANCE.admins.add(new Admin(txBxRUT.getText(), txBxName.getText(), txBxLastFather.getText(), txBxLastMother.getText(), txBxAdress.getText(), gender, txBxCellPhone.getText().split("+")[1], txBxBirthDay.getText() + "." + txBxBirthMonth.getText() + "." + txBxBirthYear.getText()));
		} else if (isProfessor) {
			Manager.INSTANCE.professors.add(new Professor(txBxRUT.getText(), txBxName.getText(), txBxLastFather.getText(), txBxLastMother.getText(), txBxAdress.getText(), gender, txBxCellPhone.getText().split("+")[1], txBxBirthDay.getText() + "." + txBxBirthMonth.getText() + "." + txBxBirthYear.getText()));
		} else if (isStudent) {
			ArrayList<StudyProgram> studyPrograms = new ArrayList<StudyProgram>();
			for (String carreer : chBxCarreers.getItems()) {
				studyPrograms.add(Manager.INSTANCE.getStudyProgramForName(carreer));
			}
			Manager.INSTANCE.students.add(new Student(Manager.INSTANCE.getNewStudentID(), Calendar.YEAR, studyPrograms, txBxRUT.getText(), txBxName.getText(), txBxLastFather.getText(), txBxLastMother.getText(), txBxAdress.getText(), gender, txBxCellPhone.getText().split("+")[1], txBxBirthDay.getText() + "." + txBxBirthMonth.getText() + "." + txBxBirthYear.getText()));
		}
		ViewUtilities.openView(LogInController.view, null);
	}
	
	public void showStudentFields() {
		listCarreers.setVisible(true);
		chBxCarreers.setVisible(true);
		labelPickCarreer.setVisible(true);
		btnAddStudyPlan.setVisible(true);
		btnRemoveStudyPlan.setVisible(true);
	}
	
	public void hideStudentFields(){
		listCarreers.setVisible(false);
		chBxCarreers.setVisible(false);
		labelPickCarreer.setVisible(false);
		btnAddStudyPlan.setVisible(false);
		btnRemoveStudyPlan.setVisible(false);
	}
	
}
