package frontend.view.main;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import backend.others.Messages;
import backend.others.Messages.UILabel;

public class LogInController {
	
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
}
