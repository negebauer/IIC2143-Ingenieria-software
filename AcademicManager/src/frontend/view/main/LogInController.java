package frontend.view.main;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import backend.manager.Manager;
import backend.others.Const;
import backend.others.Messages;
import backend.others.Messages.UILabel;
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
	Button btnLenguage;
	
	public void setUp(){
		btnSignIn.setText(Messages.getUILabel(UILabel.SIGN_IN));
		btnRegister.setText(Messages.getUILabel(UILabel.REGISTER));
		labelSignIn.setText(Messages.getUILabel(UILabel.SIGN_IN_AS_USER));
		labelRegistration.setText(Messages.getUILabel(UILabel.DONT_HAVE_ACCOUNT_REGISTER));
		labelLogIn.setText(Messages.getUILabel(UILabel.LOG_IN));
		
		ArrayList<String> users = new ArrayList<String>();
		for(User user : Manager.INSTANCE.students)
			users.add(user.getRut());
		
		chBxUsers.setItems(FXCollections.observableArrayList(users));
	}
	
	public void btnSignIn_Pressed(){
			
		User current = null;
		for(User user : Manager.INSTANCE.students)
			if(this.chBxUsers.getSelectionModel().getSelectedItem() == user.getRut()){
				current = user;
				break;
			}		
		Manager.INSTANCE.currentUser = current;
		
		URL location = getClass().getResource(Const.MAIN_MENU);
		ViewUtilities.openView(location, "Menu Principal");
	}
	
	public void btnRegister_Pressed(){
		URL location = getClass().getResource(Const.USER_REGISTRATION);
		ViewUtilities.openView(location, "Registro");
	}
	
	public void btnLenguage_Pressed(){
		Manager.INSTANCE.changeLanguage();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub

	}
}
