package frontend.view.main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import backend.others.Messages;
import backend.others.Messages.UILabel;

public class NewUserRegistrationController implements IController{
	
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
	
	@Override
	public void setUp(){
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
				Messages.getUILabel(UILabel.PROFFESOR), 
				Messages.getUILabel(UILabel.ADMINISTRATOR)
		};
		
		chBxAccess.setItems(FXCollections.observableArrayList(accesos));
		chBxSex.setItems(FXCollections.observableArrayList(Messages.getUILabel(UILabel.MALE), Messages.getUILabel(UILabel.FEMALE)));
		
		chBxAccess.getSelectionModel().selectedIndexProperty().addListener(
				new ChangeListener<Number>() {
					public void changed (ObservableValue<? extends Number> observableValue, Number value, Number newValue) {
						if (accesos[newValue.intValue()] == Messages.getUILabel(UILabel.STUDENT)){
							showStudentFields();
						} else {
							hideStudentFields();
							listCarreers.setItems(FXCollections.observableArrayList());
						}
			}
		});
		
		
	}
	
	public void btnContinue_Pressed() {
		switch (chBxAccess.getSelectionModel().getSelectedItem()){
		case "hola": break;
		default:
		}
		
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
