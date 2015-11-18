package frontend.main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.StudyProgram;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import backend.users.Admin;
import backend.users.Professor;
import backend.users.Student;
import backend.users.User.Gender;
import frontend.others.Validate;
import frontend.others.ViewUtilities;

public class MNewUserRegistrationController extends MViewController {
	
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
	
	static URL view = Object.class.getResource("/frontend/main/MNewUserRegistration.fxml");
	
	public void setUp(){
		super.setUp();
		hideLogout();
		hideStudentFields();
		
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
		
		ArrayList<String> studyProgramsNames = new ArrayList<String>();
		for (StudyProgram studyProgram : Manager.INSTANCE.studyPrograms) {
			studyProgramsNames.add(studyProgram.getName());
		}
		chBxCarreers.setItems(FXCollections.observableArrayList(studyProgramsNames));
		chBxCarreers.getSelectionModel().selectFirst();
		
		String[] accesos = new String[]{
				Messages.getUILabel(UILabel.STUDENT), 
				Messages.getUILabel(UILabel.PROFESSOR), 
				Messages.getUILabel(UILabel.ADMINISTRATOR)
		};
		
		chBxAccess.setItems(FXCollections.observableArrayList(accesos));
		chBxSex.setItems(FXCollections.observableArrayList(Messages.getUILabel(UILabel.MALE), Messages.getUILabel(UILabel.FEMALE)));
		chBxSex.getSelectionModel().selectFirst();
		
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
		chBxAccess.getSelectionModel().selectFirst();
	}
	
	public void btnContinue_Pressed() {
		this.cleanInfo();
		if (!this.checkValid())
			return;
		
		String name = txBxName.getText();
		String lastFather = txBxLastFather.getText();
		String lastMother = txBxLastMother.getText();
		String number = txBxCellPhone.getText();
		String address = txBxAdress.getText();
		String rut = txBxRUT.getText();
		String birthday = txBxBirthDay.getText() + "." + txBxBirthMonth.getText() + "." + txBxBirthYear.getText();
		Gender gender = chBxSex.getSelectionModel().getSelectedItem().equals(Messages.getUILabel(UILabel.MALE)) ? Gender.MALE : Gender.FEMALE;
		
		if (isAdmin) {
			Admin newAdmin = new Admin(rut, name, lastFather, lastMother, address, gender, number, birthday);
			Manager.INSTANCE.admins.add(newAdmin);
		} else if (isProfessor) {
			Professor newProfessor = new Professor(rut, name, lastFather, lastMother, address, gender, number, birthday);
			Manager.INSTANCE.professors.add(newProfessor);
		} else if (isStudent) {
			ArrayList<StudyProgram> studyPrograms = new ArrayList<StudyProgram>();
			for (String studyProgram : chBxCarreers.getItems()) {
				studyPrograms.add(Manager.INSTANCE.getStudyProgramForName(studyProgram));
			}
			int id = Manager.INSTANCE.getNewStudentID();
			int year = Manager.getYear();
			Student newStudent = new Student(id, year, studyPrograms, rut, name, lastFather, lastMother, address, gender, number, birthday);
			Manager.INSTANCE.students.add(newStudent);
		}
		ViewUtilities.openView(MLogInController.view);
	}
	
	public void cleanInfo() {
		if (txBxRUT.getText().contains(".") || txBxRUT.getText().contains("-")) {
			String clean = "";
			for (char character : txBxRUT.getText().toCharArray()) {
				if (character != '.' && character != '-')
					clean += character;
			}
			txBxRUT.setText(clean);
		}
		
		if (txBxCellPhone.getText().contains("+")) {
			String clean = "";
			for (char character : txBxCellPhone.getText().toCharArray()) {
				if (character != '+')
					clean += character;
			}
			txBxCellPhone.setText(clean);
		}
	}
	
	public Boolean checkValid() {	
		return (txBxRUT.getText().length() < 9 && Validate.checkRUT(txBxRUT.getText()));
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
	
	public void btnAddStudyPlan_Pressed() {
		String studyProgramName = chBxCarreers.getSelectionModel().getSelectedItem();
		if (!listCarreers.getItems().contains(studyProgramName) && studyProgramName != null) {
			ObservableList<String> currentCarrers = listCarreers.getItems();
			currentCarrers.add(studyProgramName);
			listCarreers.setItems(FXCollections.observableArrayList(currentCarrers));
		}
	}
	
	public void btnRemoveStudyPlan_Pressed() {
		String studyProgramName = chBxCarreers.getSelectionModel().getSelectedItem();
		if (listCarreers.getItems().contains(studyProgramName) && studyProgramName != null) {
			ObservableList<String> currentCarrers = listCarreers.getItems();
			currentCarrers.remove(studyProgramName);
			listCarreers.setItems(FXCollections.observableArrayList(currentCarrers));
		}
	}
}