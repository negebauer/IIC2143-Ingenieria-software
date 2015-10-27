package frontend.view.main;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import backend.manager.Manager;
import backend.others.Const;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import backend.users.*;
import backend.users.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class LogInController implements Initializable, IController {
	
	@FXML
	ChoiceBox<String> chBxUsers;
	@FXML
	Button btnSignIn;
	@FXML
	Button btnRegister;
	@FXML
	Label labelSignIn;
	@FXML
	Label labelRegistration;
	@FXML
	Label labelLogIn;
	@FXML
	Button btnLanguage;
	
	public void setUp(){
		btnSignIn.setText(Messages.getUILabel(UILabel.SIGN_IN));
		btnRegister.setText(Messages.getUILabel(UILabel.REGISTER));
		labelSignIn.setText(Messages.getUILabel(UILabel.SIGN_IN_AS_USER));
		labelRegistration.setText(Messages.getUILabel(UILabel.DONT_HAVE_ACCOUNT_REGISTER));
		labelLogIn.setText(Messages.getUILabel(UILabel.LOG_IN));
		btnLanguage.setText(Messages.LANGUAGE().toString().substring(0, 2));
		
		ArrayList<String> users = new ArrayList<String>();
		
		for (Professor professor : Manager.INSTANCE.professors) {
			users.add(Messages.getUILabel(UILabel.PROFESSOR) + " " + professor.getRut() + " " + professor.getName() + " " + professor.getLastnameFather() + " " + professor.getLastnameMother());
		}
		
		for (Student student : Manager.INSTANCE.students) {
			users.add(Messages.getUILabel(UILabel.STUDENT) + " " + student.getRut() + " " + student.getName() + " " + student.getLastnameFather() + " " + student.getLastnameMother());
		}
		
		for (Assistant assistant : Manager.INSTANCE.assistants) {
			users.add(Messages.getUILabel(UILabel.ASSISTANT) + " " + assistant.getRut() + " " + assistant.getName() + " " + assistant.getLastnameFather() + " " + assistant.getLastnameMother());
		}
		
		for (Admin admin : Manager.INSTANCE.admins) {
			users.add(Messages.getUILabel(UILabel.ADMINISTRATOR) + " " + admin.getRut() + " " + admin.getName() + " " + admin.getLastnameFather() + " " + admin.getLastnameMother());
		}
		
		chBxUsers.setItems(FXCollections.observableArrayList(users));
		
	}
	
	public void btnSignIn_Pressed() {
		ArrayList<User> users = new ArrayList<User>();
		users.addAll(Manager.INSTANCE.students);
		users.addAll(Manager.INSTANCE.admins);
		
		for (User user : users) {
			if (this.chBxUsers.getSelectionModel().getSelectedItem().split(" ")[1].equals(user.getRut())) {
				Manager.INSTANCE.currentUser = user;
				break;
			}
		}
		
		if (Manager.INSTANCE.currentUser instanceof Student) {
			URL location = getClass().getResource(Const.MAIN_MENU);
			ViewUtilities.openView(location, "Menu Principal");
		} else if (Manager.INSTANCE.currentUser instanceof Admin) {
			URL location = getClass().getResource(Const.COURSE_ADMIN);
			ViewUtilities.openView(location, "Menu Principal");
		} else {
			System.out.println("ASD");
		}
		
	}
	
	public void btnRegister_Pressed() {
		URL location = getClass().getResource(Const.USER_REGISTRATION);
		ViewUtilities.openView(location, "Registro");
	}
	
	public void btnLanguage_Pressed(){
		Manager.INSTANCE.changeLanguage();
		setUp();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub

	}
}
