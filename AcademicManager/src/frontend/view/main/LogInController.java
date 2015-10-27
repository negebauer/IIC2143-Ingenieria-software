package frontend.view.main;

import java.net.URL;
import java.util.ResourceBundle;

import backend.others.Const;
import backend.others.Messages;
import backend.others.Messages.UILabel;
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
	
	public void setUp(){
		btnSignIn.setText(Messages.getUILabel(UILabel.SIGN_IN));
		btnRegister.setText(Messages.getUILabel(UILabel.REGISTER));
		labelSignIn.setText(Messages.getUILabel(UILabel.SIGN_IN_AS_USER));
		labelRegistration.setText(Messages.getUILabel(UILabel.DONT_HAVE_ACCOUNT_REGISTER));
		labelLogIn.setText(Messages.getUILabel(UILabel.LOG_IN));
	}
	
	public void btnSignIn_Pressed(){
		URL location = getClass().getResource(Const.MAIN_MENU);
		ViewUtilities.openView(location, "Menu Principal");
	}
	
	public void btnRegister_Pressed(){
		URL location = getClass().getResource(Const.USER_REGISTRATION);
		ViewUtilities.openView(location, "Registro");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub

	}
}
